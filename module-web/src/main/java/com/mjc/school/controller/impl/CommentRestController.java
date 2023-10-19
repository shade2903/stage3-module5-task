package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.validator.annotation.ValidParams;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/comments",
        produces = {"application/JSON", "application/XML"})
public class CommentRestController implements BaseController<CommentDtoRequest, CommentDtoResponse, Long> {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> readAll(@RequestParam(defaultValue = "1", required = false) int page,
                                            @RequestParam(defaultValue = "10", required = false) int size,
                                            @RequestParam(name = "sort_by", defaultValue = "id::asc", required = false) String sortBy) {
        return commentService.readAll(page, size, sortBy);
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse readById(@PathVariable Long id) {
        return commentService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ValidParams
    public CommentDtoResponse create(@RequestBody CommentDtoRequest createRequest) {
        return commentService.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse update(@PathVariable Long id, @RequestBody CommentDtoRequest updateRequest) {
        return commentService.update(updateRequest);
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse patch(@PathVariable Long id,@RequestBody CommentDtoRequest updateRequest) {
        return commentService.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }

}
