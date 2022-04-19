package com.samuel.authuser.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameConstraintImpl.class) //qual a classe de implementacao da validacao
@Target({ElementType.METHOD, ElementType.FIELD}) //em quais tipos de campos eu posso colocar
@Retention(RetentionPolicy.RUNTIME) //quando vai ocorrer a validacao
public @interface UserNameConstraint {

    //parametros default do bean validation
    String message() default "Invalid username"; // mensagem quando for ocorrer o erro
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
