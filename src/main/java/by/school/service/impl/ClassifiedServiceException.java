package by.school.service.impl;

import by.school.controller.exception.ExceptionEnum;
import by.school.service.exception.ServiceException;

public class ClassifiedServiceException extends ServiceException {

    private ExceptionEnum exceptionType;

    public ClassifiedServiceException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum + "");
        this.exceptionType = exceptionEnum;
    }

    public ClassifiedServiceException(ExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum + "", cause);
        this.exceptionType = exceptionEnum;
    }

    public ClassifiedServiceException(Throwable cause) {
        super(cause);
    }

    public ClassifiedServiceException(Object causeObject) {
        super(causeObject);
    }

    public ExceptionEnum getExceptionType() {
        return exceptionType;
    }
}
