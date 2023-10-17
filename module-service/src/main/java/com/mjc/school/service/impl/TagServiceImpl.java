package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.TagRepositoryImpl;
import com.mjc.school.repository.model.TagModel;

import com.mjc.school.service.TagService;
import com.mjc.school.service.annotation.ValidateId;
import com.mjc.school.service.annotation.ValidateParam;
import com.mjc.school.service.constants.Constants;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exception.ErrorCode;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepositoryImpl tagRepository;
    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagRepositoryImpl tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDtoResponse> readAll(int page, int size, String sortBy) {
        return tagMapper.modelListToDtoListResponse(tagRepository.readAll(page, size, sortBy));
    }

    @Override
    @ValidateId
    @Transactional(readOnly = true)
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> tagModel = tagRepository.readById(id);
        if(tagModel.isPresent()){
            return tagMapper.tagToDtoResponse(tagModel.get());
        }
        throw new NotFoundException(String.format(ErrorCode.NOT_FOUND_DATA.getMessage()));
    }

    @Override
    @ValidateParam
    @Transactional
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return tagMapper.tagToDtoResponse(
                tagRepository.create(tagMapper.tagFromDtoRequest(createRequest)));
    }

    @Override
    @ValidateParam
    @Transactional
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        if(tagRepository.existById(updateRequest.getId())){
            TagModel tagModel = tagRepository.update(tagMapper.tagFromDtoRequest(updateRequest));
            return tagMapper.tagToDtoResponse(tagModel);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.TAG, updateRequest.getId()));
    }

    @Override
    @ValidateId
    @Transactional
    public boolean deleteById(Long id) {
        if(tagRepository.existById(id)){
            return tagRepository.deleteById(id);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.TAG, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDtoResponse> readByNewsId(Long newsId) {
        return tagMapper.modelListToDtoListResponse(tagRepository.readByNewsId(newsId));
    }
}
