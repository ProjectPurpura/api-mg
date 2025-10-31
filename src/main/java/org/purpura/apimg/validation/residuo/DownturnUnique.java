package org.purpura.apimg.validation.residuo;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DownturnUniqueValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DownturnUnique {
    String message() default "Cada requisição de baixa deve conter apenas uma instância de cada ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
