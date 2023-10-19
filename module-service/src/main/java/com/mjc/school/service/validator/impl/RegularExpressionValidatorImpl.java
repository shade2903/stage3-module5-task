package com.mjc.school.service.validator.impl;

import com.mjc.school.service.exception.ParameterParsingException;
import com.mjc.school.service.validator.annotation.RegExp;
import com.mjc.school.service.validator.interfase.FieldValidator;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Field;

public class RegularExpressionValidatorImpl implements FieldValidator {
    @Override
    public void validate(Object entity, Field field) {
        if (String.class.isAssignableFrom(field.getType())) {
            RegExp annotation = field.getAnnotation(RegExp.class);
            String regex = annotation.value();
            try {
                String fieldValue = (String) field.get(entity);
                if (fieldValue != null && !fieldValue.matches(regex)) {
                    throw new ParameterParsingException(field.getName());
                }
            } catch (IllegalAccessException e) {
                try {
                    throw new ValidationException(e);
                } catch (ValidationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
