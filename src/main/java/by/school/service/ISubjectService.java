package by.school.service;

import by.school.entity.Subject;
import by.school.service.exception.ServiceException;

public interface ISubjectService extends IService<Subject> {

    Subject getOne(int subjectId) throws ServiceException;
}
