package com.stackroute.MuzixApp.Exceptions;

//Track Already Exist EXecption class
public class TrackAlreadyExistException extends Exception{

    private String message;
    public TrackAlreadyExistException(String message)
    {
        super(message);
        this.message=message;

    }
}
