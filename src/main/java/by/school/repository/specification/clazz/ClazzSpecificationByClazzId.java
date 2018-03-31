package by.school.repository.specification.clazz;

import by.school.entity.Clazz;
import by.school.repository.specification.Specification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ClazzSpecificationByClazzId extends Specification<Clazz> {

    private int classId;

    public ClazzSpecificationByClazzId(int classId) {
        this.classId = classId;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("classId", this.classId);
    }

    @Override
    public boolean specified(Clazz clazz) {
        return clazz.getClassId()==classId;
    }
}
