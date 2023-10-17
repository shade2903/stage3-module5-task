package com.mjc.school.controller;

import java.util.List;

public interface BaseController<T, R, K> {

    List<R> readAll(int page, int size, String sortBy);

    R readById(K id);

    R create(T createRequest);

    R update(K id, T updateRequest);
    R patch(K id, T updateRequest);

    void deleteById(K id);
}
