package com.example.car_inventory.service.Impl;

import com.example.car_inventory.model.CreateCarRequest;
import com.example.car_inventory.model.ElasticCar;
import com.example.car_inventory.repository.ElasticsearchRepository;
import com.example.car_inventory.service.ElasticCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticCarServiceImpl implements ElasticCarService {

    private final ElasticsearchRepository repo;

    @Override
    public ElasticCar getCarById(String carId) {
        return repo.getById(carId);
    }

    @Override
    public ElasticCar getCarByName(String carName) {
        return repo.getByName(carName);
    }

    @Override
    public List<ElasticCar> getCarsByVersion(String version) {
        return repo.getByVersion(version);
    }

    @Override
    public List<ElasticCar> searchByModel(String model) {
        return repo.searchByModel(model);
    }

    @Override
    public List<ElasticCar> searchByName(String carName) {
        return repo.searchByName(carName);
    }

    @Override
    public List<ElasticCar> getAvailableCars() {
        return repo.getVisible();
    }

    @Override
    public List<ElasticCar> searchByDescription(String description) {
        return repo.searchByDescription(description);
    }

    @Override
    public ElasticCar createCar(CreateCarRequest request) {
        if (request != null && StringUtils.hasLength(request.getName().trim())
                && StringUtils.hasLength(request.getModel().trim())
                && StringUtils.hasLength(request.getBrand().trim())
                && StringUtils.hasLength(request.getVersion().trim())
                && request.getVisible() != null) {

            ElasticCar car = ElasticCar.builder()
                    .id(String.valueOf(request.getName().hashCode()))
                    .name(request.getName())
                    .model(request.getModel())
                    .brand(request.getBrand())
                    .version(request.getVersion())
                    .visible(request.getVisible())
                    .build();

            return repo.save(car);
        } else {
            return null;
        }
    }
}