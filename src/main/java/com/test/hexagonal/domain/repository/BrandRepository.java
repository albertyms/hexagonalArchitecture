package com.test.hexagonal.domain.repository;

import com.test.hexagonal.domain.model.Brand;

public interface BrandRepository {
    Brand findById(Long id);
}
