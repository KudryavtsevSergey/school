package by.school.service.document.generation;

import by.school.service.exception.ServiceException;

import java.io.OutputStream;

public interface IGenerationService {
    OutputStream generateClassPupilListDocument(OutputStream os, DocumentType documentType, int classId) throws ServiceException;
    OutputStream generateTeacherScheduleDocument(OutputStream os, DocumentType documentType, int teacherId) throws ServiceException;
    OutputStream generateClassScheduleDocument(OutputStream os, DocumentType documentType, int classId) throws ServiceException;
    OutputStream generateFullScheduleDocument(OutputStream os, DocumentType documentType) throws ServiceException;
    OutputStream generateMarksDocument(OutputStream os, DocumentType documentType, int subjectId, int classId) throws ServiceException;
}
