package by.school.repository.specification.pupil;

import by.school.entity.Pupil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class PupilSpecificationByClassId extends PupilSpecification {

    private int classId;

    public PupilSpecificationByClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("classId", classId);
    }

    @Override
    public boolean specified(Pupil pupil) {
        return pupil.getClassId() == classId;
    }
}
