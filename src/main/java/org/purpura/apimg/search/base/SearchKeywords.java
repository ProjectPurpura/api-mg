package org.purpura.apimg.search.base;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotNull(message = "Search keywords não podem ser nulas")
@NotBlank(message = "Search keywords não podem ser vazias")
@Constraint(validatedBy = {})
public @interface SearchKeywords {
    String message() default "As palavras chave de pesquisa precisam ser válidas, não nulas e não vazias";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
