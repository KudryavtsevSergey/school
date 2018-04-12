package by.school.controller.api;

import by.school.entity.Term;
import by.school.service.ITermService;
import by.school.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class TermAPIController {

    private final ITermService service;

    @Autowired
    public TermAPIController(@Qualifier("TermService") ITermService service) {
        this.service = service;
    }

    @RequestMapping(value = "/Term/", method = RequestMethod.GET)
    public ResponseEntity<List<Term>> getAll(HttpServletRequest req) {
        List<Term> Terms = null;

        try {
            Terms = service.read();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<Term>>(Terms, HttpStatus.OK);
    }

//------------------- Create a Term --------------------------------------------------------

    @RequestMapping(value = "/Term/", method = RequestMethod.POST)
    public ResponseEntity<Void> createTerm(@RequestBody Term Term, UriComponentsBuilder ucBuilder) {

        try {

            this.service.create(Term);

        } catch (ServiceException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/Term/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateTerm(@PathVariable("id") long id, @RequestBody Term Term) {


        try {

            this.service.update(Term);

        } catch (ServiceException e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/Term/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTerm(@PathVariable("id") int id) {

        try {

            this.service.delete(id);

        } catch (ServiceException e) {
            // error remove
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
