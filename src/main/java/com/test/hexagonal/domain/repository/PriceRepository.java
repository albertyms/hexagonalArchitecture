package com.test.hexagonal.domain.repository;

import com.test.hexagonal.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findPriceByBrandAndProductAndDate(Long brandId, Long productId, LocalDateTime date);
}
