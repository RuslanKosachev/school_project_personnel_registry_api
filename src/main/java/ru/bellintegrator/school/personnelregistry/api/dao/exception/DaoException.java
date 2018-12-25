package ru.bellintegrator.school.personnelregistry.api.dao.exception;

import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;

/**
 *  Исключение для модуля DAO
 */
public class DaoException extends Exception {

    ErrorCode errorCode;

    public DaoException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getErrorString(), cause);
        this.errorCode = errorCode;
    }

    public DaoException(ErrorCode errorCode) {
        super(errorCode.getErrorString());
        this.errorCode = errorCode;
    }

    public DaoException(String value) {
        super(value);
    }

    public DaoException(String value, Throwable cause) {
        super(value, cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
