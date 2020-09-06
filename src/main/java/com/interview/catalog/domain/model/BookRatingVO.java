package com.interview.catalog.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookRatingVO {
    private Long bookId;
    private BigDecimal rating;
}
