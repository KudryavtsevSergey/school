package by.school.repository.impl;

import by.school.entity.LessonTime;
import by.school.repository.RepositoryAbstractClass;
import by.school.repository.exception.RepositoryException;
import by.school.repository.specification.HibernateSpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LessonTimeRepository")
public class LessonTimeRepository extends RepositoryAbstractClass<LessonTime> {

    @Override
    public LessonTime create(LessonTime lessonTime, Session session) throws RepositoryException {
        return (LessonTime) session.get(LessonTime.class, session.save(lessonTime));
    }

    @Override
    public LessonTime update(LessonTime lessonTime, Session session) throws RepositoryException {
        return (LessonTime)session.merge(lessonTime);
    }

    @Override
    public LessonTime delete(LessonTime lessonTime, Session session) throws RepositoryException {
        session.delete(lessonTime);
        return lessonTime;
    }

    @Override
    public List<LessonTime> query(HibernateSpecification specification, Session session) throws RepositoryException {
        Criteria criteria =  session.createCriteria(LessonTime.class);
        Criterion criterion;
        if((specification != null) && ((criterion = specification.toCriteria()) != null)){
            criteria.add(criterion);
        }
        criteria.addOrder(Order.asc("number")).addOrder(Order.asc("startTime"));
        return criteria.list();
    }

    @Override
    public LessonTime get(int id, Session session) throws RepositoryException {
        LessonTime lessonTime = (LessonTime) session.get(LessonTime.class, id);
        if (lessonTime == null) throw new RepositoryException("Mark not found");
        return lessonTime;
    }
}
