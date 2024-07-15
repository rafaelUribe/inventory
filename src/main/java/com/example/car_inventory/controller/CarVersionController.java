package com.example.car_inventory.controller;

import com.example.car_inventory.model.CarModel;
import com.example.car_inventory.model.CarVersion;
import com.example.car_inventory.service.CarModelService;
import com.example.car_inventory.service.CarVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars/car-versions")
public class CarVersionController {

    @Autowired
    private CarVersionService carVersionService;

    @Autowired
    private CarModelService carModelService;

    @PostMapping
    public ResponseEntity<?> createCarVersion(@RequestParam String versionName, @RequestParam Long carModelId) {
        Optional<CarModel> carModelOptional = carModelService.getCarModelById(carModelId);
        if (!carModelOptional.isPresent()) {
            return ResponseEntity.status(404).body("Car Model not found");
        }
        CarModel carModel = carModelOptional.get();
        CarVersion carVersion = CarVersion.builder()
                .versionName(versionName)
                .carModel(carModel)
                .fullName(carModel.getBrand().getName() + " " + carModel.getName() + " " + versionName)
                .build();
        return ResponseEntity.ok(carVersionService.createCarVersion(carVersion));
    }

    @GetMapping
    public ResponseEntity<List<CarVersion>> getAllCarVersions() {
        return ResponseEntity.ok(carVersionService.getAllCarVersions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarVersion> getCarVersionById(@PathVariable Long id) {
        return carVersionService.getCarVersionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/byModel/{id}")
    public ResponseEntity<List<CarVersion>> getCarVersionByModelId(@PathVariable Long id) {
        return ResponseEntity.ok(carVersionService.getCarVersionByModelId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarVersion> updateCarVersion(@PathVariable Long id, @RequestBody CarVersion carVersionDetails) {
        return ResponseEntity.ok(carVersionService.updateCarVersion(id, carVersionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarVersion(@PathVariable Long id) {
        carVersionService.deleteCarVersion(id);
        return ResponseEntity.noContent().build();
    }


}
