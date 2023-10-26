package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.NewsDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/comments",
        produces = {"application/JSON", "application/XML"})
@Api(value = "Operations for creating, updating, retrieving and deleting comment in the application")
public class CommentRestController implements BaseController<CommentDtoRequest, CommentDtoResponse, Long> {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View all comments", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully retrieved all comments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public ResponseEntity<List<CommentDtoResponse>> readAll(@RequestParam(defaultValue = "1", required = false) int page,
                                                            @RequestParam(defaultValue = "10", required = false) int size,
                                                            @RequestParam(name = "sort_by", defaultValue = "id::asc", required = false) String sortBy) {
        List<CommentDtoResponse> commentDtoResponses = commentService.readAll(page, size, sortBy);
        commentDtoResponses.stream().forEach(this::mapLinksEntity);
        return new ResponseEntity<>(commentDtoResponses, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve specific comment with the supplied id", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the comment with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public ResponseEntity<CommentDtoResponse> readById(@PathVariable Long id) {
        CommentDtoResponse commentDtoResponse = commentService.readById(id);
        mapLinksEntity(commentDtoResponse);
        return new ResponseEntity<>(commentDtoResponse, HttpStatus.OK);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create a comment", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a comment"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public ResponseEntity<CommentDtoResponse> create(@RequestBody CommentDtoRequest createRequest) {
        CommentDtoResponse commentDtoResponse = commentService.create(createRequest);
        mapLinksEntity(commentDtoResponse);
        return new ResponseEntity<>(commentDtoResponse, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a piece of comment information", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated comment information"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public ResponseEntity<CommentDtoResponse> update(@PathVariable Long id, @RequestBody CommentDtoRequest updateRequest) {
        CommentDtoResponse commentDtoResponse = commentService.update(updateRequest);
        mapLinksEntity(commentDtoResponse);
        return new ResponseEntity<>(commentDtoResponse, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    @ApiOperation(value = "Patch a piece of comment information", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully patched  comment information"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CommentDtoResponse> patch(@PathVariable Long id, @RequestBody CommentDtoRequest updateRequest) {
        CommentDtoResponse commentDtoResponse = commentService.update(updateRequest);
        return new ResponseEntity<>(commentDtoResponse, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes specific comment with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deletes the specific comment"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    private void mapLinksEntity(CommentDtoResponse response) {
        response.add(getLink(response));
        response.add(linkTo(methodOn(NewsRestController.class).readById(response.getNewsId())).withRel("news"));
    }

    private Link getLink(CommentDtoResponse response) {
        return linkTo(CommentDtoResponse.class).slash(response.getId()).withSelfRel();
    }

}
