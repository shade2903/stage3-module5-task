package com.mjc.school.controller;

import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.List;

public interface TagController extends BaseController<TagDtoRequest, TagDtoResponse, Long> {
    List<TagDtoResponse> readByNewsId(Long newsId);
}
