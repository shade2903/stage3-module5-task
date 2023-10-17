package com.mjs.school.service;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.impl.AuthorRepositoryImpl;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.InvalidDataException;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.impl.AuthorServiceImpl;
import com.mjc.school.service.mapper.AuthorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    private final Long EXPECTED_ID = 4l;
    private final String EXPECTED_NAME = "TestAuthorName";
    private final String EXPECTED_UPDATED_NAME = "UpdatedAuthorName";
    private List<AuthorModel> authorList;
    private AuthorDtoRequest authorDtoRequest;
    private AuthorModel authorModel;
    private LocalDateTime timeNow;

    @Mock
    private AuthorRepositoryImpl authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    void init() {
        timeNow = LocalDateTime.now();
        authorDtoRequest = new AuthorDtoRequest(EXPECTED_ID, EXPECTED_NAME);
        authorModel = new AuthorModel(EXPECTED_ID, EXPECTED_NAME, timeNow, timeNow);
        authorList = List.of(new AuthorModel(), new AuthorModel(), new AuthorModel());

    }

    @Test
    void readAllTest() {
        when(authorMapper.modelListToDtoList(authorList)).thenReturn(
                List.of(new AuthorDtoResponse(), new AuthorDtoResponse(), new AuthorDtoResponse()));
        when(authorRepository.readAll(1, 2, "id::desc")).thenReturn(authorList);
        List<AuthorDtoResponse> authorDtoResponses = authorService.readAll(1, 2, "id::desc");
        assertNotNull(authorDtoResponses);
        assertEquals(authorList.size(), authorDtoResponses.size());
    }

    @Test
    void createTest() {
        when(authorMapper.authorFromDtoRequest(authorDtoRequest)).thenReturn(authorModel);
        when(authorMapper.authorToDtoResponse(authorModel)).thenReturn(
                new AuthorDtoResponse(EXPECTED_ID, EXPECTED_NAME, timeNow, timeNow));
        given(authorRepository.create(authorModel)).willReturn(authorModel);
        AuthorDtoResponse actual = authorService.create(authorDtoRequest);
        assertNotNull(actual);
        assertEquals(EXPECTED_ID, actual.getId());
        assertEquals(EXPECTED_NAME, actual.getName());
    }

    @Test
    void readByIdTest() {

        when(authorMapper.authorToDtoResponse(authorModel)).thenReturn(
                new AuthorDtoResponse(EXPECTED_ID, EXPECTED_NAME, timeNow, timeNow));
        given(authorRepository.readById(EXPECTED_ID)).willReturn(Optional.of(authorModel));
        AuthorDtoResponse actual = authorService.readById(EXPECTED_ID);
        assertNotNull(actual);
        assertEquals(EXPECTED_ID, actual.getId());
        assertEquals(EXPECTED_NAME, actual.getName());
    }

    @Test
    void updateTest() {
        AuthorDtoRequest updatedRequest = authorDtoRequest;
        updatedRequest.setName(EXPECTED_UPDATED_NAME);
        AuthorModel updatedModel = new AuthorModel(authorDtoRequest.getId(), authorDtoRequest.getName(), timeNow, timeNow);
        when(authorMapper.authorFromDtoRequest(updatedRequest)).thenReturn(updatedModel);
        when(authorMapper.authorToDtoResponse(updatedModel)).thenReturn(new AuthorDtoResponse(
                updatedModel.getId(), updatedModel.getName(), updatedModel.getCreateDate(), updatedModel.getLastUpdateDate()
        ));
        given(authorRepository.update(updatedModel)).willReturn(updatedModel);
        given(authorRepository.existById(updatedModel.getId())).willReturn(true);
        AuthorDtoResponse actual = authorService.update(updatedRequest);
        assertNotNull(actual);
        assertEquals(EXPECTED_ID, actual.getId());
        assertEquals(EXPECTED_UPDATED_NAME,actual.getName());
    }

    @Test
    void deleteTest() {
        given(authorRepository.deleteById(EXPECTED_ID)).willReturn(true);
        given(authorRepository.existById(EXPECTED_ID)).willReturn(true);
        assertTrue(authorService.deleteById(4L));
    }

    @Test()
    void notValidIdTest() {
        Long notValidId = 0L;
        assertThrows(NotFoundException.class, () -> authorService.deleteById(notValidId));
    }

    @Test
    void notValidNameTest() {
        String notValidName = "Jak";
        AuthorDtoRequest authorDtoRequest1 = new AuthorDtoRequest(null, notValidName);
        when(authorRepository.create(argThat(argument -> argument.getName() == null ||
                argument.getName().length() < 5)))
                .thenThrow(InvalidDataException.class);
        when(authorMapper.authorFromDtoRequest(authorDtoRequest1)).thenReturn(
                new AuthorModel(null, notValidName,timeNow,timeNow));
        assertThrows(InvalidDataException.class, () ->
                authorService.create(authorDtoRequest1));
    }

}
