package com.mjc.school.service.validator.interfase;

import java.lang.reflect.Field;

public interface FieldValidator {
    void validate(Object entity, Field field);
}
