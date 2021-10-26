package com.romario.circulationservicetest.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
@Builder
public class Car {
    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private UUID carId;

    @NotNull
    private String licensePlate;

    private Date enrollmentDate;

    private String brand;

    private String type;

    @NotNull
    private String color;

    private String originCountry;

    @NotNull
    private String motor;

    @NotNull
    private String chassis;

}
