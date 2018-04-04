package by.school.controller;

import by.school.entity.LessonTime;
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
public class LessonTimeController {

    private final IService<LessonTime> service;

    @Autowired
    public LessonTimeController(@Qualifier("LessonTimeService") IService<LessonTime> service) {
        this.service = service;
    }

    @RequestMapping("/lessonTimes")
    public String list(Model model) {
        model.addAttribute("lessonTime", new LessonTime());
        try {

            model.addAttribute("lessonTimes", this.service.read());

        } catch (ServiceException e) {
            //error get list
        }
        return "lessonTimes";
    }

    @RequestMapping(value = "/lessonTimes/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("lessonTime") LessonTime lessonTime) {
        try {

            if (lessonTime.getLessonId() == 0) {
                this.service.create(lessonTime);
            } else {
                this.service.update(lessonTime);
            }

        } catch (ServiceException e) {
            //error added
        }
        return "redirect:/lessonTimes";
    }

    @RequestMapping("/lessonTimes/remove/{id}")
    public String remove(@PathVariable("id") int id){
        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
        }
        return "redirect:/lessonTimes";
    }

    @RequestMapping("/lessonTimes/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        try {

            model.addAttribute("type", "edit");
            model.addAttribute("lessonTime", this.service.getOne(id));

        } catch (ServiceException e) {
            //error get one
        }
        return "lessonTimes";
    }
}
