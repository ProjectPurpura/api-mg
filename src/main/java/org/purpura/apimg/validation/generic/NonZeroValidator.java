package org.purpura.apimg.validation.generic;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NonZeroValidator implements ConstraintValidator<NonZero, Integer> {
    @Override
    public boolean isValid(Integer o, ConstraintValidatorContext constraintValidatorContext) {
        int value = o == null ? 0 : o;
        return value != 0;
    }
}
