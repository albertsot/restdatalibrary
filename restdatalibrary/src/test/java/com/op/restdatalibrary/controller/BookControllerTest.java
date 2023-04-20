package com.op.restdatalibrary.controller;

import com.op.restdatalibrary.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.net.ssl.SSLEngineResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    void setUp() {
        restTemplateBuilder=restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate=new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Book[]> response =testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());

        List<Book> books= Arrays.asList(response.getBody());
        System.out.println(books.size());
    }

    @Test
    void finById() {
    }

    @Test
    void create() {
    }
}