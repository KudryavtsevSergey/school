package by.school.repository.impl;

import by.school.entity.Clazz;
import by.school.repository.RepositoryAbstractClass;
import by.school.repository.exception.RepositoryException;
import by.school.repository.specification.HibernateSpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ClassRepository")
public class ClazzRepository extends RepositoryAbstractClass<Clazz> {

    @Override
    public Clazz create(Clazz clazz, Session session) throws RepositoryException {
        session.save(clazz);
        return clazz;
    }

    @Override
    public Clazz update(Clazz clazz, Session session) throws RepositoryException {
//        session.update(clazz);
        return (Clazz) session.merge(clazz);
    }

    @Override
    public Clazz delete(Clazz clazz, Session session) throws RepositoryException {
        session.delete(clazz);
        return clazz;
    }

    @Override
    public List<Clazz> query(HibernateSpecification specification, Session session) throws RepositoryException {
        Criteria criteria =  session.createCriteria(Clazz.class);
        Criterion criterion;
        if((specification != null) && ((criterion = specification.toCriteria()) != null)){
            criteria.add(criterion);
        }
        criteria.addOrder(Order.asc("number")).addOrder(Order.asc("letterMark"));
        return criteria.list();
    }

    @Override
    public Clazz get(int id, Session session) throws RepositoryException {
        Clazz clazz = session.get(Clazz.class, id);
        if (clazz == null) throw new RepositoryException("Class not found");
        return clazz;
    }
}