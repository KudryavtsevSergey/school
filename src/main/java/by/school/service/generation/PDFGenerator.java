package by.school.service.generation;

import by.school.entity.*;
import by.school.entity.enums.DayOfWeekEnum;
import by.school.entity.enums.MarkType;
import by.school.service.exception.ServiceException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.OutputStream;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.List;

public class PDFGenerator implements IGenerator {
    private static final String PATH_TO_FONT = "c:/Windows/Fonts/arial.ttf";

    @Override
    public OutputStream generateClassPupilListDocument(OutputStream os, Teacher teacher,
                                                       Clazz clazz, List<Pupil> pupilList) throws ServiceException {
        try {
            Font font = createFont();
            Document doc = createDocument(os);
            doc.open();
            doc.add(generateParagraph(font,
                    MessageFormat.format("Класс: {0} \"{1}\"", clazz.getNumber(), clazz.getLetterMark())));
            doc.add(generateParagraph(font,
                    MessageFormat.format("Классный руководитель: {0}", teacher.getLastName())));
            addTableWithPupils(doc, font, pupilList);
            doc.close();
        } catch (Exception exc) {
            throw new ServiceException("Can't build document" );
        }
        return os;
    }

    @Override
    public OutputStream generateTeacherScheduleDocument(OutputStream os, Teacher teacher, List<SubjectInSchedule> subjectInScheduleList, List<LessonTime> lessonTimeList) throws ServiceException {
        try {
            Font font = createFont();
            Document doc = createDocument(os);
            doc.open();
            doc.add(generateParagraph(font, "Расписание уроков для учителя"));
            doc.add(generateParagraph(font,
                    MessageFormat.format("Учитель: {0}", teacher.getLastName())));
            addTeacherScheduleToDocument(doc, font, subjectInScheduleList, lessonTimeList);
            doc.close();
        } catch (Exception exc) {
            throw new ServiceException("Can't build document");
        }
        return os;
    }

    @Override
    public OutputStream generateClassScheduleDocument(OutputStream os,
                                                      Clazz clazz, List<SubjectInSchedule> subjectInScheduleList,
                                                      List<LessonTime> lessonTimeList) throws ServiceException {
        try {
            Font font = createFont();
            Document doc = createDocument(os);
            doc.open();
            doc.add(generateParagraph(font, "Расписание уроков для класса"));
            doc.add(generateParagraph(font,
                    MessageFormat.format("Класс: {0} \"{1}\"", clazz.getNumber(), clazz.getLetterMark())));
            addClassScheduleToDocument(doc, font, subjectInScheduleList, lessonTimeList);
            doc.close();
        } catch (Exception exc) {
            throw new ServiceException("Can't build document");
        }
        return os;
    }

    @Override
    public OutputStream generateFullScheduleDocument(OutputStream os, List<SubjectInSchedule> subjectInScheduleList,
                                                     List<LessonTime> lessonTimeList,
                                                     List<Teacher> teacherList)
            throws ServiceException {
        try {
            Font font = createFont();
            Document doc = createDocument(os);
            doc.open();
            doc.add(generateParagraph(font, "Полное расписание школы"));
            addFullScheduleToDocument(doc, font, subjectInScheduleList, lessonTimeList, teacherList);
            doc.close();
        } catch (Exception exc) {
            throw new ServiceException("Can't build document");
        }
        return os;
    }

    @Override
    public OutputStream generateMarksDocument(OutputStream os, Subject subject, List<Mark> markList,
                                              List<Pupil> pupilList, Clazz clazz,
                                              List<Date> lessonDateList) throws ServiceException {
        try {
            Font font = createFont();
            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(doc, os);
            writer.setEncryption("".getBytes(), "".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
           // writer.setPageEvent(new Watermark());
            doc.open();
            doc.add(generateParagraph(font,
                    MessageFormat.format("Оценки по предмету {0} {1} \"{2}\" класса", subject.getName(),
                            clazz.getNumber(), clazz.getLetterMark())));
            addMarksToDocument(doc, font, markList, pupilList, lessonDateList);
            doc.close();
        } catch (Exception exc) {

            throw new ServiceException("Can't build document");
        }
        return os;
    }

    private Paragraph generateParagraph(Font font, String text) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        return paragraph;
    }


