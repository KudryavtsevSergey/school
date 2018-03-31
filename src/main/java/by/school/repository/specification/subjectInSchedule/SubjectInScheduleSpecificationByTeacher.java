package by.school.repository.specification.subjectInSchedule;

import by.school.entity.SubjectInSchedule;
import by.school.entity.Teacher;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class SubjectInScheduleSpecificationByTeacher extends SubjectInScheduleSpecification {
    private Teacher teacher;

    public SubjectInScheduleSpecificationByTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("teacher", teacher);
    }

    //
    @Override
    public boolean specified(SubjectInSchedule subjectInSchedule) {
        return subjectInSchedule.getTeacherByTeacherId() == teacher;
    }
}
