package by.school.service;

import by.school.entity.User;
import by.school.service.exception.ServiceException;
import org.hibernate.Session;

public interface IUserService extends IService<User> {
    User changeRole(int userId, int roleId) throws ServiceException;
    User changePassword(int userId, String password) throws ServiceException;
    User changeRole(int userId, int roleId, Session session) throws ServiceException;
    User getOne(int userId) throws ServiceException;
}
