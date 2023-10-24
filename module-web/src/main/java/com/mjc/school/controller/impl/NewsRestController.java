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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/v1/news",
        produces = {"application/JSON", "application/XML"})
@Api(value = "Operations for creating, updating, retrieving and deleting news in the application")
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
    @ApiOperation(value = "View all news", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all news"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readAll(@RequestParam(defaultValue = "1", required = false) int page,
                                         @RequestParam(defaultValue = "10", required = false) int size,
                                         @RequestParam(name = "sort_by", defaultValue = "id::asc", required = false) String sortBy) {
        return newsService.readAll(page, size, sortBy);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve specific news with the supplied id", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the news with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse readById(@PathVariable Long id) {
        return newsService.readById(id);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create a piece of news", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a piece of news"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDtoResponse create(@RequestBody NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a piece of news information", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated news information"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse update(@PathVariable Long id,@RequestBody NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    @PatchMapping("/{id}")
    @ApiOperation(value = "Patch a piece of news information", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully patched  news information"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse patch(@PathVariable Long id, @RequestBody NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes specific news with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deletes the specific news"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
    }


    @GetMapping("/search")
    @ApiOperation(value = "View all news by tag id, tag names, author, title, content", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all news by search param"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readBySearchParams(@RequestParam(value = "tag-id", required = false) List<Integer> tagIds,
                                                    @RequestParam(value = "tag-names", required = false) List<String> tagNames,
                                                    @RequestParam(value = "author", required = false) String author,
                                                    @RequestParam(value = "title", required = false) String title,
                                                    @RequestParam(value = "content", required = false) String content) {
        return newsService.readBySearchParams(new NewsQueryParams(tagNames,tagIds,author,title,content));
    }


    @GetMapping("/news/{newsId}/author")
    @ApiOperation(value = "Retrieve specific author with the supplied news id", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the author with the supplied news id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse readAuthorByNewsId(@PathVariable Long newsId) {
        return authorService.readByNewsId(newsId);
    }


    @GetMapping("/news/{newsId}/tags")
    @ApiOperation(value = "Retrieve specific all tags with the supplied news id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the tags with the supplied news id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readTagsByNewsId(@PathVariable Long newsId) {
        return tagService.readByNewsId(newsId);
    }

    @GetMapping("/news/{newsId}/comments")
    @ApiOperation(value = "Retrieve specific all comments with the supplied news id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the comments with the supplied news id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> readCommentsByNewsId(@PathVariable Long newsId) {
        return commentService.readByNewsId(newsId);
    }
}
