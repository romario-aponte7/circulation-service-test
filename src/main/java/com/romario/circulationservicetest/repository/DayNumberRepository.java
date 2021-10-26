package com.romario.circulationservicetest.repository;

import com.romario.circulationservicetest.entity.Car;
import com.romario.circulationservicetest.entity.DayNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DayNumberRepository extends CrudRepository<DayNumber, UUID> {

    Optional<Car> findByNumber(Integer number);

}
