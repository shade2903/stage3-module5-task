package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.query.NewsQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/v1/news",
        produces = {"application/JSON", "application/XML"})
public class NewsRestController implements NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readAll(@RequestParam(defaultValue = "1", required = false) int page,
                                         @RequestParam(defaultValue = "10", required = false) int size,
                                         @RequestParam(name = "sort_by", defaultValue = "id::asc", required = false) String sortBy) {
        return newsService.readAll(page, size, sortBy);
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse readById(@PathVariable Long id) {
        return newsService.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDtoResponse create(@RequestBody NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse update(@PathVariable Long id,@RequestBody NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse patch(@PathVariable Long id, @RequestBody NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
    }

    @Override
    @GetMapping("/param")
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readBySearchParams(@RequestParam(value = "tag-id", required = false) List<Integer> tagIds,
                                                    @RequestParam(value = "tag-names", required = false) List<String> tagNames,
                                                    @RequestParam(value = "author", required = false) String author,
                                                    @RequestParam(value = "title", required = false) String title,
                                                    @RequestParam(value = "content", required = false) String content) {
        return newsService.readBySearchParams(new NewsQueryParams(tagNames,tagIds,author,title,content));
    }
}
