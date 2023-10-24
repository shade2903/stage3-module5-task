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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<NewsDtoResponse>> readAll(@RequestParam(defaultValue = "1", required = false) int page,
                                                        @RequestParam(defaultValue = "10", required = false) int size,
                                                        @RequestParam(name = "sort_by", defaultValue = "id::asc", required = false) String sortBy) {
        return new ResponseEntity<>(newsService.readAll(page, size, sortBy),HttpStatus.OK);
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
    public ResponseEntity<NewsDtoResponse>readById(@PathVariable Long id) {
        return new ResponseEntity<>(newsService.readById(id),HttpStatus.OK);
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
    public ResponseEntity<NewsDtoResponse> create(@RequestBody NewsDtoRequest createRequest) {
        return new ResponseEntity<>(newsService.create(createRequest),HttpStatus.CREATED);
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
    public ResponseEntity<NewsDtoResponse> update(@PathVariable Long id,@RequestBody NewsDtoRequest updateRequest) {
        return new ResponseEntity<>(newsService.update(updateRequest),HttpStatus.OK);
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
    public ResponseEntity<NewsDtoResponse> patch(@PathVariable Long id, @RequestBody NewsDtoRequest updateRequest) {
        return new ResponseEntity<>(newsService.update(updateRequest),HttpStatus.OK);
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
    public ResponseEntity<List<NewsDtoResponse>> readBySearchParams(@RequestParam(value = "tag-id", required = false) List<Integer> tagIds,
                                                    @RequestParam(value = "tag-names", required = false) List<String> tagNames,
                                                    @RequestParam(value = "author", required = false) String author,
                                                    @RequestParam(value = "title", required = false) String title,
                                                    @RequestParam(value = "content", required = false) String content) {
        return new ResponseEntity<>(newsService.readBySearchParams(new NewsQueryParams(tagNames,tagIds,author,title,content)),HttpStatus.OK);
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

    public ResponseEntity<AuthorDtoResponse> readAuthorByNewsId(@PathVariable Long newsId) {
        return new ResponseEntity<>(authorService.readByNewsId(newsId),HttpStatus.OK);
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
    public ResponseEntity<List<TagDtoResponse>> readTagsByNewsId(@PathVariable Long newsId) {
        return new ResponseEntity<>(tagService.readByNewsId(newsId),HttpStatus.OK);
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

    public ResponseEntity<List<CommentDtoResponse>> readCommentsByNewsId(@PathVariable Long newsId) {
        return new ResponseEntity<>(commentService.readByNewsId(newsId),HttpStatus.OK);
    }
}
