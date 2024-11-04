package com.test.hexagonal.infrastructure.configuration;


import com.test.hexagonal.application.service.PriceService;
import com.test.hexagonal.domain.repository.PriceRepository;
import com.test.hexagonal.infrastructure.persistence.adapter.PricePersistenceAdapter;
import com.test.hexagonal.infrastructure.persistence.repository.JpaPriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfiguration {

    private final JpaPriceRepository jpaPriceRepository;

    public JpaConfiguration(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Bean
    public PriceRepository priceRepository() {
        return new PricePersistenceAdapter(jpaPriceRepository);
    }

    @Bean
    public PriceService priceService(PriceRepository priceRepository) {
        return new PriceService(priceRepository);
    }
}
