package by.school.entity;

import by.school.entity.enums.MarkType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Mark {
    private int markId;
    private int pupilId;
    private Integer value;
    private String type;
    private Date date;
    private int subjectId;
    private Integer teacherId;
    private Integer termNumber;
    private Pupil pupilByPupilId;
    private Subject subjectBySubjectId;
    private Teacher teacherByTeacherId;

    @Id
    @Column(name = "mark_id", nullable = false)
    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    @Basic
    @Column(name = "pupil_id", nullable = false)
    public int getPupilId() {
        return pupilId;
    }

    public void setPupilId(int pupilId) {
        this.pupilId = pupilId;
    }

    @Basic
    @Column(name = "value", nullable = true)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "subject_id", nullable = false)
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "teacher_id", nullable = true)
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "term_number", nullable = true)
    public Integer getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(Integer termNumber) {
        this.termNumber = termNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark = (Mark) o;

        if (markId != mark.markId) return false;
        if (pupilId != mark.pupilId) return false;
        if (subjectId != mark.subjectId) return false;
        if (value != null ? !value.equals(mark.value) : mark.value != null) return false;
        if (type != null ? !type.equals(mark.type) : mark.type != null) return false;
        if (date != null ? !date.equals(mark.date) : mark.date != null) return false;
        if (teacherId != null ? !teacherId.equals(mark.teacherId) : mark.teacherId != null) return false;
        if (termNumber != null ? !termNumber.equals(mark.termNumber) : mark.termNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = markId;
        result = 31 * result + pupilId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + subjectId;
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (termNumber != null ? termNumber.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pupil_id", referencedColumnName = "pupil_id", nullable = false)
    public Pupil getPupilByPupilId() {
        return pupilByPupilId;
    }

    public void setPupilByPupilId(Pupil pupilByPupilId) {
        this.pupilByPupilId = pupilByPupilId;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false)
    public Subject getSubjectBySubjectId() {
        return subjectBySubjectId;
    }

    public void setSubjectBySubjectId(Subject subjectBySubjectId) {
        this.subjectBySubjectId = subjectBySubjectId;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    public Teacher getTeacherByTeacherId() {
        return teacherByTeacherId;
    }

    public void setTeacherByTeacherId(Teacher teacherByTeacherId) {
        this.teacherByTeacherId = teacherByTeacherId;
    }
}
