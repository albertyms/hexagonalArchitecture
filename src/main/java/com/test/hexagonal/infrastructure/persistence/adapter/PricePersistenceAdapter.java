package com.test.hexagonal.infrastructure.persistence.adapter;

import com.test.hexagonal.application.mapper.PriceMapper;
import com.test.hexagonal.domain.model.Price;
import com.test.hexagonal.domain.repository.PriceRepository;
import com.test.hexagonal.infrastructure.persistence.repository.JpaPriceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PricePersistenceAdapter implements PriceRepository {
    private final JpaPriceRepository jpaPriceRepository;

    public PricePersistenceAdapter(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public List<Price> findPriceByBrandAndProductAndDate(Long brandId, Long productId, LocalDateTime date) {
        return jpaPriceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, date, date).stream().map(PriceMapper::toDomain).collect(Collectors.toList());

    }
}
