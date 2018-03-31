package by.school.service;


import by.school.entity.User;
import by.school.service.exception.AuthException;
import by.school.service.exception.ServiceException;

public interface IAuthService {
    int login(String username, String password) throws AuthException, ServiceException;

    void logout(User user) throws AuthException, ServiceException;

    User checkToken(String token) throws AuthException, ServiceException;
}
