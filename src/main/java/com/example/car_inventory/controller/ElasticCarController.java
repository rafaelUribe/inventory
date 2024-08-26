package com.example.car_inventory.controller;

import com.example.car_inventory.model.ElasticCar;
import com.example.car_inventory.service.ElasticCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/elastic")
public class ElasticCarController {

    @Autowired
    private final ElasticCarService service;

    @GetMapping("/{car}")
    public ResponseEntity<ElasticCar> getProductById(@PathVariable String productId) {
        ElasticCar product = service.getCarById(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cars")
    public ResponseEntity<List<ElasticCar>> getProducts() {
        List<ElasticCar> products = service.getAvailableCars();

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cars/search/as-you-type/{value}")
    public ResponseEntity<List<ElasticCar>> searchByName(@PathVariable String value) {
        List<ElasticCar> cars = service.searchByName(value);

        if (cars != null && !cars.isEmpty()) {
            return ResponseEntity.ok(cars);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/elastic/cars/search/full-text/{term}")
    public ResponseEntity<List<ElasticCar>> searchByDescription(@PathVariable String term) {
        List<ElasticCar> cars = service.searchByDescription(term);
        if (cars == null || cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
}