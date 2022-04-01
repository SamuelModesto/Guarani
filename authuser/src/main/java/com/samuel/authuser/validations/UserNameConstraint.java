package com.samuel.authuser.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameConstraintImpl.class) //implementacao da validacao
@Target({ElementType.METHOD, ElementType.FIELD}) //em quais tipos de campos eu posso colocar
@Retention(RetentionPolicy.RUNTIME) //quando vai ocorrer
public @interface UserNameConstraint {

    //parametros default do bean validation
    String message() default "Invalid username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
