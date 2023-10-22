package com.mjc.school.service.validator;

import com.mjc.school.service.validator.ConstraintViolation;

import java.util.Set;

public interface Validator {
    Set<ConstraintViolation> validate(Object object);
}
