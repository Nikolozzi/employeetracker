package com.gmail.khitirinikoloz.employeetracker.rest.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception e){

        EmployeeErrorResponse error = new EmployeeErrorResponse(HttpStatus.BAD_REQUEST.value(),
                e.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(InvalidEmployeeException e){

        EmployeeErrorResponse error = new EmployeeErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(),
                e.getMessage());


        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }
}
