package com.AssignmentStream.AssignmentStream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception{

        ExceptionResponse exceptionResponse = new
                ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity<Object> handlBookNotFoundException(Exception ex,WebRequest request) throws Exception{
        ExceptionResponse exceptionResponse = new
                ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookISsuedException.class)
    public final ResponseEntity<Object> handleBookIssuedException(Exception ex,WebRequest request) throws Exception{
        ExceptionResponse exceptionResponse = new
                ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
