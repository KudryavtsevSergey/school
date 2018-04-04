package by.school.controller;

import by.school.entity.util.UserAuthInfo;
import by.school.service.IAuthService;
import by.school.service.exception.AuthException;
import by.school.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = "userAuthInfo")
public class AuthAPIController {

    private final IAuthService authService;

    @Autowired
    public AuthAPIController(@Qualifier("AuthService") IAuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main(@ModelAttribute("userAuthInfo") UserAuthInfo user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userAuthInfo", new UserAuthInfo());
        modelAndView.setViewName("main");

        return modelAndView;
    }


    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userAuthInfo", new UserAuthInfo());
        return "registration";
    }

    @RequestMapping(value = "/welcome.html")
    public String init(Model model) {
        model.addAttribute("userAuthInfo", new UserAuthInfo());
        return "registration";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView main(@ModelAttribute("userAuthInfo") UserAuthInfo user, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return new ModelAndView("main", "userAuthInfo", new UserAuthInfo());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute("userAuthInfo") UserAuthInfo user) {
        ModelAndView modelAndView = new ModelAndView();
        int roleLevel=0;
        //ResponseEntity resultResponse = null;St
        String errorMessage="";
        try {
            user.setLevel(authService.login(user.getUsername(), user.getPassword()));
            modelAndView.addObject("userAuthInfo", user);
            modelAndView.setViewName("main");
            return modelAndView;
           // resultResponse = new ResponseEntity(roleLevel, HttpStatus.OK);
        } catch (AuthException | ServiceException exc) {
           errorMessage=exc.getMessage()+":(";
           // resultResponse = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (Exception exc) {
            errorMessage=exc.getMessage();
            //resultResponse = new ResponseEntity(new ErrorObject("Some critical error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modelAndView.setViewName("registration");
        modelAndView.addObject("errorMessage", errorMessage);
      //  modelAndView.addObject("resultResponse", resultResponse);
        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }

}
