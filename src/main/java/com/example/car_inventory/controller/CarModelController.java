package com.example.car_inventory.controller;

import com.example.car_inventory.model.Brand;
import com.example.car_inventory.model.CarModel;
import com.example.car_inventory.service.BrandService;
import com.example.car_inventory.service.CarModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars/car-models")
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private BrandService brandService;

    @PostMapping()
    public ResponseEntity<CarModel> createCarModel(@RequestBody CarModel carModel, @RequestParam Long brandId) {
        Brand brand = brandService.getBrandById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        carModel.setBrand(brand);
        CarModel savedCarModel = carModelService.createCarModel(carModel);
        return ResponseEntity.ok(savedCarModel);
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCarModels() {
        return ResponseEntity.ok(carModelService.getAllCarModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarModelById(@PathVariable Long id) {
        return carModelService.getCarModelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/byBrand/{id}")
    public ResponseEntity<List<CarModel>> getCarModelsByBrandId(@PathVariable Long id) {
        List<CarModel> carModels = carModelService.getCarModelsByBrandId(id);
        return ResponseEntity.ok(carModels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCarModel(@PathVariable Long id, @RequestBody CarModel carModelDetails) {
        return ResponseEntity.ok(carModelService.updateCarModel(id, carModelDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        carModelService.deleteCarModel(id);
        return ResponseEntity.noContent().build();
    }
}
