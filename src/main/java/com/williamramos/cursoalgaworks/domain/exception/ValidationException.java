package com.williamramos.cursoalgaworks.domain.exception;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ValidationException extends RuntimeException {
    private BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }


    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
