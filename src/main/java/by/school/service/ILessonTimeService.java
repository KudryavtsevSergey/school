package by.school.service;


import by.school.entity.LessonTime;
import by.school.service.exception.ServiceException;

import java.util.List;

public interface ILessonTimeService extends IService<LessonTime> {
    List<LessonTime> getLessonTimeList() throws ServiceException;
    LessonTime update(LessonTime lesson) throws ServiceException;
}