    private void addTableWithPupils(Document doc, Font font, List<Pupil> pupilList) throws DocumentException {
        PdfPTable table = new PdfPTable(3);

        float[] columnWidths = {1f, 4f, 4f};
        table.setWidths(columnWidths);
        table.setSpacingBefore(4f);

        for(int i = 0; i < pupilList.size(); i++) {
            PdfPCell numberCell = new PdfPCell();
            PdfPCell fioCell = new PdfPCell();
            PdfPCell phoneNumberCell = new PdfPCell();

            numberCell.addElement(generateParagraph(font, i + 1 + ""));
            fioCell.addElement(generateParagraph(font, pupilList.get(i).getLastName()));
            phoneNumberCell.addElement(generateParagraph(font, pupilList.get(i).getPhoneNumber()));

            table.addCell(numberCell);
            table.addCell(fioCell);
            table.addCell(phoneNumberCell);
        }

        doc.add(table);
    }

//    private void addWatermarkToDocument(Document doc) throws DocumentException {
//        ClassLoader classLoader = getClass().getClassLoader();
//        try {
//            InputStream is = new FileInputStream(new File(classLoader.getResource("images/watermark.png").getFile()));
//            byte[] data = new byte[is.available()];
//            is.read(data);
//            Image img = Image.getInstance(data);
//            img.setAbsolutePosition(0, 0);
//            doc.add(img);
//        } catch (IOException exc) {}
//    }

//    public class Watermark extends PdfPageEventHelper {
//        @Override
//        public void onEndPage(PdfWriter writer, Document document) {
//            try {
//                addWatermarkToDocument(document);
//            } catch(DocumentException exc) {}
//        }
//    }

    private Document createDocument(OutputStream os) throws DocumentException {
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, os);
       // writer.setPageEvent(new Watermark());
        writer.setEncryption("".getBytes(), "".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        return doc;
    }

    private Font createFont() throws Exception {
        BaseFont baseFont = BaseFont.createFont(PATH_TO_FONT, "CP1251", BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 10, Font.NORMAL);
        return font;
    }

    private void addClassScheduleToDocument(Document doc, Font font, List<SubjectInSchedule> subjectInScheduleList,
                                            List<LessonTime> lessonTimeList) throws DocumentException {
        DayOfWeekEnum[] days = DayOfWeekEnum.values();
        for(int dayIndex = 1; dayIndex < days.length; dayIndex++) {
            DayOfWeekEnum day = days[dayIndex];
            doc.add(generateParagraph(font, getDayRussianName(day)));

            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(4f);

            for(LessonTime lessonTime : lessonTimeList) {
                PdfPCell subjectCell = new PdfPCell();
                PdfPCell beginTimeCell = new PdfPCell();
                PdfPCell teacherCell = new PdfPCell();
                PdfPCell placeCell = new PdfPCell();

                SubjectInSchedule subject = getSubjectByBeginTimeAndDay(subjectInScheduleList, lessonTime, day);
                if(subject != null) {
                    subjectCell.addElement(generateParagraph(font, subject.getSubjectBySubjectId().getName()));
                    teacherCell.addElement(generateParagraph(font, subject.getTeacherByTeacherId().getLastName()));
                    placeCell.addElement(generateParagraph(font, subject.getPlace()));

                }
                beginTimeCell.addElement(generateParagraph(font, lessonTime.getStartTime().toString()));

                table.addCell(beginTimeCell);
                table.addCell(subjectCell);
                table.addCell(teacherCell);
                table.addCell(placeCell);
            }

            doc.add(table);
        }
    }

