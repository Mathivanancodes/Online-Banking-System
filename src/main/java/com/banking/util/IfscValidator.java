package com.banking.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IfscValidator implements ConstraintValidator<ValidIFSC, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.matches("^[A-Z]{4}0[A-Z0-9]{6}$");
    }
}
