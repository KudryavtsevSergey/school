package by.school.controller;

import by.school.entity.Teacher;
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
public class TeacherController {
    private final IService<Teacher> service;

    @Autowired
    public TeacherController(@Qualifier("TeacherService") IService<Teacher> service) {
        this.service = service;
    }

    @RequestMapping("/teachers")
    public String list(Model model) {
        model.addAttribute("teacher", new Teacher());
        try {

            model.addAttribute("teachers", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "teachers";
    }

    @RequestMapping(value = "/teachers/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("teacher") Teacher teacher) {
        try {

            if (teacher.getTeacherId() == 0) {
                this.service.create(teacher);
            } else {
                this.service.update(teacher);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/teachers";
    }

    @RequestMapping("/teachers/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/teachers";
    }

    @RequestMapping("/teachers/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("teacher", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "teachers";
    }
}
