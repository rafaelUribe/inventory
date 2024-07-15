package com.example.car_inventory.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version_name")
    private String versionName;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    private CarModel carModel;

    private Long inventory;

}
