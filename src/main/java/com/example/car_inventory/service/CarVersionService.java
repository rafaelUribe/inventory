package com.example.car_inventory.service;

import com.example.car_inventory.model.CarVersion;

import java.util.List;
import java.util.Optional;

public interface CarVersionService {
    CarVersion createCarVersion(CarVersion carVersion);

    List<CarVersion> getAllCarVersions();

    Optional<CarVersion> getCarVersionById(Long id);

    List<CarVersion> getCarVersionByModelId(Long id);

    CarVersion updateCarVersion(Long id, CarVersion carVersionDetails);

    void deleteCarVersion(Long id);

    CarVersion updateInventory(Long id, String operation);

}
