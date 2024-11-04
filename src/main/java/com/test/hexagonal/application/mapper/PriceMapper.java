package com.test.hexagonal.application.mapper;

import com.test.hexagonal.application.dto.PriceDTO;
import com.test.hexagonal.domain.model.Price;

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
}
