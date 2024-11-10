package com.test.hexagonal.application.service;

import com.test.hexagonal.application.dto.PriceDTO;
import com.test.hexagonal.application.mapper.PriceMapper;
import com.test.hexagonal.domain.model.Price;
import com.test.hexagonal.domain.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<PriceDTO> findPrice(Long brandId, Long productId, LocalDateTime date) {
        List<Price> prices = priceRepository.findPriceByBrandAndProductAndDate(brandId, productId, date);
        return prices.stream().max(Comparator.comparingInt(Price::getPriority)).map(PriceMapper::toDTO);
    }
}
