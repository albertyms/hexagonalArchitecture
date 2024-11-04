package com.test.hexagonal.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PriceDTO {
    private Long id;
    private Long brandId;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
