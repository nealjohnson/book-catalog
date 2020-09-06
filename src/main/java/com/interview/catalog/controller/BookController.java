package com.interview.catalog.controller;

import com.interview.catalog.domain.model.BookCatalogVO;
import com.interview.catalog.service.BookCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private BookCatalogService bookCatalogService;
    @Autowired
    public BookController(BookCatalogService bookCatalogService) {
        this.bookCatalogService = bookCatalogService;
    }

    @GetMapping(value = "/catalog/{userId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchBookCatalogForUser(@PathVariable("userId")  Long id){
        List<BookCatalogVO> result = bookCatalogService.fetch(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
