package com.test.hexagonal.domain.repository;

import com.test.hexagonal.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findPriceByBrandAndProductAndDate(Long brandId, Long productId, LocalDateTime date);
}
