package com.mjc.school.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T, R, K> {

    ResponseEntity<List<R>> readAll(int page, int size, String sortBy);

    ResponseEntity<R> readById(K id);

    ResponseEntity<R> create(T createRequest);

   ResponseEntity<R> update(K id, T updateRequest);
    ResponseEntity<R> patch(K id, T updateRequest);

    void deleteById(K id);
}
