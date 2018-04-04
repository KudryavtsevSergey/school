package by.school.controller;

import by.school.entity.Mark;
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
public class MarkController {

    private final IService<Mark> service;

    @Autowired
    public MarkController(@Qualifier("MarkService") IService<Mark> service) {
        this.service = service;
    }

    @RequestMapping("/marks")
    public String list(Model model) {
        model.addAttribute("mark", new Mark());
        try {

            model.addAttribute("marks", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "marks";
    }

    @RequestMapping(value = "/marks/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("mark") Mark mark) {
        try {

            if (mark.getMarkId() == 0) {
                this.service.create(mark);
            } else {
                this.service.update(mark);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/marks";
    }

    @RequestMapping("/marks/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/marks";
    }

    @RequestMapping("/marks/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("mark", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "marks";
    }
}