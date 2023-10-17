package com.mjc.school.controller.impl;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/authors",
        produces = {"application/JSON", "application/XML"})
public class AuthorRestController implements AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @Override
    @GetMapping("/news/{newsId}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse readByIdNews(@PathVariable Long newsId) {
        return authorService.readByNewsId(newsId);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDtoResponse> readAll(@RequestParam(defaultValue = "1", required = false) int page,
                                           @RequestParam(defaultValue = "10", required = false) int size,
                                           @RequestParam(name = "sort_by", defaultValue = "id::asc", required = false) String sortBy) {
        return authorService.readAll(page,size,sortBy);
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse readById(@PathVariable Long id) {
        return authorService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDtoResponse create(@RequestBody AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse patch(@PathVariable Long id, @RequestBody AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse update(@PathVariable Long id, @RequestBody AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteById(@PathVariable  Long id) {
        authorService.deleteById(id);
    }
}
