package com.mjc.school.service.validator;

import com.mjc.school.service.validator.annotation.NotEmpty;
import com.mjc.school.service.validator.annotation.RegExp;
import com.mjc.school.service.validator.impl.NotEmptyValidatorImpl;
import com.mjc.school.service.validator.impl.RegularExpressionValidatorImpl;
import com.mjc.school.service.validator.interfase.FieldValidator;
import com.mjc.school.service.validator.interfase.ParamValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ValidationConfiguration {

    @Bean
    public ParamValidator getParamValidator(){
        Map<Class<? extends Annotation>, FieldValidator> validatorMap = new HashMap<>();
        validatorMap.put(RegExp.class, new RegularExpressionValidatorImpl());
        validatorMap.put(NotEmpty.class,new NotEmptyValidatorImpl());
        return new AnnotationBasedParamValidatorIml(validatorMap);
    }
}
