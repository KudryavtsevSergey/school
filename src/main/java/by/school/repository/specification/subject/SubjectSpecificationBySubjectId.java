package by.school.repository.specification.subject;

import by.school.entity.Subject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;


public class SubjectSpecificationBySubjectId extends SubjectSpecification {

    private int subjectId;

    public SubjectSpecificationBySubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("subjectId", this.subjectId);
    }

    @Override
    public boolean specified(Subject subject) {
        return subject.getSubjectId() == subjectId;
    }

}
