package com.interview.catalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
public class BookCatalogVO {
    private Long bookId;
    private String name;
    private BigDecimal rating;
}
