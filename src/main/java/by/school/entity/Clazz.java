package by.school.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "class", schema = "school_journal_db", catalog = "")
public class Clazz {
    private int classId;
    private int number;
    private String letterMark;
    private Collection<Pupil> pupilsByClassId;
    private Collection<SubjectInSchedule> subjectInSchedulesByClassId;
    private Collection<Teacher> teachersByClassId;

    @Id
    @Column(name = "class_id", nullable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "letter_mark", nullable = false, length = 45)
    public String getLetterMark() {
        return letterMark;
    }

    public void setLetterMark(String letterMark) {
        this.letterMark = letterMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clazz clazz = (Clazz) o;

        if (classId != clazz.classId) return false;
        if (number != clazz.number) return false;
        if (letterMark != null ? !letterMark.equals(clazz.letterMark) : clazz.letterMark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + number;
        result = 31 * result + (letterMark != null ? letterMark.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clazzByClassId")
    public Collection<Pupil> getPupilsByClassId() {
        return pupilsByClassId;
    }

    public void setPupilsByClassId(Collection<Pupil> pupilsByClassId) {
        this.pupilsByClassId = pupilsByClassId;
    }

    @OneToMany(mappedBy = "clazzByClassId")
    public Collection<SubjectInSchedule> getSubjectInSchedulesByClassId() {
        return subjectInSchedulesByClassId;
    }

    public void setSubjectInSchedulesByClassId(Collection<SubjectInSchedule> subjectInSchedulesByClassId) {
        this.subjectInSchedulesByClassId = subjectInSchedulesByClassId;
    }

    @OneToMany(mappedBy = "clazzByClassId")
    public Collection<Teacher> getTeachersByClassId() {
        return teachersByClassId;
    }

    public void setTeachersByClassId(Collection<Teacher> teachersByClassId) {
        this.teachersByClassId = teachersByClassId;
    }
}
