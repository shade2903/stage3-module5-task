package com.mjc.school.service.aspect;

import com.mjc.school.service.validator.interfase.ParamValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Aspect
@Component
public class MethodParamValidationAspect {
    private final ParamValidator validator;

    @Autowired
    public MethodParamValidationAspect(ParamValidator validator) {
        this.validator = validator;
    }

    @Before(value = "@annotation(com.mjc.school.service.validator.annotation.ValidParams)")
    public void validateParameters(JoinPoint joinPoint) {
        Stream.of(joinPoint.getArgs()).forEach(validator::validate);
    }
}
