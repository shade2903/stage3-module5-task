package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.AuthorRepositoryImpl;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.annotation.ValidateId;
import com.mjc.school.service.annotation.ValidateParam;
import com.mjc.school.service.constants.Constants;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.ErrorCode;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepositoryImpl authorRepository;
    private final AuthorMapper authorMapper;


    @Autowired
    public AuthorServiceImpl(AuthorRepositoryImpl authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }




    @Override
    @Transactional(readOnly = true)
    public List<AuthorDtoResponse> readAll(int page, int size, String sortBy) {
        return authorMapper.modelListToDtoList(authorRepository.readAll(page,size,sortBy));

    }

    @Override
    @ValidateId
    @Transactional(readOnly = true)
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> authorModel = authorRepository.readById(id);
        if (authorModel.isPresent()) {
            return authorMapper.authorToDtoResponse(authorModel.get());
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, id));
    }

    @Override
    @ValidateParam
    @Transactional
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return authorMapper.authorToDtoResponse(
                authorRepository.create(authorMapper.authorFromDtoRequest(createRequest)));
    }

    @Override
    @ValidateParam
    @Transactional
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if(authorRepository.existById(updateRequest.getId())){
            AuthorModel updateAuthor = authorRepository.update(authorMapper.authorFromDtoRequest(updateRequest));
            return authorMapper.authorToDtoResponse(updateAuthor);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, updateRequest.getId()));
    }

    @Override
    @ValidateId
    @Transactional
    public boolean deleteById(Long id) {
        if(authorRepository.existById(id)){
            return authorRepository.deleteById(id);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, id));
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDtoResponse readByNewsId(Long newsId) {
        Optional<AuthorModel> authorModel = authorRepository.readByNewsId(newsId);
        if (authorModel.isPresent()) {
            return authorMapper.authorToDtoResponse(authorModel.get());
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.NEWS_ID, newsId));
    }
}
