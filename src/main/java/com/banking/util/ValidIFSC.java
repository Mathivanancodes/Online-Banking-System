package com.banking.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IfscValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIFSC {
    String message() default "Invalid IFSC code format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
