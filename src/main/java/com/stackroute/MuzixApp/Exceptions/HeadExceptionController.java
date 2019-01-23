package com.stackroute.MuzixApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

//Global Excption Handler Class
@ControllerAdvice
public class HeadExceptionController {

    //Handling Exception of Track Not Found
    @ExceptionHandler(TrackNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorClass handleTracknotFoundException(final TrackNotFound e, final HttpServletRequest request){
        ErrorClass errorClass= new ErrorClass();
        errorClass.setErrormessage(e.getMessage());
        errorClass.setRequestedURI(request.getRequestURI());
        return errorClass;
    }

    //Handling Exception of Track Already Exist
    @ExceptionHandler(TrackAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody ErrorClass handleTrackAlreadyExists(final TrackAlreadyExistException e,final HttpServletRequest request){
        ErrorClass errorClass= new ErrorClass();
        errorClass.setErrormessage(e.getMessage());
        errorClass.setRequestedURI(request.getRequestURI());
        return errorClass;
    }
}
