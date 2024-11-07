package com.test.hexagonal.application.mapper;

import com.test.hexagonal.application.dto.PriceDTO;
import com.test.hexagonal.domain.model.Brand;
import com.test.hexagonal.domain.model.Price;
import com.test.hexagonal.infrastructure.persistence.entity.PriceEntity;

public class PriceMapper {

    public static PriceDTO toDTO(Price price) {
        return new PriceDTO(
                price.getId(),
                price.getBrand().getId(),
                price.getPrice(),
                price.getStartDate(),
                price.getEndDate()
        );
    }

    public static Price toDomain(PriceEntity entity) {
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
