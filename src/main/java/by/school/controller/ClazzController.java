package by.school.controller;

import by.school.entity.Clazz;
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

import java.util.List;

@Controller
public class ClazzController {

    private final IService<Clazz> service;

    @Autowired
    public ClazzController(@Qualifier("ClazzService") IService<Clazz> service) {
        this.service = service;
    }

    @RequestMapping("/clazzes")
    public String list(Model model) {
        model.addAttribute("clazz", new Clazz());
        try {

            model.addAttribute("clazzes", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "clazzes";
    }

    @RequestMapping(value = "/clazzes/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("clazz") Clazz clazz) {
        try {

            if (clazz.getClassId() == 0) {
                this.service.create(clazz);
            } else {
                this.service.update(clazz);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/clazzes";
    }

    @RequestMapping("/clazzes/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/clazzes";
    }

    @RequestMapping("/clazzes/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("clazz", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "clazzes";
    }
}