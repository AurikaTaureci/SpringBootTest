package com.example.SpringBootTest.exception;

import org.apache.logging.log4j.message.Message;

public class CdNotFoundException extends RuntimeException{

    public CdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public CdNotFoundException(String message) {
        super(message);
    }
}
