package by.school.repository.impl;

import by.school.entity.Mark;
import by.school.repository.RepositoryAbstractClass;
import by.school.repository.exception.RepositoryException;
import by.school.repository.specification.HibernateSpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MarkRepository")
public class MarkRepository extends RepositoryAbstractClass<Mark> {
    @Override
    public Mark create(Mark mark, Session session) throws RepositoryException {
        return (Mark) session.get(Mark.class, session.save(mark));
    }

    @Override
    public Mark update(Mark mark, Session session) throws RepositoryException {
        return (Mark)session.merge(mark);
    }

    @Override
    public Mark delete(Mark mark, Session session) throws RepositoryException {
        session.delete(mark);
        return mark;
    }

    @Override
    public List<Mark> query(HibernateSpecification specification, Session session) throws RepositoryException {
        Criteria criteria =  session.createCriteria(Mark.class);
        Criterion criterion;
        if((specification != null) && ((criterion = specification.toCriteria()) != null)){
            criteria.add(criterion);
        }
        criteria.addOrder(Order.asc("pupil")).addOrder(Order.asc("date"));
        return criteria.list();
    }

    @Override
    public Mark get(int id, Session session) throws RepositoryException {
        Mark mark = (Mark) session.get(Mark.class, id);
        if (mark == null) throw new RepositoryException("Mark not found");
        return mark;
    }
}
