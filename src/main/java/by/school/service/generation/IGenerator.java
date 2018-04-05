package by.school.service.generation;

import by.school.entity.*;
import by.school.service.exception.ServiceException;

import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

public interface IGenerator {
    OutputStream generateClassPupilListDocument(OutputStream os, Teacher teacher, Clazz clazz, List<Pupil> pupilList)
            throws ServiceException;

    OutputStream generateTeacherScheduleDocument(OutputStream os, Teacher teacher, List<SubjectInSchedule> subjectInScheduleList,
                                                 List<LessonTime> lessonTimeList) throws ServiceException;

    OutputStream generateClassScheduleDocument(OutputStream os, Clazz clazz, List<SubjectInSchedule> subjectInScheduleList,
                                               List<LessonTime> lessonTimeList) throws ServiceException;

    OutputStream generateFullScheduleDocument(OutputStream os, List<SubjectInSchedule> subjectInScheduleList,
                                              List<LessonTime> lessonTimeList, List<Teacher> teacherList) throws ServiceException;

    OutputStream generateMarksDocument(OutputStream os, Subject subject, List<Mark> markList, List<Pupil> pupilList, Clazz clazz,
                                       List<Date> lessonDateList) throws ServiceException;
}
