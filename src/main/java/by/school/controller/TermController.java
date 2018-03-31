package by.school.controller;

import by.school.entity.Term;
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
public class TermController {
    private final IService<Term> service;

    @Autowired
    public TermController(@Qualifier("TermService") IService<Term> service) {
        this.service = service;
    }

    @RequestMapping("/terms")
    public String list(Model model) {
        model.addAttribute("term", new Term());
        try {

            model.addAttribute("terms", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "terms";
    }

    @RequestMapping(value = "/terms/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("term") Term term) {
        try {

            if (term.getTermId() == 0) {
                this.service.create(term);
            } else {
                this.service.update(term);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/terms";
    }

    @RequestMapping("/terms/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/terms";
    }

    @RequestMapping("/terms/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("term", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "terms";
    }
}
