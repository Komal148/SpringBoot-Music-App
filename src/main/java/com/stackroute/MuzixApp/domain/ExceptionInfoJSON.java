package com.stackroute.MuzixApp.domain;

import java.util.Date;

public class ExceptionInfoJSON {
    private String url;
    private String message;
    public Date timeStamp;

    public ExceptionInfoJSON(String url, String message, Date timeStamp) {
        super();
        this.url = url;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
