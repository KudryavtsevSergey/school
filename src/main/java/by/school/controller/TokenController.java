package by.school.controller;

import by.school.entity.Token;
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
public class TokenController {
    /*
    private final IService<Token> service;

    @Autowired
    public TokenController(@Qualifier("TokenService") IService<Token> service) {
        this.service = service;
    }

    @RequestMapping("/tokens")
    public String list(Model model) {
        model.addAttribute("token", new Token());
        try {

            model.addAttribute("tokens", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "tokens";
    }

    @RequestMapping(value = "/tokens/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("token") Token token) {
        try {

            if (token.getMasterId() == 0) {
                this.service.create(token);
            } else {
                this.service.update(token);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/tokens";
    }

    @RequestMapping("/tokens/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/tokens";
    }

    @RequestMapping("/tokens/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("token", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "tokens";
    }*/
}
