package com.example.car_inventory.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCarRequest {

    private String name;
    private String version;
    private String model;
    private String brand;
    private Boolean visible;
}
