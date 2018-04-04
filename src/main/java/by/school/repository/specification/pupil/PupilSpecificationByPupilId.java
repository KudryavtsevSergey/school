package by.school.repository.specification.pupil;

import by.school.entity.Pupil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class PupilSpecificationByPupilId extends PupilSpecification {

    private int pupilId;

    public PupilSpecificationByPupilId(int pupilId) {
        this.pupilId = pupilId;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("userId", this.pupilId);
    }
////
    @Override
    public boolean specified(Pupil pupil) {
        return pupil.getUserByPupilId().getUserId() == pupilId;
    }
}
