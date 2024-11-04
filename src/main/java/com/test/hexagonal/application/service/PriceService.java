package com.test.hexagonal.application.service;

import com.test.hexagonal.application.dto.PriceDTO;
import com.test.hexagonal.application.mapper.PriceMapper;
import com.test.hexagonal.domain.model.Price;
import com.test.hexagonal.domain.repository.PriceRepository;
import com.test.hexagonal.infrastructure.exception.PriceNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<PriceDTO> findPrice(Long brandId, Long productId, LocalDateTime date) {
        Optional<Price> price = priceRepository.findPriceByBrandAndProductAndDate(brandId, productId, date);
        if (price.isEmpty()) {
            throw new PriceNotFoundException(productId, brandId, date.toString());
        }
        return price.map(PriceMapper::toDTO);
    }
}
