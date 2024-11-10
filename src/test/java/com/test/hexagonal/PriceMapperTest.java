package com.test.hexagonal;

import com.test.hexagonal.application.dto.PriceDTO;
import com.test.hexagonal.application.mapper.PriceMapper;
import com.test.hexagonal.domain.model.Brand;
import com.test.hexagonal.domain.model.Price;
import com.test.hexagonal.infrastructure.persistence.entity.BrandEntity;
import com.test.hexagonal.infrastructure.persistence.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceMapperTest {

    @Test
    void testMapper_Price_toDto() {
        Long productId = 100L;
        LocalDateTime date = LocalDateTime.of(2023, 7, 14, 10, 0);

        Brand brand = new Brand(1L, "ZARA");
        Price highPriorityPrice = new Price(1L, brand, 1, productId, 1, BigDecimal.valueOf(35.25), "EUR", date.minusDays(1), date.plusDays(1));
        Price lowPriorityPrice = new Price(2L, brand, 1, productId, 0, BigDecimal.valueOf(25.25), "EUR", date.minusDays(1), date.plusDays(1));

        List<Price> prices = new ArrayList<>();
        prices.add(highPriorityPrice);
        prices.add(lowPriorityPrice);

        List<PriceDTO> pricesDTO = prices.stream().map(PriceMapper::toDTO).toList();

        assertFalse(pricesDTO.isEmpty());
        assertEquals(pricesDTO.size(), 2);

        assertInstanceOf(PriceDTO.class, pricesDTO.get(0));
        assertEquals(pricesDTO.get(0).getId(), 1L);
        assertEquals(pricesDTO.get(0).getPrice(), BigDecimal.valueOf(35.25));

        assertInstanceOf(PriceDTO.class, pricesDTO.get(1));
        assertEquals(pricesDTO.get(1).getId(), 2L);
        assertEquals(pricesDTO.get(1).getPrice(), BigDecimal.valueOf(25.25));

    }


    @Test
    void testMapper_Price_toDomain() {
        Long productId = 100L;
        LocalDateTime date = LocalDateTime.of(2023, 7, 14, 10, 0);

        BrandEntity brandEntity = new BrandEntity(1L, "ZARA");

        PriceEntity highPriorityPriceEntity = new PriceEntity(1L, brandEntity,  date.minusDays(1),  date.plusDays(1),1, productId, 1, BigDecimal.valueOf(35.25), "EUR");
        PriceEntity lowPriorityPriceEntity = new PriceEntity(2L, brandEntity,  date.minusDays(1),  date.plusDays(1),1, productId, 0, BigDecimal.valueOf(25.25), "EUR");

        List<PriceEntity> pricesEntity = new ArrayList<>();
        pricesEntity.add(highPriorityPriceEntity);
        pricesEntity.add(lowPriorityPriceEntity);

        List<Price> pricesDTO = pricesEntity.stream().map(PriceMapper::toDomain).toList();

        assertFalse(pricesDTO.isEmpty());
        assertEquals(pricesDTO.size(), 2);

        assertInstanceOf(Price.class, pricesDTO.get(0));
        assertEquals(pricesDTO.get(0).getId(), 1L);
        assertEquals(pricesDTO.get(0).getPrice(), BigDecimal.valueOf(35.25));

        assertInstanceOf(Price.class, pricesDTO.get(1));
        assertEquals(pricesDTO.get(1).getId(), 2L);
        assertEquals(pricesDTO.get(1).getPrice(), BigDecimal.valueOf(25.25));

    }

}
