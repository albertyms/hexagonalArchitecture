package com.test.hexagonal;

import com.test.hexagonal.application.dto.PriceDTO;
import com.test.hexagonal.application.mapper.PriceMapper;
import com.test.hexagonal.application.service.PriceService;
import com.test.hexagonal.domain.model.Brand;
import com.test.hexagonal.domain.model.Price;
import com.test.hexagonal.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        priceRepository = Mockito.mock(PriceRepository.class);
        priceService = new PriceService(priceRepository);
    }

    @Test
    void testFindPrice_WhenPriceExists_ShouldReturnPriceWithHighestPriority() {
        Long brandId = 1L;
        Long productId = 100L;
        LocalDateTime date = LocalDateTime.of(2023, 7, 14, 10, 0);

        Brand brand = new Brand(1L, "ZARA");
        Price highPriorityPrice = new Price(1L, brand, 1, productId, 1, BigDecimal.valueOf(35.25), "EUR", date.minusDays(1), date.plusDays(1));
        Price lowPriorityPrice = new Price(2L, brand, 1, productId, 0, BigDecimal.valueOf(25.25), "EUR", date.minusDays(1), date.plusDays(1));

        List<Price> prices = new ArrayList<>();
        prices.add(highPriorityPrice);
        prices.add(lowPriorityPrice);

        Optional<PriceDTO> priceDTO = prices.stream().max(Comparator.comparingInt(Price::getPriority)).map(PriceMapper::toDTO);

        when(priceRepository.findPriceByBrandAndProductAndDate(brandId, productId, date)).thenReturn(prices);

        Optional<PriceDTO> result = priceService.findPrice(brandId, productId, date);

        assertTrue(result.isPresent(), "The price should be present");
        assertEquals(priceDTO.get(), result.get());
        assertEquals(result.get().getId(), 1L);
        assertEquals(result.get().getBrandId(), 1L);
        assertEquals(result.get().getPrice(),BigDecimal.valueOf(35.25));
        assertEquals(result.get().getStartDate(), LocalDateTime.of(2023, 7, 13, 10, 0));
        assertEquals(result.get().getEndDate(), LocalDateTime.of(2023, 7, 15, 10, 0));

    }


    @Test
    void testFindPrice_WhenPriceExists_ShouldReturnPriceWithLowestPriority() {
        Long brandId = 1L;
        Long productId = 100L;
        LocalDateTime date = LocalDateTime.of(2023, 7, 14, 16, 0);

        Brand brand = new Brand(1L, "ZARA");
        Price lowPriorityPrice = new Price(2L, brand, 1, productId, 0, BigDecimal.valueOf(25.25), "EUR", date.minusDays(1), date.plusDays(1));

        List<Price> prices = new ArrayList<>();
        prices.add(lowPriorityPrice);

        Optional<PriceDTO> priceDTO = prices.stream().max(Comparator.comparingInt(Price::getPriority)).map(PriceMapper::toDTO);

        when(priceRepository.findPriceByBrandAndProductAndDate(brandId, productId, date)).thenReturn(prices);

        Optional<PriceDTO> result = priceService.findPrice(brandId, productId, date);

        assertTrue(result.isPresent(), "The price should be present");
        assertEquals(priceDTO.get(), result.get());
        assertEquals(result.get().getId(), 2L);
        assertEquals(result.get().getBrandId(), 1L);
        assertEquals(result.get().getPrice(),BigDecimal.valueOf(25.25));
        assertEquals(result.get().getStartDate(), LocalDateTime.of(2023, 7, 13, 16, 0));
        assertEquals(result.get().getEndDate(), LocalDateTime.of(2023, 7, 15, 16, 0));

    }

    @Test
    void testFindPrice_WhenPriceDoesNotExists_ShouldReturnEmptyList() {
        Long brandId = 1L;
        Long productId = 100L;
        LocalDateTime date = LocalDateTime.of(2023, 7, 14, 16, 0);

        List<Price> prices = new ArrayList<>();

        when(priceRepository.findPriceByBrandAndProductAndDate(brandId, productId, date)).thenReturn(prices);

        Optional<PriceDTO> result = priceService.findPrice(brandId, productId, date);

        assertTrue(result.isEmpty(), "Not data was found");

    }


}
