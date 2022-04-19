package com.samuel.authuser.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameConstraintImpl implements ConstraintValidator<UserNameConstraint, String> {
    /**
     * método default da classe e deve ser implementado dessa forma
     * @param constraintAnnotation
     */
    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * essa anotação verifica se o userName é nulo, vazio ou possui espacos em branco
     * @param userName
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if (userName == null || userName.trim().isEmpty() || userName.contains(" ")) {
            return false;
        }
        return true;
    }
}
