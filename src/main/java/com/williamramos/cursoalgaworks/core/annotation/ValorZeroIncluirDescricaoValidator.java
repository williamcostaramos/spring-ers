package com.williamramos.cursoalgaworks.core.annotation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ValorZeroIncluirDescricaoValidator implements ConstraintValidator<ValorZeroIncluirDescricao, Object> {
    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluirDescricao constraint) {
        this.valorField = constraint.valorField();
        this.descricaoField = constraint.descricaoField();
        this.descricaoObrigatoria = constraint.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(object.getClass(), this.valorField).getReadMethod().invoke(object);
            String descricao = (String) BeanUtils.getPropertyDescriptor(object.getClass(), this.descricaoField).getReadMethod().invoke(object);
            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
                isValid = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }
            return isValid;
        } catch (Exception e) {
            throw new ValidationException(e);
        }

    }
}
