package com.pilog.plontology.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;

public class BadRequestException extends HttpStatusCodeException {


    public BadRequestException(HttpStatus errorCode, String message, String statusText) {
        super(message, errorCode, statusText, (HttpHeaders)null, (byte[])null, (Charset)null);
    }


}

