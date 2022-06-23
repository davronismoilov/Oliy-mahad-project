package uz.oliymahad.user.annotation.role_constraint;



import uz.oliymahad.user.model.enums.ERole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<Role, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(ERole.values()).anyMatch(u -> u.name().equals(s));
    }
}
