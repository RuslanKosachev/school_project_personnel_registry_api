package ru.bellintegrator.school.personnelregistry.api.view.exception;

/**
 *  Исключение для View
 */
public class ViewException extends Exception {

    ErrorCode errorCode;

    public ViewException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getErrorString(), cause);
        this.errorCode = errorCode;
    }

    public ViewException(ErrorCode errorCode) {
        super(errorCode.getErrorString());
        this.errorCode = errorCode;
    }

    public ViewException(String value) {
        super(value);
    }

    public ViewException(String value, Throwable cause) {
        super(value, cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
