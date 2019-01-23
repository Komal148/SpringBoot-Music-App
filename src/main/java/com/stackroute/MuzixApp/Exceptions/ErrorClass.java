package com.stackroute.MuzixApp.Exceptions;

import lombok.Data;

//This class format the Exception in the form of Error Message and RequestedURI
@Data
public class ErrorClass {

    private String errormessage;

    private String requestedURI;
}
