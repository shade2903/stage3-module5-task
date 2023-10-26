package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/authors",
        produces = {"application/JSON", "application/XML"})
@Api(value = "Operations for creating, updating, retrieving and deleting author in the application")
public class AuthorRestController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping
    @ApiOperation(value = "View all authors", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all authors"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public ResponseEntity<List<AuthorDtoResponse>> readAll(@RequestParam(defaultValue = "1", required = false) int page,
                                                          @RequestParam(defaultValue = "10", required = false) int size,
                                                          @RequestParam(name = "sort_by", defaultValue = "id::asc", required = false) String sortBy) {
        List<AuthorDtoResponse> authorDtoResponses = authorService.readAll(page,size,sortBy);
        authorDtoResponses.stream().forEach(response -> response.add(getLinK(response)));
        return new ResponseEntity<>(authorDtoResponses,HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve specific author with the supplied id", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the author with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDtoResponse> readById(@PathVariable Long id) {
        AuthorDtoResponse authorDtoResponse = authorService.readById(id);
        return new ResponseEntity<>(authorDtoResponse.add(getLinK(authorDtoResponse)),HttpStatus.OK);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create an author", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created an author"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public ResponseEntity<AuthorDtoResponse> create(@RequestBody AuthorDtoRequest createRequest) {
        AuthorDtoResponse authorDtoResponse = authorService.create(createRequest);
        return new ResponseEntity<>(authorDtoResponse.add(getLinK(authorDtoResponse)), HttpStatus.CREATED);
    }

    @Override
    @PatchMapping("/{id}")
    @ApiOperation(value = "Patch a piece of author information", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully patched  author information"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public ResponseEntity<AuthorDtoResponse> patch(@PathVariable Long id, @RequestBody AuthorDtoRequest updateRequest) {
        AuthorDtoResponse authorDtoResponse = authorService.update(updateRequest);
        return new ResponseEntity<>(authorDtoResponse.add(getLinK(authorDtoResponse)), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a piece of author information", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated author information"),
            @ApiResponse(code = 400, message = "Your request is not valid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public ResponseEntity<AuthorDtoResponse> update(@PathVariable Long id, @RequestBody AuthorDtoRequest updateRequest) {
        AuthorDtoResponse authorDtoResponse = authorService.update(updateRequest);
        return new ResponseEntity<>(authorDtoResponse.add(getLinK(authorDtoResponse)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes specific author with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deletes the specific author"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    private Link getLinK(AuthorDtoResponse response){
        return linkTo(AuthorRestController.class).slash(response.getId()).withSelfRel();

    }
}
