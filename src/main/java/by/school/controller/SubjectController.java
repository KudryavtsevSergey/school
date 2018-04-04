package by.school.controller;

import by.school.entity.Subject;
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
public class SubjectController {
    private final IService<Subject> service;

    @Autowired
    public SubjectController(@Qualifier("SubjectService") IService<Subject> service) {
        this.service = service;
    }

    @RequestMapping("/subjects")
    public String list(Model model) {
        model.addAttribute("subject", new Subject());
        try {

            model.addAttribute("subjects", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "subjects";
    }

    @RequestMapping(value = "/subjects/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("subject") Subject subject) {
        try {

            if (subject.getSubjectId() == 0) {
                this.service.create(subject);
            } else {
                this.service.update(subject);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/subjects";
    }

    @RequestMapping("/subjects/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/subjects";
    }

    @RequestMapping("/subjects/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("subject", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "subjects";
    }
}