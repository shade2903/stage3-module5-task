package com.mjs.school.controller;

import com.mjc.school.service.dto.AuthorDtoRequest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class AuthorRestControllerTest extends FunctionalTest {
//    @Test
//    public void readAllTest() {
//        given().when().get("/authors").then().statusCode(200);
//    }
//
//    @Test
//    public void readByIdTest() {
//        given()
//                .pathParam("id", 1)
//                .when()
//                .get("/authors/{id}")
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(1))
//                .body("name", notNullValue());
//
//    }
//
//    @Test
//    public void createTest() {
//        Map<String, String> author = new HashMap<>();
//        author.put("name", "Test author");
//        given()
//                .contentType("application/JSON")
//                .body(author)
//                        .when()
//                .post("/authors")
//                .then()
//                .statusCode(201)
//                .body("name", equalTo("Test author"));
//
//    }
//    @Test
//    public void createEntityNameLessThanFiveCharacters(){
//        Map<String, String> author = new HashMap<>();
//        author.put("name", "Lol");
//        given()
//                .contentType("application/JSON")
//                .body(author)
//                .when()
//                .post("/authors")
//                .then()
//                .statusCode(400);
//    }
//    public void updateTest(){
//        Map<String, String> author = new HashMap<>();
//        author.put("name", "Maksim");
//       int updatedId =  given()
//                .contentType("application/JSON")
//                .body(author)
//                .when()
//                .post("/authors")
//                .then()
//                .statusCode(201)
//                .extract().path("id");
//
//        Map<String, String> updatedAuthor = new HashMap<>();
//        author.put("id","" + updatedId);
//        author.put("name","updatedName");
//
//    }

}
