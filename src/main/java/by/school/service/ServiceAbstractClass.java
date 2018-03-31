package by.school.service;

import by.school.persistence.HibernateUtil;
import org.hibernate.SessionFactory;

public abstract class ServiceAbstractClass {
    public SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
}
