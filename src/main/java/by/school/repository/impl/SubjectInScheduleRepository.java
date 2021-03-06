package by.school.repository.impl;

import by.school.entity.SubjectInSchedule;
import by.school.repository.RepositoryAbstractClass;
import by.school.repository.exception.RepositoryException;
import by.school.repository.specification.HibernateSpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("SubjectInScheduleRepository")
public class SubjectInScheduleRepository extends RepositoryAbstractClass<SubjectInSchedule> {
    @Override
    public SubjectInSchedule create(SubjectInSchedule subjectInSchedule, Session session) throws RepositoryException {
        session.save(subjectInSchedule);
        return subjectInSchedule;
    }

    @Override
    public SubjectInSchedule update(SubjectInSchedule subjectInSchedule, Session session) throws RepositoryException {
        session.update(subjectInSchedule);
        return subjectInSchedule;
    }

    @Override
    public SubjectInSchedule delete(SubjectInSchedule subjectInSchedule, Session session) throws RepositoryException {
        session.delete(subjectInSchedule);
        return subjectInSchedule;
    }

    @Override
    public List<SubjectInSchedule> query(HibernateSpecification specification, Session session) throws RepositoryException {
        Criteria criteria =  session.createCriteria(SubjectInSchedule.class);
        Criterion criterion;
        if((specification != null) && ((criterion = specification.toCriteria()) != null)){
            criteria.add(criterion);
        }
        return criteria.list();
    }

    @Override
    public SubjectInSchedule get(int id, Session session) throws RepositoryException {
        SubjectInSchedule subject = (SubjectInSchedule) session.get(SubjectInSchedule.class, id);
        if (subject == null) throw new RepositoryException("Subject In Schedule not found");
        return subject;
    }
}
