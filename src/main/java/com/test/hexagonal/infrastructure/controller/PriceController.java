package com.test.hexagonal.infrastructure.controller;

import com.test.hexagonal.application.dto.PriceDTO;
import com.test.hexagonal.application.service.PriceService;
import com.test.hexagonal.infrastructure.exception.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceDTO> getPrice(@RequestParam Long brandId, @RequestParam Long productId, @RequestParam LocalDateTime date) {
        try {
            PriceDTO priceDTO = priceService.findPrice(brandId, productId, date)
                    .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date.toString()));
            return ResponseEntity.ok(priceDTO);
        } catch (PriceNotFoundException  e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
