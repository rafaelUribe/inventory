package com.example.car_inventory.service;

import com.example.car_inventory.model.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarModelService {
    CarModel createCarModel(CarModel carModel);

    List<CarModel> getAllCarModels();

    Optional<CarModel> getCarModelById(Long id);

    List<CarModel> getCarModelsByBrandId(Long id);

    CarModel updateCarModel(Long id, CarModel carModelDetails);

    void deleteCarModel(Long id);
}
