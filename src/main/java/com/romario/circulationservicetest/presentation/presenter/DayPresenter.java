package com.romario.circulationservicetest.presentation.presenter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "dayId")
@ToString(of = "dayId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayPresenter {

    private UUID dayId;
    private String day;

}

