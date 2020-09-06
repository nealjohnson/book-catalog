package com.interview.catalog.controller.info.unit.integration;

import com.google.common.collect.Lists;
import com.interview.catalog.domain.model.BookCatalogVO;
import com.interview.catalog.domain.model.BookInfoVO;
import com.interview.catalog.domain.model.BookRatingVO;
import com.interview.catalog.integration.BookInfoService;
import com.interview.catalog.integration.BookRatingService;
import com.interview.catalog.integration.impl.BookInfoServiceImpl;
import com.interview.catalog.service.BookCatalogService;
import com.interview.catalog.service.impl.BookCatalogServiceImpl;
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
public class BookInfoServiceTest {


    public BookInfoService service;
    @Mock
    public RestTemplate restTemplate;
    @Before
    public void prepare(){
        service= new BookInfoServiceImpl(restTemplate);
    }

    @Test
    public void testRetrieveBooksInfoForBookId() {
        Long bookId =1l;
        String bookName = "book1";
        BookInfoVO bookInfoVO =new BookInfoVO();
        bookInfoVO.setName(bookName);
        bookInfoVO.setId(bookId);
        ReflectionTestUtils.setField(service,"bookInfoUri","http://test-demo/");
        Mockito.when(restTemplate.getForObject(Mockito.isA(URI.class), Mockito.isA(Class.class))).thenReturn(bookInfoVO);
        BookInfoVO result = service.retrieveBooksInfo(bookId);
        Assert.assertEquals(bookName,result.getName());
        Assert.assertEquals(bookId,result.getId());
    }


    @Test
    public void testRetrieveBooksInfoForMultiple() {
        Long bookId =1l;
        String bookName = "book1";
        BookInfoVO bookInfoVO =new BookInfoVO();
        bookInfoVO.setName(bookName);
        bookInfoVO.setId(bookId);
        ReflectionTestUtils.setField(service,"bookInfoUriMultiple","http://test-demo/");
        Mockito.when(restTemplate.getForObject(Mockito.isA(URI.class), Mockito.isA(Class.class))).thenReturn(new BookInfoVO[]{bookInfoVO});
        List<BookInfoVO> result = service.retrieveBooksInfo(Lists.newArrayList(bookId));
        Assert.assertEquals(bookName,result.get(0).getName());
        Assert.assertEquals(bookId,result.get(0).getId());
    }
}
