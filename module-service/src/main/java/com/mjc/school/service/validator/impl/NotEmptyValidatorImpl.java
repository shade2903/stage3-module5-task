package com.mjc.school.service.validator.impl;

import com.mjc.school.service.exception.RequestException;
import com.mjc.school.service.exception.RequiredParameterDidNotSetException;
import com.mjc.school.service.validator.interfase.FieldValidator;


import javax.xml.bind.ValidationException;
import java.lang.reflect.Field;
import java.util.Collection;

public class NotEmptyValidatorImpl implements FieldValidator {
    @Override
    public void validate(Object entity, Field field) {
        try{
            if(Collection.class.isAssignableFrom(field.getClass())){
                Collection<?> fieldValue = (Collection<?>) field.get(entity);
                if(fieldValue == null || fieldValue.isEmpty()) {
                    throw new RequiredParameterDidNotSetException(field.getName());

                }
            } else if (String.class.isAssignableFrom(field.getType())){
                String fieldValue = (String) field.get(entity);
                if(fieldValue == null || fieldValue.isEmpty()) {
                    throw new RequiredParameterDidNotSetException(field.getName());
                    }
                }else{
                    if(field.get(entity) == null){
                        throw new RequiredParameterDidNotSetException(field.getName());
                    }
                }


        }catch (IllegalAccessException e){
            try {
                throw new ValidationException(e);
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
