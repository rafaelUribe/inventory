package com.example.car_inventory.repository;

import com.example.car_inventory.model.ElasticCar;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class  ElasticsearchRepository {

    private final String[] nameSearchFields = { "name.search", "name.search._2gram", "name.search._3gram" };

    private final ElasticCarRepository carRepository;
    private final ElasticsearchOperations elasticClient;

    public ElasticCar getById(String id) {
        return carRepository.findById(id).orElse(null);
    }

    public ElasticCar save(ElasticCar car){
        return carRepository.save(car);
    }

    public ElasticCar getByName(String name) {
        return carRepository.findByName(name).orElse(null);
    }

    public List<ElasticCar> getByVersion(String version) {
        return carRepository.findByVersion(version);
    }

    public List<ElasticCar> getVisible() {
        return carRepository.findByVisible(true);
    }

    public List<ElasticCar> searchByName(String namePart) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.multiMatchQuery(namePart, nameSearchFields)
                .type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQuery).build();

        var result = elasticClient.search(searchQuery, ElasticCar.class);
        return result.getSearchHits().stream().map(hit -> hit.getContent()).collect(Collectors.toList());
    }

    public List<ElasticCar> searchByModel(String model) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.multiMatchQuery(model, "model"));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQuery).build();

        var result = elasticClient.search(searchQuery, ElasticCar.class);
        return result.getSearchHits().stream().map(hit -> hit.getContent()).collect(Collectors.toList());
    }

    public List<ElasticCar> searchByDescription(String description) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("name", description));

        NativeSearchQueryBuilder nativeSearchQueryBuilder =
                new NativeSearchQueryBuilder().withQuery(boolQuery);
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        var result = elasticClient.search(query, ElasticCar.class);
        return result.getSearchHits().stream()
                .map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }

}
