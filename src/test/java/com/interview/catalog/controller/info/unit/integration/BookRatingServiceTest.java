package com.interview.catalog.controller.info.unit.integration;

import com.google.common.collect.Lists;
import com.interview.catalog.domain.model.BookInfoVO;
import com.interview.catalog.domain.model.BookRatingVO;
import com.interview.catalog.integration.BookInfoService;
import com.interview.catalog.integration.BookRatingService;
import com.interview.catalog.integration.impl.BookInfoServiceImpl;
import com.interview.catalog.integration.impl.BookRatingServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BookRatingServiceTest {


    public BookRatingService service;
    @Mock
    public RestTemplate restTemplate;

    @Before
    public void prepare(){
        service= new BookRatingServiceImpl(restTemplate);
    }

    @Test
    public void testRetrieveBooksInfoForBookId() {
        Long userId =1l;
        BigDecimal rating =new BigDecimal("5.50");
        Long bookId =1l;
        BookRatingVO bookRatingVO =new BookRatingVO();
        bookRatingVO.setRating(rating);
        bookRatingVO.setBookId(bookId);
        ReflectionTestUtils.setField(service,"bookRatingUri","http://test-demo/");
        Mockito.when(restTemplate.getForObject(Mockito.isA(URI.class), Mockito.isA(Class.class))).thenReturn(new BookRatingVO[]{bookRatingVO});
        List<BookRatingVO> result = service.fetchBookRatingsOfUser(userId);
        Assert.assertEquals(bookId,result.get(0).getBookId());
        Assert.assertEquals(rating,result.get(0).getRating());
    }
}
