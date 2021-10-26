package com.romario.circulationservicetest.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="days")
@Getter
@Setter
@EqualsAndHashCode(of = "dayId")
@ToString(of = "dayId")
@Builder
public class Day {

    @Id
    @GeneratedValue
    private UUID dayId;
    @Column(unique=true)
    @NotNull
    private String day;

    @ManyToMany(mappedBy = "days")
    private List<DayNumber> dayNumbers;
}
