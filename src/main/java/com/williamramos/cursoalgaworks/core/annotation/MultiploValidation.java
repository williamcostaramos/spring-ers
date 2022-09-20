package com.williamramos.cursoalgaworks.core.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidation implements ConstraintValidator<Multiplo, Number> {
    private int numeroMultiplo;

    @Override
    public void initialize(Multiplo constraintAnnotation) {
        this.numeroMultiplo = constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        if(number != null){
            BigDecimal valorDecimal = BigDecimal.valueOf(number.doubleValue());
            BigDecimal multiploDecial = BigDecimal.valueOf(this.numeroMultiplo);
            BigDecimal resto = valorDecimal.remainder(multiploDecial);
            isValid = BigDecimal.ZERO.compareTo(resto) == 0;
        }
        return isValid;
    }
}
