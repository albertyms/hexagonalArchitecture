package com.test.hexagonal.infrastructure.persistence.adapter;

import com.test.hexagonal.domain.model.Brand;
import com.test.hexagonal.domain.model.Price;
import com.test.hexagonal.domain.repository.PriceRepository;
import com.test.hexagonal.infrastructure.persistence.entity.PriceEntity;
import com.test.hexagonal.infrastructure.persistence.repository.JpaPriceRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class PricePersistenceAdapter implements PriceRepository {
    private final JpaPriceRepository jpaPriceRepository;

    public PricePersistenceAdapter(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public Optional<Price> findPriceByBrandAndProductAndDate(Long brandId, Long productId, LocalDateTime date) {
        return jpaPriceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, date, date)
                .map(this::toDomain);
    }

    private Price toDomain(PriceEntity entity) {
        // Convert PriceEntity to Price (Domain object)
        return new Price(
                entity.getId(),
                new Brand(entity.getBrand().getId(), entity.getBrand().getName()),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency(),
                entity.getStartDate(),
                entity.getEndDate()
        );
    }
}
