package com.test.hexagonal.infrastructure.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String message) {
        super(message);
    }

    public PriceNotFoundException(Long id) {
        super("No price found for ID: " + id);
    }

    public PriceNotFoundException(Long productId, Long brandId, String date) {
        super("No price found for product ID:" +  productId +  " brand ID: " + brandId + " on date: " + date);
    }
}
