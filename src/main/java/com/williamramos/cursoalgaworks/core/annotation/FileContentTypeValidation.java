package com.williamramos.cursoalgaworks.core.annotation;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidation implements ConstraintValidator<FileContentTypeSize, MultipartFile> {

    private List<String> allowed;

    @Override
    public void initialize(FileContentTypeSize constraintAnnotation) {
        this.allowed = Arrays.asList(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext constraintValidatorContext) {
      return value ==null || allowed.contains(value.getContentType());
    }
}
