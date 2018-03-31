package by.school.service;


import by.school.entity.Clazz;
import by.school.service.exception.ServiceException;

public interface IClassService extends IService<Clazz> {

    Clazz getOne(int classId) throws ServiceException;
}
