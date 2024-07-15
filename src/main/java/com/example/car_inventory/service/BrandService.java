package com.example.car_inventory.service;

import com.example.car_inventory.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Brand createBrand(Brand brand);

    List<Brand> getAllBrands();

    Optional<Brand> getBrandById(Long id);

    Brand updateBrand(Long id, Brand brandDetails);

    void deleteBrand(Long id);
}
