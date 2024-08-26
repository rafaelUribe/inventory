package com.example.car_inventory.repository;

import com.example.car_inventory.model.ElasticCar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElasticCarRepository extends ElasticsearchRepository<ElasticCar, String> {

    Optional<ElasticCar> findByName(String name);

    Optional<ElasticCar> findById(String id);

    List<ElasticCar> findByVersion(String version);

    List<ElasticCar> findByVisible(Boolean visible);

    ElasticCar save(ElasticCar elasticCar);
}
