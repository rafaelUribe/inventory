package com.example.car_inventory.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "cars", createIndex = true)
public class ElasticCar {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Keyword, name = "name"),
            otherFields = @InnerField(suffix = "search", type = FieldType.Search_As_You_Type))
    private String name;

    @Field(type = FieldType.Keyword, name = "version")
    private String version;

    @Field(type = FieldType.Text, name = "model")
    private String model;

    @Field(type = FieldType.Text, name = "model")
    private String brand;

    @Field(type = FieldType.Boolean, name = "visible")
    private Boolean visible;


}
