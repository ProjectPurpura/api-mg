package org.purpura.apimg.validation.generic;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NonZeroValidator.class)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NonZero {
    String message() default "Valor deve ser diferente de zero";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
