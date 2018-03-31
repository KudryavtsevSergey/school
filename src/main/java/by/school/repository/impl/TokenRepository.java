package by.school.repository.impl;

import by.school.entity.Token;
import by.school.repository.RepositoryAbstractClass;
import by.school.repository.exception.RepositoryException;
import by.school.repository.specification.HibernateSpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("TokenRepository")
public class TokenRepository extends RepositoryAbstractClass<Token> {
    @Override
    public Token create(Token token, Session session) throws RepositoryException {
        session.save(token);
        return token;
    }

    @Override
    public Token update(Token token, Session session) throws RepositoryException {
        session.update(token);
        return token;
    }

    @Override
    public Token delete(Token token, Session session) throws RepositoryException {
        session.delete(token);
        return token;
    }

    @Override
    public List<Token> query(HibernateSpecification specification, Session session) throws RepositoryException {
        Criteria criteria =  session.createCriteria(Token.class);
        Criterion criterion;
        if((specification != null) && ((criterion = specification.toCriteria()) != null)){
            criteria.add(criterion);
        }
        return criteria.list();
    }

    @Override
    public Token get(int id, Session session) throws RepositoryException {
        return null;
    }
}
