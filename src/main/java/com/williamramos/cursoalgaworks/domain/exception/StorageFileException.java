package com.williamramos.cursoalgaworks.domain.exception;

public class StorageFileException extends RuntimeException{
    public StorageFileException(String s) {
        super(s);
    }

    public StorageFileException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
