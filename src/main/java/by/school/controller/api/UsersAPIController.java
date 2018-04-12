package by.school.controller.api;

import by.school.entity.User;
import by.school.service.IUserService;
import by.school.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UsersAPIController{

    private final IUserService userService;

    @Autowired
    public UsersAPIController(@Qualifier("UserService") IUserService userService) {
        this.userService = userService;
    }

    /*
    @PutMapping(value = "/changeRole", params = {"userId", "roleId"})
    @ResponseBody
    public ResponseEntity changeRole(HttpServletRequest req, @RequestParam("userId") int userId, @RequestParam("roleId") int roleId) {
        return doResponse(userService::changeRole, userId, roleId, "Can't change role of user", LOGGER, false);
    }*/

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll(HttpServletRequest req) {
        List<User> users = null;
        try {
            users = userService.read();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return users;
    }

    /*
    @PostMapping
    @ResponseBody
    public ResponseEntity create(HttpServletRequest req, @RequestBody User user) {
        return createOrUpdate(userService::create, user, "Can't create user", LOGGER);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity update(HttpServletRequest req, @RequestBody User user) {
        return createOrUpdate(userService::update, user, "Can't update user", LOGGER);
    }

    @DeleteMapping(value = "/{userId}")
    @ResponseBody
    public ResponseEntity delete(HttpServletRequest req, @PathVariable("userId") int userId) {
        return delete(userService::delete, userId, "Can't create user", LOGGER);
    }

    @GetMapping(value = "/{userId}")
    @ResponseBody
    public ResponseEntity getOne(HttpServletRequest req, @PathVariable("userId") int userId) {
        return getOne(userService::getOne, userId, "Can't get user", LOGGER);
    }

    @PutMapping(value = "/{userId}", params = {"password"})
    @ResponseBody
    public ResponseEntity changePassword(HttpServletRequest req, @PathVariable("userId") int userId,
                                         @RequestParam("password") String password) {
        return doResponse(userService::changePassword, userId, password, "Can't change password of user", LOGGER);
    }*/
}
