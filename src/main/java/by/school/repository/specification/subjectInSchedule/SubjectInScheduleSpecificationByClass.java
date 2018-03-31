package by.school.repository.specification.subjectInSchedule;

import by.school.entity.Clazz;
import by.school.entity.SubjectInSchedule;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class SubjectInScheduleSpecificationByClass extends SubjectInScheduleSpecification {
    private Clazz clazz;

    public SubjectInScheduleSpecificationByClass(Clazz clazz) {
        this.clazz = clazz;
    }
    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("clazz",clazz);
    }

    ///
    @Override
    public boolean specified(SubjectInSchedule subjectInSchedule) {
        return subjectInSchedule.getClazzByClassId() == clazz ;
    }
}
