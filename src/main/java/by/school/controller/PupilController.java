package by.school.controller;

import by.school.entity.Pupil;
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
public class PupilController {
    private final IService<Pupil> service;

    @Autowired
    public PupilController(@Qualifier("PupilService") IService<Pupil> service) {
        this.service = service;
    }

    @RequestMapping("/pupils")
    public String list(Model model) {
        model.addAttribute("pupil", new Pupil());
        try {

            model.addAttribute("pupils", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "pupils";
    }

    @RequestMapping(value = "/pupils/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("pupil") Pupil pupil) {
        try {

            if (pupil.getPupilId() == 0) {
                this.service.create(pupil);
            } else {
                this.service.update(pupil);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/pupils";
    }

    @RequestMapping("/pupils/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/pupils";
    }

    @RequestMapping("/pupils/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("pupil", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "pupils";
    }
}