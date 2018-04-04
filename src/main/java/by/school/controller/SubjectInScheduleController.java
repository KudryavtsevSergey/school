package by.school.controller;

import by.school.entity.SubjectInSchedule;
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
public class SubjectInScheduleController {
    private final IService<SubjectInSchedule> service;

    @Autowired
    public SubjectInScheduleController(@Qualifier("SubjectInScheduleService") IService<SubjectInSchedule> service) {
        this.service = service;
    }

    @RequestMapping("/subjectInSchedules")
    public String list(Model model) {
        model.addAttribute("subjectInSchedule", new SubjectInSchedule());
        try {

            model.addAttribute("subjectInSchedules", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "subjectInSchedules";
    }

    @RequestMapping(value = "/subjectInSchedules/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("subjectInSchedule") SubjectInSchedule subjectInSchedule) {
        try {

            if (subjectInSchedule.getSubectInScheduleId() == 0) {
                this.service.create(subjectInSchedule);
            } else {
                this.service.update(subjectInSchedule);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/subjectInSchedules";
    }

    @RequestMapping("/subjectInSchedules/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/subjectInSchedules";
    }

    @RequestMapping("/subjectInSchedules/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("subjectInSchedule", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "subjectInSchedules";
    }
}
