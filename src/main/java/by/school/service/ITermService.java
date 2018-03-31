package by.school.service;


import by.school.entity.Term;
import by.school.service.exception.ServiceException;

import java.util.List;

public interface ITermService extends IService<Term> {
    Term getCurrentTerm() throws ServiceException;
    Term update(Term term) throws ServiceException;
    List<Term> read() throws ServiceException;
}
