package com.interview.catalog.controller.info.unit.service;

import com.google.common.collect.Lists;
import com.interview.catalog.domain.model.BookCatalogVO;
import com.interview.catalog.domain.model.BookInfoVO;
import com.interview.catalog.domain.model.BookRatingVO;
import com.interview.catalog.integration.BookInfoService;
import com.interview.catalog.integration.BookRatingService;
import com.interview.catalog.service.BookCatalogService;
import com.interview.catalog.service.impl.BookCatalogServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BookCatalogServiceTest {


    public BookCatalogService service;
    @Mock
    public BookInfoService bookInfoService;
    @Mock
    public BookRatingService bookRatingService;
    @Before
    public void prepare(){
        service= new BookCatalogServiceImpl(bookInfoService,bookRatingService);
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

        BookRatingVO bookRatingVO =new BookRatingVO();
        bookRatingVO.setBookId(bookId);
        bookRatingVO.setRating(rating);

        BookInfoVO bookInfoVO =new BookInfoVO();
        bookInfoVO.setName(bookName);
        bookInfoVO.setId(bookId);

        Mockito.when(bookRatingService.fetchBookRatingsOfUser(userId)).thenReturn(Lists.newArrayList(bookRatingVO));
        Mockito.when(bookInfoService.retrieveBooksInfo(Lists.newArrayList(bookId))).thenReturn(Lists.newArrayList(bookInfoVO));

        List<BookCatalogVO> result = service.fetch(userId);

        Assert.assertEquals(bookName,result.get(0).getName());
        Assert.assertEquals(bookId,result.get(0).getBookId());
        Assert.assertEquals(rating,result.get(0).getRating());
    }

}
