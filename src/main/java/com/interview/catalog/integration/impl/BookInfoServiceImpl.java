package com.interview.catalog.integration.impl;

import com.interview.catalog.domain.model.BookInfoVO;
import com.interview.catalog.integration.BookInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Value("${bookInfo.each-book}")
    private String bookInfoUri;
    @Value("${bookInfo.multiple-books}")
    private String bookInfoUriMultiple;

    private RestTemplate restTemplate;


    @Override
    public BookInfoVO retrieveBooksInfo(Long bookId) {
        Map<String, Long> param = Map.of("bookId",bookId);
        URI uri = UriComponentsBuilder.fromHttpUrl(bookInfoUri)
                .buildAndExpand(param).encode().toUri();
       return restTemplate.getForObject(uri, BookInfoVO.class);
    }

    @Override
    @HystrixCommand(fallbackMethod = "bookInfoWithEmptyName")
    public List<BookInfoVO> retrieveBooksInfo(List<Long> bookIds) {
        URI uri = UriComponentsBuilder.fromHttpUrl(bookInfoUriMultiple).queryParam("bookId", bookIds)
                .build().encode().toUri();

        BookInfoVO[] result = restTemplate.getForObject(uri, BookInfoVO[].class);
        return Arrays.asList(result);
    }

    //Backup method.
    public List<BookInfoVO> bookInfoWithEmptyName(List<Long> bookIds){
     return bookIds.stream().map(bookId -> {
            BookInfoVO bookInfoVO = new BookInfoVO();
            bookInfoVO.setId(bookId);
            bookInfoVO.setName("");
            return bookInfoVO;
        }).collect(Collectors.toList());
    }

    @Autowired
    public BookInfoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
