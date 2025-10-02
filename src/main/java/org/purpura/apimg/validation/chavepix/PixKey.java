package org.purpura.apimg.validation.chavepix;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PixKeyValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PixKey {
    String message() default "Chave Pix inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