    private void addTeacherScheduleToDocument(Document doc, Font font, List<SubjectInSchedule> subjectInScheduleList,
                                              List<LessonTime> lessonTimeList) throws DocumentException {
        DayOfWeekEnum[] days = DayOfWeekEnum.values();
        for(int dayIndex = 1; dayIndex < days.length; dayIndex++) {
            DayOfWeekEnum day = days[dayIndex];
            doc.add(generateParagraph(font, getDayRussianName(day)));

            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(4f);

            for(LessonTime lessonTime : lessonTimeList) {
                PdfPCell beginTimeCell = new PdfPCell();
                PdfPCell subjectCell = new PdfPCell();
                PdfPCell classCell = new PdfPCell();
                PdfPCell placeCell = new PdfPCell();

                SubjectInSchedule subject = getSubjectByBeginTimeAndDay(subjectInScheduleList, lessonTime, day);
                if(subject != null) {
                    subjectCell.addElement(generateParagraph(font, subject.getSubjectBySubjectId().getName()));
                    classCell.addElement(generateParagraph(font,
                            MessageFormat.format("{0} \"{1}\"",
                                    subject.getClazzByClassId().getNumber(), subject.getClazzByClassId().getLetterMark())));
                    placeCell.addElement(generateParagraph(font, subject.getPlace()));

                }
                beginTimeCell.addElement(generateParagraph(font, lessonTime.getStartTime().toString()));

                table.addCell(beginTimeCell);
                table.addCell(subjectCell);
                table.addCell(classCell);
                table.addCell(placeCell);
            }

            doc.add(table);
        }
    }

    private void addFullScheduleToDocument(Document doc, Font font, List<SubjectInSchedule> subjectInScheduleList,
                                           List<LessonTime> lessonTimeList,
                                           List<Teacher> teacherList) throws DocumentException {
        for(Teacher teacher : teacherList) {
            doc.add(generateParagraph(font, MessageFormat.format("Учитель: {0}", teacher.getLastName())));
            addTeacherScheduleToDocument(doc, font, subjectInScheduleList, lessonTimeList);
            doc.newPage();
        }
    }

    private SubjectInSchedule getSubjectByBeginTimeAndDay(List<SubjectInSchedule> subjectInScheduleList,
                                                          LessonTime lessonTime, DayOfWeekEnum day) {
        for (SubjectInSchedule subject : subjectInScheduleList) {
           if( DayOfWeekEnum.valueOf(subject.getDayOfWeek()).equals(day))
            if(subject.getBeginTime().equals(lessonTime.getStartTime()) )
                return subject;
        }
        return null;
    }

    private String getDayRussianName(DayOfWeekEnum day) {
        switch(day) {
            case mon:
                return "Понедельник";
            case tue:
                return "Вторник";
            case wed:
                return "Среда";
            case thu:
                return "Четверг";
            case fri:
                return "Пятница";
            case sat:
                return "Суббота";
        }
        return null;
    }

