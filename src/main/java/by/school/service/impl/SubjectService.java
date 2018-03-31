package by.school.service.impl;

import by.school.controller.exception.ExceptionEnum;
import by.school.entity.Subject;
import by.school.repository.IRepository;
import by.school.service.CRUDService;
import by.school.service.ISubjectService;
import by.school.service.exception.ServiceException;
import by.school.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.school.utils.ValidateServiceUtils.validateId;
import static by.school.utils.ValidateServiceUtils.validateString;

@Service("SubjectService")
public class SubjectService extends CRUDService<Subject> implements ISubjectService {

    @Autowired
    public SubjectService(@Qualifier("SubjectRepository") IRepository<Subject> repository) {
        this.repository = repository;
    }

    @Override
    public Subject create(Subject subject) throws ServiceException {
        try {
            validateString(subject.getName(), "Name");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.subject_has_wrong_name);
        }
        try {
            validateString(subject.getDescription(), "Description");
        } catch (ValidationException exc) {

            throw new ClassifiedServiceException(ExceptionEnum.subject_has_wrong_description);
        }
        return super.create(subject);
    }

    @Override
    public Subject update(Subject subject) throws ServiceException {
        try {
            validateId(subject.getSubjectId(), "Subject");
            validateString(subject.getName(), "Name");
            validateString(subject.getDescription(), "Description");
        } catch (ValidationException exc) {

            throw new ServiceException(exc);
        }
        return super.update(subject);
    }

    @Override
    public void delete(int subjectId) throws ServiceException {
        try {
            validateId(subjectId, "Subject");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.subject_not_found);
        }
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        super.delete(subject);
    }

    @Override
    public List<Subject> read() throws ServiceException {
        return super.read();
    }

    @Override
    public Subject getOne(int subjectId) throws ServiceException {
        try {
            validateId(subjectId, "Subject");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.subject_not_found);
        }
        return super.getOne(subjectId);
    }
}
