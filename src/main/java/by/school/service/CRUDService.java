package by.school.service;


import by.school.repository.IRepository;
import by.school.repository.exception.RepositoryException;
import by.school.service.exception.ServiceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class CRUDService<T> extends ServiceAbstractClass {
    protected IRepository<T> repository;

    public T create(T obj) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            obj = repository.create(obj, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return obj;
    }


    public T update(T obj) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            obj = repository.update(obj, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return obj;
    }

    public void delete(T obj) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            repository.delete(obj, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
    }

    public List<T> read() throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<T> list;
        try {
            list = repository.query(null, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return list;
    }

    public T getOne(int id) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T t ;
        try {
            t = repository.get(id,session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return t;
    }
}