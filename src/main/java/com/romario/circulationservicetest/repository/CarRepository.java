package com.romario.circulationservicetest.repository;

import com.romario.circulationservicetest.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends CrudRepository<Car, UUID> {

    Optional<Car> findByLicensePlate(String licensePlate);

}
