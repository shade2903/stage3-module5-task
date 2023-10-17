package com.mjc.school.controller.impl;

import com.mjc.school.controller.TagController;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/v1/tags",
        produces = {"application/JSON", "application/XML"})
public class TagRestController implements TagController {
    private final TagService tagService;

    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readAll(@RequestParam(defaultValue = "1", required = false) int page,
                                        @RequestParam(defaultValue = "10", required = false) int size,
                                        @RequestParam(name = "sort_by", defaultValue = "id::asc", required = false) String sortBy) {
        return tagService.readAll(page, size, sortBy);
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse readById(@PathVariable Long id) {
        return tagService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDtoResponse create(@RequestBody TagDtoRequest createRequest) {
        return tagService.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse update(@PathVariable Long id, @RequestBody TagDtoRequest updateRequest) {
        return tagService.update(updateRequest);
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse patch(@PathVariable Long id, @RequestBody TagDtoRequest updateRequest) {
        return tagService.update(updateRequest);
    }


    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        tagService.deleteById(id);
    }

    @Override
    @GetMapping("/news/{newsId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readByNewsId(@PathVariable Long newsId) {
        return tagService.readByNewsId(newsId);
    }
}
