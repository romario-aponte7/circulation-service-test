package com.romario.circulationservicetest.presentation.presenter;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CarSearchPresenter {
    private String carId;
    private Date date;

}
