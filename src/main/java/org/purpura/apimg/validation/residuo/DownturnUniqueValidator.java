package org.purpura.apimg.validation.residuo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.purpura.apimg.dto.schemas.empresa.residuo.EstoqueDownturn;

import java.util.List;

public class DownturnUniqueValidator implements ConstraintValidator<DownturnUnique, List<EstoqueDownturn>> {
    @Override
    public boolean isValid(List<EstoqueDownturn> estoqueDownturns, ConstraintValidatorContext constraintValidatorContext) {
        return estoqueDownturns.stream().distinct().count() == estoqueDownturns.size();
    }
}
