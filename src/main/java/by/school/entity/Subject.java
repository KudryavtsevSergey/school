package by.school.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Subject {
    private int subjectId;
    private String name;
    private String description;
    private Collection<Mark> marksBySubjectId;
    private Collection<SubjectInSchedule> subjectInSchedulesBySubjectId;

    @Id
    @Column(name = "subject_id", nullable = false)
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (subjectId != subject.subjectId) return false;
        if (name != null ? !name.equals(subject.name) : subject.name != null) return false;
        if (description != null ? !description.equals(subject.description) : subject.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subjectId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "subjectBySubjectId")
    public Collection<Mark> getMarksBySubjectId() {
        return marksBySubjectId;
    }

    public void setMarksBySubjectId(Collection<Mark> marksBySubjectId) {
        this.marksBySubjectId = marksBySubjectId;
    }

    @OneToMany(mappedBy = "subjectBySubjectId")
    public Collection<SubjectInSchedule> getSubjectInSchedulesBySubjectId() {
        return subjectInSchedulesBySubjectId;
    }

    public void setSubjectInSchedulesBySubjectId(Collection<SubjectInSchedule> subjectInSchedulesBySubjectId) {
        this.subjectInSchedulesBySubjectId = subjectInSchedulesBySubjectId;
    }
}
