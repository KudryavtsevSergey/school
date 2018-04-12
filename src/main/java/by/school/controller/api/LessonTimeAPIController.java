package by.school.controller.api;

import by.school.entity.LessonTime;
import by.school.service.ILessonTimeService;
import by.school.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class LessonTimeAPIController {

    private final ILessonTimeService service;

    @Autowired
    public LessonTimeAPIController(@Qualifier("LessonTimeService") ILessonTimeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/lessonTime/", method = RequestMethod.GET)
    public ResponseEntity<List<LessonTime>> getAll(HttpServletRequest req) {
        List<LessonTime> lessonTimes = null;

        try {
            lessonTimes = service.getLessonTimeList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<LessonTime>>(lessonTimes, HttpStatus.OK);
    }

//------------------- Create a lessonTime --------------------------------------------------------

    @RequestMapping(value = "/lessonTime/", method = RequestMethod.POST)
    public ResponseEntity<Void> createLessonTime(@RequestBody LessonTime lessonTime, UriComponentsBuilder ucBuilder) {

        try {

            this.service.create(lessonTime);

        } catch (ServiceException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/lessonTime/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateLessonTime(@PathVariable("id") long id, @RequestBody LessonTime lessonTime) {


        try {

            this.service.update(lessonTime);

        } catch (ServiceException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/lessonTime/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLessonTime(@PathVariable("id") int id) {

        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
