package by.school.service;

import by.school.entity.SubjectInSchedule;
import by.school.service.exception.ServiceException;

import java.util.List;

public interface ISubjectInScheduleService extends IService<SubjectInSchedule> {
    List<SubjectInSchedule> getPupilSchedule(int id) throws ServiceException;
    List<SubjectInSchedule> getTeacherSchedule(int teacherId) throws ServiceException;
    List<SubjectInSchedule> getSubjectsWithTeacherClassSubject(int classId, int teacherId, int subjectId)
            throws ServiceException;
}
