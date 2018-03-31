package by.school.service.impl;

import by.school.controller.exception.ExceptionEnum;
import by.school.entity.*;
import by.school.repository.IRepository;
import by.school.repository.exception.RepositoryException;
import by.school.service.CRUDService;
import by.school.service.IUserService;
import by.school.service.exception.ServiceException;
import by.school.utils.MD5Generator;
import by.school.utils.exception.ValidationException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.school.utils.ValidateServiceUtils.validateString;


@Service("UserService")
public class UserService extends CRUDService<User> implements IUserService {
    private static final int USER_ROLE = 1;
    private static final int PUPIL_ROLE = 2;
    private static final int DIRECTOR_ROLE = 5;
    private static final int TEACHER_ROLE = 3;
    private static final int DIRECTOR_OF_STUDIES_ROLE = 4;
    private static final int ADMIN_ROLE = 6;

  //  @Autowired
    //@Qualifier("MailService")
    //private MailService mailService;

    @Autowired
    public UserService(@Qualifier("UserRepository") IRepository<User> repository, @Qualifier("TokenRepository") IRepository<Token> tokenRepository) {
        this.repository = repository;
    }

    private void checkPassword(User newUser, User user) throws ServiceException {
        String password = newUser.getPassHash();
        if(password == null) return;
        if (password.length() < 6) {
            throw new ClassifiedServiceException(ExceptionEnum.password_is_small);
        }
        user.setPassHash(MD5Generator.generate(password));
    }

    @Override
    public User create(User user) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            validateString(user.getUsername(), "Username");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.username_not_valid);
        }

            validateEmail(user.getEmail());

        try {
            user.setLocked((byte) 0);
            user.setRoleByRoleId((Role)session.get(Role.class, USER_ROLE));
            String password = generateNewPassword();
            user.setPassHash(MD5Generator.generate(password));
            repository.create(user, session);
            transaction.commit();
            //mailService.sendMail(user.getEmail(), password);
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return user;
    }

    private void validateEmail(String email) {
    }

    @Override
    public User changeRole(int userId, int roleId) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = changeRole(userId, roleId, session);
        try {
            repository.update(user, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return user;
    }

    private void checkPassword(String password) throws ServiceException {
        if (password == null) {
            throw new ClassifiedServiceException(ExceptionEnum.password_is_null);
        }
        if (password.length() < 6) {
            throw new ClassifiedServiceException(ExceptionEnum.password_is_small);
        }
    }

    @Override
    public User changePassword(int userId, String password) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, userId);
        if (user == null) {
            throw new ClassifiedServiceException(ExceptionEnum.user_not_found);
        }
        checkPassword(password);
        user.setPassHash(MD5Generator.generate(password));
        try {
            repository.update(user, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return user;
    }

    private void deleteTeacherIfExist(int teacherId, Session session) {
        Teacher teacher = (Teacher) session.get(Teacher.class, teacherId);
        if(teacher != null) {
            session.delete(teacher);
        }
    }

    private void deletePupilIfExist(int pupilId, Session session) {
        Pupil pupil = (Pupil) session.get(Pupil.class, pupilId);
        if(pupil != null) {
            session.delete(pupil);
        }
    }

    @Override
    public User changeRole(int userId, int roleId, Session session) throws ServiceException {
        User user = (User) session.get(User.class, userId);
        if(user == null) {
            throw new ClassifiedServiceException(ExceptionEnum.user_not_found);
        }
        Role role = (Role) session.get(Role.class, roleId);
        if(role == null) {
            throw new ClassifiedServiceException(ExceptionEnum.role_not_found);
        }
        if(roleId == PUPIL_ROLE) {
            deleteTeacherIfExist(userId, session);
        } else if((roleId == TEACHER_ROLE) || (roleId == DIRECTOR_OF_STUDIES_ROLE) || (roleId == DIRECTOR_ROLE)) {
            deletePupilIfExist(userId, session);
        } else {
            deleteTeacherIfExist(userId, session);
            deletePupilIfExist(userId, session);
        }
        user.setRoleByRoleId(role);
        return user;
    }

    private String generateNewPassword() {
        return MD5Generator.generate(((Long)(System.currentTimeMillis() % 1000)).toString());
    }

    private void checkNewRole(User newUser, User user, Session session) throws ServiceException {
        Integer roleId = newUser.getRoleId();
        if(roleId == null) return;
        try {
            Role role = (Role) session.load(Role.class, roleId);
            user.setRoleByRoleId(role);
        } catch (ObjectNotFoundException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.role_not_found);
        }
    }

    private void checkLockedStatus(User newUser, User user) {
        Byte lockedStatus = newUser.getLocked();
        if(lockedStatus == null) return;
        if(lockedStatus >= 0) {
            user.setLocked((byte)1);
        } else {
            user.setLocked((byte)0);
        }
    }

    private int checkId(Integer id) throws ServiceException {
        if(id == null) {
            throw new ClassifiedServiceException(ExceptionEnum.wrong_id_number);
        }
        return id.intValue();
    }

    @Override
    public User update(User newUser) throws ServiceException {
        User user;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            user = repository.get(checkId(newUser.getUserId()), session);
            checkPassword(newUser, user);
            checkNewRole(newUser, user, session);
            checkLockedStatus(newUser, user);
            repository.update(user, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public void delete(int id) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = (User)session.load(User.class, id);
            deletePupilIfExist(user.getUserId(), session);
            deleteTeacherIfExist(user.getUserId(), session);
            repository.delete(user, session);
            transaction.commit();
        } catch (ObjectNotFoundException | RepositoryException exc) {
            transaction.rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> read() throws ServiceException {
        return super.read();
    }
}
