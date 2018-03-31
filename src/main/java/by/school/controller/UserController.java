package by.school.controller;

import by.school.entity.User;
import by.school.service.IService;
import by.school.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private final IService<User> service;

    @Autowired
    public UserController(@Qualifier("UserService") IService<User> service) {
        this.service = service;
    }

    @RequestMapping("/users")
    public String list(Model model) {
        model.addAttribute("user", new User());
        try {

            model.addAttribute("users", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User user) {
        try {

            if (user.getUserId() == 0) {
                this.service.create(user);
            } else {
                this.service.update(user);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/users";
    }

    @RequestMapping("/users/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/users";
    }

    @RequestMapping("/users/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("user", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "users";
    }
}
