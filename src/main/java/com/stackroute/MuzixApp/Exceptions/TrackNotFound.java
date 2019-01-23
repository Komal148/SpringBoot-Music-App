package com.stackroute.MuzixApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TrackNotFound extends Exception {
    private String message;
    public TrackNotFound(String message)
    {
        super(message);
        this.message=message;

    }
}
