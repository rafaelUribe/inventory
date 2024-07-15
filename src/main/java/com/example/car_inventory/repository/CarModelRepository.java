package com.example.car_inventory.repository;

import com.example.car_inventory.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    List<CarModel> findByBrandId(Long id);
}
