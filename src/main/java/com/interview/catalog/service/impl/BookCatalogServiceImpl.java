package com.interview.catalog.service.impl;

import com.interview.catalog.domain.model.BookCatalogVO;
import com.interview.catalog.domain.model.BookInfoVO;
import com.interview.catalog.domain.model.BookRatingVO;
import com.interview.catalog.integration.BookInfoService;
import com.interview.catalog.integration.BookRatingService;
import com.interview.catalog.service.BookCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookCatalogServiceImpl implements BookCatalogService {
    private BookInfoService bookInfoService;
    private BookRatingService bookRatingService;

    @Autowired
    public BookCatalogServiceImpl(BookInfoService bookInfoService, BookRatingService bookRatingService) {
        this.bookInfoService = bookInfoService;
        this.bookRatingService = bookRatingService;
    }

    @Override
    public List<BookCatalogVO> fetch(Long userId) {
        List<BookRatingVO> bookRatings = bookRatingService.fetchBookRatingsOfUser(userId);
        List<Long> bookIds = bookRatings.stream().map(bookRatingVO -> bookRatingVO.getBookId()).collect(Collectors.toList());
        List<BookInfoVO> booksInfoResult = bookInfoService.retrieveBooksInfo(bookIds);

        Map<Long, BookCatalogVO> mergedMap = bookRatings.stream().map(bookRatingVO -> apply(bookRatingVO)).collect(Collectors.toMap(BookCatalogVO::getBookId, Function.identity()));
        booksInfoResult.forEach(bookInfoVO -> mergedMap.get(bookInfoVO.getId()).setName(bookInfoVO.getName()));
        return new ArrayList<>(mergedMap.values());
    }


    private  BookCatalogVO apply(BookRatingVO bookRatingVO) {
        BookCatalogVO bookCatalogVO = new BookCatalogVO();
        bookCatalogVO.setBookId(bookRatingVO.getBookId());
        bookCatalogVO.setRating(bookRatingVO.getRating());
        return bookCatalogVO;
    }
}
