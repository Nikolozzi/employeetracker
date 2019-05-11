package com.gmail.khitirinikoloz.employeetracker.rest.exceptionhandling;

public class InvalidEmployeeException extends RuntimeException {
    public InvalidEmployeeException() {
        super();
    }

    public InvalidEmployeeException(String message) {
        super(message);
    }

    public InvalidEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmployeeException(Throwable cause) {
        super(cause);
    }

    protected InvalidEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
