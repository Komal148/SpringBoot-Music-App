package com.stackroute.MuzixApp.Exceptions;

import lombok.Data;

@Data
public class ErrorClass {

    private String errormessage;

    private String requestedURI;
}
