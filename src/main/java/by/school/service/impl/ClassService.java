package by.school.service.impl;

import by.school.controller.exception.ExceptionEnum;
import by.school.entity.Clazz;
import by.school.repository.IRepository;
import by.school.repository.exception.RepositoryException;
import by.school.service.CRUDService;
import by.school.service.IClassService;
import by.school.service.exception.ServiceException;
import by.school.utils.exception.ValidationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.school.utils.ValidateServiceUtils.validateId;
import static by.school.utils.ValidateServiceUtils.validateString;


@Service("ClazzService")
public class ClassService extends CRUDService<Clazz> implements IClassService {

    @Autowired
    public ClassService(@Qualifier("ClassRepository") IRepository<Clazz> repository) {
        this.repository = repository;
    }

    @Override
    public List<Clazz> read() throws ServiceException {
        return super.read();
    }

    @Override
    public Clazz create(Clazz clazz) throws ServiceException {
        validateClassNumber(clazz.getNumber());
        checkClassBeforeCreate(clazz);

        return super.create(clazz);
    }

    @Override
    public Clazz update(Clazz clazz) throws ServiceException {
        Session session = sessionFactory.openSession();
        prepareClassBeforeUpdate(clazz, session);

        return super.update(clazz);
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            validateId(id, "Class");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.class_not_found);
        }
        Clazz clazz = new Clazz();
        clazz.setClassId(id);
        super.delete(clazz);
    }


    @Override
    public Clazz getOne(int id) throws ServiceException {
        try {
            validateId(id, "Class");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.class_not_found);
        }
        return super.getOne(id);
    }

    private void checkClassBeforeCreate(Clazz clazz) throws ServiceException {
        try {
            validateString(clazz.getLetterMark(), "Letter Mark");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.invalid_class_letter);
        }
        validateClassNumber(clazz.getNumber());
    }

    private Clazz prepareClassBeforeUpdate(Clazz newClazz, Session session) throws ServiceException {
        Clazz clazz;
        try {
            clazz = repository.get(newClazz.getClassId(), session);
            validateClass(clazz);
            checkClassNumber(clazz, newClazz.getNumber());
            checkLetterMark(clazz, newClazz.getLetterMark());
        } catch (RepositoryException exc) {
            throw new ServiceException(exc);
        }
        return clazz;
    }

    private void checkLetterMark(Clazz clazz, String letterMark) {
        if (letterMark == null) return;
        try {
            validateLetterMark(letterMark);
            clazz.setLetterMark(letterMark);
        } catch (ValidationException exc) {

        }
    }

    private void validateLetterMark(String string) throws ValidationException {
        validateString(string, "Letter Mark");
    }

    private void checkClassNumber(Clazz clazz, Integer number) {
        if (number == null) return;
        try {
            validateClassNumber(number);
            clazz.setNumber(number);
        } catch (ServiceException exc) {

        }
    }

    private void validateClassNumber(int number) throws ServiceException {
        if (number <= 0 || number >= 12)
            throw new ClassifiedServiceException(ExceptionEnum.invalid_class_number);
    }

    private void validateClass(Clazz clazz) throws ServiceException {
        if (clazz == null) {
            throw new ClassifiedServiceException(ExceptionEnum.invalid_class_letter);
        }
    }
}