    private void addMarksToDocument(Document doc, Font font, List<Mark> markList, List<Pupil> pupilList,
                                    List<Date> lessonDateList)
            throws DocumentException {
        int i = 0;
        while(i < lessonDateList.size()) {
            int columnCount = (lessonDateList.size() - i);
            columnCount = columnCount > 8 ? 8 : columnCount;

            PdfPTable table = new PdfPTable(1 + columnCount);
            table.setSpacingBefore(4f);
            table.setWidthPercentage(100f);

            table.addCell(new PdfPCell());

            for(int j = 0; j < columnCount; j++) {
                Date date = lessonDateList.get(i + j);
                PdfPCell dateCell = new PdfPCell();
                dateCell.addElement(generateParagraph(font, date.toString()));
                table.addCell(dateCell);
            }

            for(Pupil pupil : pupilList) {
                PdfPCell pupilCell = new PdfPCell();
                pupilCell.addElement(generateParagraph(font, pupil.getLastName()));
                table.addCell(pupilCell);

                for(int j = 0; j < columnCount; j++) {
                    Date date = lessonDateList.get(i + j);
                    PdfPCell markCell = new PdfPCell();
                    Mark mark = getMarkByPupilAndDay(markList, pupil.getUserByPupilId().getUserId(), date);
                    if(mark != null) {
                        markCell.addElement(generateParagraph(font, getMarkDescription(mark)));
                       // setBackgroundToMarkCell(markCell, mark);
                    }
                    table.addCell(markCell);
                }
            }
            i += columnCount;
            doc.add(table);
            doc.newPage();
        }

        PdfPTable table = new PdfPTable(6);
        table.setSpacingBefore(4f);
        table.setWidthPercentage(100f);

        table.addCell(new PdfPCell());
        for(int term = 1; term <= 4; term++) {
            PdfPCell termCell = new PdfPCell();
            termCell.addElement(generateParagraph(font, MessageFormat.format("{0} четв.", term)));
            table.addCell(termCell);
        }

        PdfPCell yearCell = new PdfPCell();
        yearCell.addElement(generateParagraph(font, "Год."));
        table.addCell(yearCell);

        for(Pupil pupil : pupilList) {
            PdfPCell pupilCell = new PdfPCell();
            pupilCell.addElement(generateParagraph(font, pupil.getLastName()));
            table.addCell(pupilCell);

            for(int j = 1; j <= 4; j++) {
                PdfPCell markCell = new PdfPCell();
                Mark mark = getTermMarkByPupil(markList, pupil.getUserByPupilId().getUserId(), j);
                if(mark != null) {
                    markCell.addElement(generateParagraph(font, getMarkDescription(mark)));
                    //setBackgroundToMarkCell(markCell, mark);
                }
                table.addCell(markCell);
            }

            PdfPCell markCell = new PdfPCell();
            Mark mark = getYearMarkByPupil(markList, pupil.getUserByPupilId().getUserId());
            if(mark != null) {
                markCell.addElement(generateParagraph(font, getMarkDescription(mark)));
                //setBackgroundToMarkCell(markCell, mark);
            }
            table.addCell(markCell);
        }

        doc.add(table);

    }

    private String getMarkDescription(Mark mark) {
        switch(MarkType.valueOf(mark.getType())) {
            case apsent:
                return "Н";
            case self:
                return MessageFormat.format("{0} (сам.)", mark.getValue());
            case control:
                return MessageFormat.format("{0} (контр.)", mark.getValue());
            case simple:
                return MessageFormat.format("{0}", mark.getValue());
            case year:
                return MessageFormat.format("{0}", mark.getValue());
            case term:
                return MessageFormat.format("{0}", mark.getValue());
            default:
                return "";
        }
    }

//    private void setBackgroundToMarkCell(PdfPCell markCell, Mark mark) {
////        switch(mark.getType()) {
////            case apsent:
////                markCell.setBackgroundColor(BaseColor.GRAY);
////            case self:
////                markCell.setBackgroundColor(BaseColor.BLUE);
////            case control:
////                markCell.setBackgroundColor(BaseColor.RED);
////            case year:
////                markCell.setBackgroundColor(BaseColor.ORANGE);
////            case term:
////                markCell.setBackgroundColor(BaseColor.YELLOW);
////            case simple:
////            default:
////                return;
////        }
//    }

    private Mark getMarkByPupilAndDay(List<Mark> markList, int pupilId, Date date) {
        for (Mark mark : markList) {
            if(mark.getPupilByPupilId().getUserByPupilId().getUserId() == pupilId && mark.getDate() != null && mark.getDate().equals(date)) {
                return mark;
            }
        }
        return null;
    }

    private Mark getYearMarkByPupil(List<Mark> markList, int pupilId) {
        for (Mark mark : markList) {
            if(mark.getPupilByPupilId().getUserByPupilId().getUserId() == pupilId && MarkType.valueOf(mark.getType()) == MarkType.year) {
                return mark;
            }
        }
        return null;
    }

    private Mark getTermMarkByPupil(List<Mark> markList, int pupilId, int termNumber) {
        for (Mark mark : markList) {
            if(mark.getPupilByPupilId().getUserByPupilId().getUserId() == pupilId && MarkType.valueOf(mark.getType()) == MarkType.term &&
                    mark.getTermNumber() == termNumber) {
                return mark;
            }
        }
        return null;
    }
}
