package com.interview.catalog.integration;

import com.interview.catalog.domain.model.BookInfoVO;

import java.util.List;

public interface BookInfoService {
    BookInfoVO retrieveBooksInfo(Long bookId);
    List<BookInfoVO> retrieveBooksInfo(List<Long> bookIds);
}
