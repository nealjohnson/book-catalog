package com.interview.catalog.integration;

import com.interview.catalog.domain.model.BookRatingVO;

import java.util.List;

public interface BookRatingService {
    List<BookRatingVO> fetchBookRatingsOfUser(Long userId);
}
