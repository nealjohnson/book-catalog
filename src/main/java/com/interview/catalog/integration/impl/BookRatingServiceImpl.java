package com.interview.catalog.integration.impl;

import com.interview.catalog.domain.model.BookRatingVO;

import com.interview.catalog.integration.BookRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BookRatingServiceImpl implements BookRatingService {

    private RestTemplate restTemplate;
    @Value("${bookRating.each-user}")
    private String bookRatingUri;

    @Autowired
    public BookRatingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<BookRatingVO> fetchBookRatingsOfUser(Long userId) {
        Map<String, Long> param = Map.of("userId",userId);
        URI uri = UriComponentsBuilder.fromHttpUrl(bookRatingUri)
                .buildAndExpand(param).encode().toUri();
        BookRatingVO[] result = restTemplate.getForObject(uri, BookRatingVO[].class);
       return Arrays.asList(result);
    }


}
