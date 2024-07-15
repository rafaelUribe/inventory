package com.example.car_inventory.service.Impl;

import com.example.car_inventory.model.Brand;
import com.example.car_inventory.repository.BrandRepository;
import com.example.car_inventory.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand updateBrand(Long id, Brand brandDetails) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setName(brandDetails.getName());
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
        brandRepository.delete(brand);
    }
}
