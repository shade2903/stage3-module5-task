package com.mjc.school.service.validator;

import com.mjc.school.service.validator.interfase.FieldValidator;
import com.mjc.school.service.validator.interfase.ParamValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class AnnotationBasedParamValidatorIml implements ParamValidator {
    private final Map<Class<? extends Annotation>, FieldValidator> validationFunction;
    private final Set<Class<? extends Annotation>> supportedFieldAnnotations;

    public AnnotationBasedParamValidatorIml(Map<Class<? extends Annotation>, FieldValidator> validationFunctions) {
        this.validationFunction = validationFunctions;
        this.supportedFieldAnnotations = this.validationFunction.keySet();
    }

    @Override
    public void validate(Object param) {
        if(param == null){
            throw new RuntimeException("Passed param is null");
        }
        Class<?> clazz = param.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields){
            field.setAccessible(true);
            supportedFieldAnnotations.stream()
                    .filter(field::isAnnotationPresent)
                    .map(validationFunction::get)
                    .forEach(fieldValidator -> fieldValidator.validate(param, field));
        }
    }
}
