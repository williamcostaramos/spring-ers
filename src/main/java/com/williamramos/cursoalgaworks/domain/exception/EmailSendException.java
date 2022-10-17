package com.williamramos.cursoalgaworks.domain.exception;

public class EmailSendException extends RuntimeException{
    public EmailSendException(String s) {
        super(s);
    }

    public EmailSendException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
