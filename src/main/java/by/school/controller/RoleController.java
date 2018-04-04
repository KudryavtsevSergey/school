package by.school.controller;

import by.school.entity.Role;
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
public class RoleController {
    private final IService<Role> service;

    @Autowired
    public RoleController(@Qualifier("RoleService") IService<Role> service) {
        this.service = service;
    }

    @RequestMapping("/roles")
    public String list(Model model) {
        model.addAttribute("role", new Role());
        try {

            model.addAttribute("roles", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "roles";
    }

    @RequestMapping(value = "/roles/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("role") Role role) {
        try {

            if (role.getRoleId() == 0) {
                this.service.create(role);
            } else {
                this.service.update(role);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/roles";
    }

    @RequestMapping("/roles/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/roles";
    }

    @RequestMapping("/roles/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("role", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "roles";
    }
}
