package com.romario.circulationservicetest.presentation.presenter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "dayNumberId")
@ToString(of = "dayNumberId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NumberDayPresenter {

    private UUID dayNumberId;
    private Integer number;

}

