package com.example.car_inventory.service.Impl;


import com.example.car_inventory.model.CreateCarRequest;
import com.example.car_inventory.repository.CarVersionRepository;
import com.example.car_inventory.service.CarVersionService;
import com.example.car_inventory.service.ElasticCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.car_inventory.model.CarVersion;

import java.util.List;
import java.util.Optional;

@Service
public class CarVersionServiceImpl implements CarVersionService {

    @Autowired
    private CarVersionRepository carVersionRepository;


    @Autowired
    private ElasticCarService elasticCarService;


    @Override
    public CarVersion createCarVersion(CarVersion carVersion) {
        carVersion.setInventory(0L);
        CarVersion savedCarVersion = carVersionRepository.save(carVersion);

        CreateCarRequest request = CreateCarRequest.builder()
                .name(savedCarVersion.getFullName())
                .brand(savedCarVersion.getCarModel().getBrand().getName())
                .model(savedCarVersion.getCarModel().getName())
                .version(savedCarVersion.getVersionName())
                .visible(true)
                .build();

        elasticCarService.createCar(request);

        return savedCarVersion;
    }

    @Override
    public List<CarVersion> getAllCarVersions() {
        return carVersionRepository.findAll();
    }

    @Override
    public Optional<CarVersion> getCarVersionById(Long id) {
        return carVersionRepository.findById(id);
    }

    @Override
    public List<CarVersion> getCarVersionByModelId(Long id){
        return carVersionRepository.findByCarModelId(id);
    }

    @Override
    public CarVersion updateCarVersion(Long id, CarVersion carVersionDetails) {
        CarVersion carVersion = carVersionRepository.findById(id).orElseThrow(() -> new RuntimeException("Car Version not found"));
        carVersion.setVersionName(carVersionDetails.getVersionName());
        carVersion.setInventory(carVersionDetails.getInventory());
        carVersion.setCarModel(carVersionDetails.getCarModel());
        carVersion.setFullName(carVersionDetails.getCarModel().getBrand().getName() + " " + carVersionDetails.getCarModel().getName() + " " + carVersionDetails.getVersionName());
        return carVersionRepository.save(carVersion);
    }

    @Override
    public void deleteCarVersion(Long id) {
        CarVersion carVersion = carVersionRepository.findById(id).orElseThrow(() -> new RuntimeException("Car Version not found"));
        carVersionRepository.delete(carVersion);
    }

    @Override
    public CarVersion updateInventory(Long id, String operation) {
        CarVersion carVersion = carVersionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car Version not found"));

        if (operation.equalsIgnoreCase("add")) {
            carVersion.setInventory(carVersion.getInventory() + 1);
        } else if (operation.equalsIgnoreCase("subtract")) {
            if (carVersion.getInventory() < 1) {
                throw new IllegalArgumentException("Insufficient inventory to subtract");
            }
            carVersion.setInventory(carVersion.getInventory() - 1);
        } else {
            throw new IllegalArgumentException("Invalid operation. Use 'add' or 'subtract'.");
        }

        return carVersionRepository.save(carVersion);
    }

}
