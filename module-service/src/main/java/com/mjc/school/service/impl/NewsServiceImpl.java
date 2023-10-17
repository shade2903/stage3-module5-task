package com.mjc.school.service.impl;


import com.mjc.school.repository.impl.AuthorRepositoryImpl;
import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.query.NewsSearchQueryParams;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.annotation.ValidateId;
import com.mjc.school.service.annotation.ValidateParam;
import com.mjc.school.service.constants.Constants;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.ErrorCode;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.query.NewsQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepositoryImpl newsRepository;
    private final AuthorRepositoryImpl authorRepository;
    private final NewsMapper newsMapper;

    @Autowired
    public NewsServiceImpl(NewsRepositoryImpl newsRepository, AuthorRepositoryImpl authorRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
        this.newsMapper = newsMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDtoResponse> readAll(int page, int size, String sortBy) {
        return newsMapper.modelListToDtoList(newsRepository.readAll(page,size,sortBy));
    }

    @Override
    @ValidateId
    @Transactional(readOnly = true)
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsModel = newsRepository.readById(id);
        if (newsModel.isPresent()) {
            return newsMapper.newsToDtoResponse(newsModel.get());
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, id));
    }

    @Override
    @ValidateParam
    @Transactional
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        if (!authorRepository.existById(createRequest.getAuthorId())) {
            throw new NotFoundException(
                    String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, createRequest.getAuthorId()));
        }
        NewsModel createNews = newsMapper.newsFromDtoRequest(createRequest);
        return newsMapper.newsToDtoResponse(newsRepository.create(createNews));
    }

    @Override
    @ValidateParam
    @Transactional
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if (!authorRepository.existById(updateRequest.getAuthorId())) {
            throw new NotFoundException(
                    String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, updateRequest.getAuthorId()));
        }
        if (newsRepository.existById(updateRequest.getId())) {
            NewsModel newsModel = newsRepository.update(newsMapper.newsFromDtoRequest(updateRequest));
            return newsMapper.newsToDtoResponse(newsModel);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.NEWS_ID, updateRequest.getId()));
    }

    @Override
    @ValidateId
    @Transactional
    public boolean deleteById(Long id) {
        if (newsRepository.existById(id)) {

            return newsRepository.deleteById(id);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.NEWS_ID, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDtoResponse> readBySearchParams(NewsQueryParams queryParams) {
        NewsSearchQueryParams newsSearchQueryParams = new NewsSearchQueryParams(
                queryParams.tagNames(),
                queryParams.tagIds(),
                queryParams.authorName(),
                queryParams.title(),
                queryParams.content()
        );
        return newsMapper.modelListToDtoList(newsRepository.readBySearchParams(newsSearchQueryParams));
    }
}
