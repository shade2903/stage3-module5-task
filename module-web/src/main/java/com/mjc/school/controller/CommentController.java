package com.mjc.school.controller;

import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;

import java.util.List;

public interface CommentController extends BaseController<CommentDtoRequest, CommentDtoResponse, Long> {
    List<CommentDtoResponse> readByNewsId(Long newsId);
}
