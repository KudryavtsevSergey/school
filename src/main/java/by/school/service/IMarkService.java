package by.school.service;

import by.school.entity.Mark;
import by.school.service.exception.ServiceException;

import java.util.List;

public interface IMarkService extends IService<Mark> {

    Mark getOne(int id) throws ServiceException;
    List<Mark> getMarksForSubjectInClass(int subjectId, int classId, int termId) throws ServiceException;
    List<Mark> getMarksForTermOrderInClass(int classId, int termId) throws ServiceException;
    List<Mark> getMarksForPupil(int pupilId, int termId) throws ServiceException;
}
