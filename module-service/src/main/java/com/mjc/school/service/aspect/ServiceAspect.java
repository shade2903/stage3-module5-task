package com.mjc.school.service.aspect;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.validator.ValidatorSimple;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    @Before("@annotation(com.mjc.school.service.annotation.ValidateId)" +
            "&& execution(public * com.mjc.school.service.impl.AuthorServiceImpl.*(..)) && args(id)")
    public void validateAuthorId(Long id) {
        ValidatorSimple.validateAuthorId(id);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateParam) " +
            "&& execution(public * com.mjc.school.service.impl.AuthorServiceImpl.*(..))  " +
            "&& args(request)")
    public void validateAuthorDto(AuthorDtoRequest request) {
        ValidatorSimple.validateAuthorDto(request);

    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateId)" +
            "&& execution(public * com.mjc.school.service.impl.AuthorServiceImpl.*(..)) && args(id)")
    public void validateNewsId(Long id) {
        ValidatorSimple.validateNewsId(id);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateParam) " +
            "&& execution(public * com.mjc.school.service.impl.NewsServiceImpl.*(..))" +
            "&& args(request)")
    public void validateNewsDto(NewsDtoRequest request) {
        ValidatorSimple.validateNewsDto(request);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateId)" +
            "&& execution(public * com.mjc.school.service.impl.TagServiceImpl.*(..)) && args(id)")
    public void validateTagId(Long id) {
        ValidatorSimple.validateTagId(id);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateParam) " +
            "&& execution(public * com.mjc.school.service.impl.TagServiceImpl.*(..))  " +
            "&& args(request)")
    public void validateTagDto(TagDtoRequest request) {
        ValidatorSimple.validateTagDto(request);
    }
}
