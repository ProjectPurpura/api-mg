package org.purpura.apimg.validation.chavepix;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;
import java.util.regex.Pattern;

public class PixKeyValidator implements ConstraintValidator<PixKey, String> {

    private static final Pattern CPF = Pattern.compile("^\\d{11}$");
    private static final Pattern CNPJ = Pattern.compile("^\\d{14}$");
    private static final Pattern PHONE = Pattern.compile("^\\+[1-9][0-9]{1,14}$");
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    public boolean isValid(String key, ConstraintValidatorContext context) {
        if (key == null || key.length() > 77) {
            return false;
        }

        if (CPF.matcher(key).matches() || CNPJ.matcher(key).matches()) {
            return true;
        }
        if (PHONE.matcher(key).matches()) {
            return true;
        }
        if (EMAIL.matcher(key).matches()) {
            return true;
        }
        try {
            UUID.fromString(key);
            return true;
        } catch (IllegalArgumentException ignored) { }

        return false;
    }
}
