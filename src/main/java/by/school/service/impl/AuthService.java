package by.school.service.impl;

import by.school.entity.Role;
import by.school.entity.Token;
import by.school.entity.User;
import by.school.repository.IRepository;
import by.school.repository.exception.RepositoryException;
import by.school.repository.specification.user.UserSpecificationByUsername;
import by.school.service.IAuthService;
import by.school.service.ServiceAbstractClass;
import by.school.service.exception.AuthException;
import by.school.service.exception.ServiceException;
import by.school.utils.MD5Generator;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//import by.school.utils.MD5Generator;
//import com.auth0.jwt.JWT;

@Service(value = "AuthService")
public class AuthService extends ServiceAbstractClass implements IAuthService {
    private final String SECRET = "simple_secret_string";


    private final IRepository<User> userRepository;

    private final IRepository<Token> tokenRepository;

    private final IRepository<Role> roleRepository;

    @Autowired
    public AuthService(@Qualifier("UserRepository") IRepository<User> userRepository, @Qualifier("RoleRepository") IRepository<Role> roleRepository, @Qualifier("TokenRepository") IRepository<Token> tokenRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }


    /**
     * Returns token string value.
     */
    @Override
    public int login(String username, String password) throws AuthException {
        List<User> users;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            users = userRepository.query(new UserSpecificationByUsername(username), session);
            if (users.size() > 0) {
                User user = users.get(0);
              //  if (user.getLocked() != 0) {
                 //   throw new AuthException("User is blocked");
              //  }
                if (verifyPasswords(user.getPassHash(), password)) {
                        session.getTransaction().commit();
                        return user.getRoleByRoleId().getLevel();

                } else {
                    throw new AuthException("Password is incorrect");
                }
            } else {
                throw new AuthException("User not found");
            }
        } catch (RepositoryException exc) {
            session.getTransaction().rollback();
        }
        return 0;
    }

    private boolean verifyPasswords(String hash, String pass) {
        return hash.equals(MD5Generator.generate(pass));
    }

    @Override
    public void logout(User user) throws AuthException, ServiceException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Token token = new Token();
            token.setMasterId(user.getUserId());
            token.setActive((byte) 0);
            token.setValue("");
            tokenRepository.update(token, session);
            session.getTransaction().commit();
        } catch (RepositoryException exc) {
            session.getTransaction().rollback();
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
    }

    /**
     * If token is valid returns user object, in another case returns null.
     */
    @Override
    public User checkToken(String token) throws AuthException, ServiceException {
//        if ((token == null) || (token.isEmpty())) {
//            throw new AuthException("Token is incorrect");
//        }
//        JWT jwt;
//        jwt = JWT.decode(token);
//
//
//        String username = jwt.getSubject();
//        if (username != null) {
//            User user;
//            Session session = null;
//            try {
//                session = sessionFactory.openSession();
//                session.beginTransaction();
//                List<User> users = userRepository.query(new UserSpecificationByUsername(username), session);
//                if (users.size() > 0) {
//                    user = users.get(0);
//                    Token tokenObj = session.get(Token.class, user.getUserId());
//                    if (tokenObj.getActive() == 0) {
//                        throw new AuthException("Token is not active");
//                    }
////                        if(user.getLocked() != 0) {
////                            throw new AuthException("User is blocked");
////                        }
//                    return user;
//                } else {
//                    throw new AuthException("Logout error");
//                }
//            } catch (RepositoryException exc) {
//
//            } finally {
//                session.getTransaction().commit();
//                session.close();
//            }
//        }

        return null;
    }
}
