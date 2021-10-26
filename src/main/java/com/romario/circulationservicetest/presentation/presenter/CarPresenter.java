package com.romario.circulationservicetest.presentation.presenter;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of ="carId")
@ToString(of = "carId")
@Builder
@AllArgsConstructor
public class CarPresenter {

    @NotNull
    private UUID carId;
    private String licensePlate;
    private Date enrollmentDate;
    private String brand;
    private String type;
    private String color;
    private String originCountry;
    private String motor;
    private String chassis;


}
