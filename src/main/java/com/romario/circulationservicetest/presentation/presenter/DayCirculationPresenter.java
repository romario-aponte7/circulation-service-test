package com.romario.circulationservicetest.presentation.presenter;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DayCirculationPresenter {
    private String day;
    private List<Integer> numbers;
}
