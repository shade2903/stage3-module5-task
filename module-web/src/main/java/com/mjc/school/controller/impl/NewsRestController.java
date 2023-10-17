package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.query.NewsQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/v1/news",
        produces = {"application/JSON", "application/XML"})
public class NewsRestController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    private final NewsService newsService;
    private final AuthorService authorService;
    private final TagService tagService;
    private final CommentService commentService;

    @Autowired
    public NewsRestController(NewsService newsService, AuthorService authorService, TagService tagService, CommentService commentService) {
        this.newsService = newsService;
        this.authorService = authorService;
        this.tagService = tagService;
        this.commentService = commentService;
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


    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readBySearchParams(@RequestParam(value = "tag-id", required = false) List<Integer> tagIds,
                                                    @RequestParam(value = "tag-names", required = false) List<String> tagNames,
                                                    @RequestParam(value = "author", required = false) String author,
                                                    @RequestParam(value = "title", required = false) String title,
                                                    @RequestParam(value = "content", required = false) String content) {
        return newsService.readBySearchParams(new NewsQueryParams(tagNames,tagIds,author,title,content));
    }


    @GetMapping("/news/{newsId}/author")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse readAuthorByNewsId(@PathVariable Long newsId) {
        return authorService.readByNewsId(newsId);
    }


    @GetMapping("/news/{newsId}/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readTagsByNewsId(@PathVariable Long newsId) {
        return tagService.readByNewsId(newsId);
    }

    @GetMapping("/news/{newsId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> readCommentsByNewsId(@PathVariable Long newsId) {
        return commentService.readByNewsId(newsId);
    }
}
