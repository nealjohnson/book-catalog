package com.interview.catalog.service;

import com.interview.catalog.domain.model.BookCatalogVO;

import java.util.ArrayList;
import java.util.List;

public interface BookCatalogService {
    List<BookCatalogVO> fetch(Long id);
}
