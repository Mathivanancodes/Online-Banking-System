package com.banking.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "Password must be at least 8 characters, with 1 uppercase, 1 lowercase, 1 digit, and 1 special char";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
