package by.school.repository.impl;

import by.school.entity.Role;
import by.school.repository.RepositoryAbstractClass;
import by.school.repository.exception.RepositoryException;
import by.school.repository.specification.HibernateSpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("RoleRepository")
public class RoleRepository extends RepositoryAbstractClass<Role> {
    @Override
    public Role create(Role role, Session session) throws RepositoryException {
        session.save(role);
        return role;
    }

    @Override
    public Role update(Role role, Session session) throws RepositoryException {
        session.update(role);
        return role;
    }

    @Override
    public Role delete(Role role, Session session) throws RepositoryException {
        session.delete(role);
        return role;
    }

    @Override
    public List<Role> query(HibernateSpecification specification, Session session) throws RepositoryException {
        Criteria criteria = session.createCriteria(Role.class);
        Criterion criterion;
        if ((specification != null) && ((criterion = specification.toCriteria()) != null)) {
            criteria.add(criterion);
        }
        return criteria.list();
    }

    @Override
    public Role get(int id, Session session) throws RepositoryException {
        Role role = (Role) session.get(Role.class, id);
        if (role == null) throw new RepositoryException("Pupil not found");
        return role;
    }
}
