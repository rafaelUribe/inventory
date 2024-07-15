package com.example.car_inventory.service.Impl;


import com.example.car_inventory.repository.CarVersionRepository;
import com.example.car_inventory.service.CarVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.car_inventory.model.CarVersion;

import java.util.List;
import java.util.Optional;

@Service
public class CarVersionServiceImpl implements CarVersionService {

    @Autowired
    private CarVersionRepository carVersionRepository;

    @Override
    public CarVersion createCarVersion(CarVersion carVersion) {
        return  carVersionRepository.save(carVersion);
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
        carVersion.setCarModel(carVersionDetails.getCarModel());
        carVersion.setFullName(carVersionDetails.getCarModel().getBrand().getName() + " " + carVersionDetails.getCarModel().getName() + " " + carVersionDetails.getVersionName());
        return carVersionRepository.save(carVersion);
    }

    @Override
    public void deleteCarVersion(Long id) {
        CarVersion carVersion = carVersionRepository.findById(id).orElseThrow(() -> new RuntimeException("Car Version not found"));
        carVersionRepository.delete(carVersion);
    }

}
