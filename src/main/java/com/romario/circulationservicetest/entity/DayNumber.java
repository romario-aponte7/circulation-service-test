package com.romario.circulationservicetest.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="day_numbers")
@Getter
@Setter
@EqualsAndHashCode(of = "dayNumberId")
@ToString(of = "dayNumberId")
@Builder
public class DayNumber {
    @Id
    @GeneratedValue
    private UUID dayNumberId;
    @Column(unique = true)
    @NotNull
    private Integer number;


    @JoinTable(
            name = "days_day_numbers",
            joinColumns = @JoinColumn(name = "day_number_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "day_id", nullable = false)
    )
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Day> days;
}
