package com.example.car_inventory.repository;

import com.example.car_inventory.model.CarVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarVersionRepository extends JpaRepository<CarVersion, Long> {
    List<CarVersion> findByCarModelId(Long id);

}
