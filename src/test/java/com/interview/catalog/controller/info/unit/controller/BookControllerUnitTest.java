package com.interview.catalog.controller.info.unit.controller;

import com.google.common.collect.Lists;
import com.interview.catalog.controller.BookController;
import com.interview.catalog.domain.model.BookCatalogVO;
import com.interview.catalog.service.BookCatalogService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BookControllerUnitTest {

    @Mock
    public BookCatalogService bookService;

    public BookController bookController;
    @Before
    public void prepare(){
        bookController= new BookController(bookService);
    }

    @Test
    public void testFetchBookCatalogForUser() {
        Long userId =1l;
        Long bookId =1l;
        String bookName = "book1";
        BigDecimal rating = new BigDecimal("7.6");
        BookCatalogVO bookCatalogVO =new BookCatalogVO();
        bookCatalogVO.setBookId(bookId);
        bookCatalogVO.setName(bookName);
        bookCatalogVO.setRating(rating);
        List<BookCatalogVO> list =Lists.newArrayList(bookCatalogVO);
        Mockito.when(bookService.fetch(userId)).thenReturn(list);
        ResponseEntity result = bookController.fetchBookCatalogForUser(userId);

        List<BookCatalogVO> resultList = (List<BookCatalogVO>) result.getBody();
        Assert.assertEquals(bookName,resultList.get(0).getName());
        Assert.assertEquals(bookName,resultList.get(0).getName());
        Assert.assertEquals(rating,resultList.get(0).getRating());
    }

}
