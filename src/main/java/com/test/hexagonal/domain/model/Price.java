package com.test.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Price {
    private Long id;
    private Brand brand;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
