package com.romario.circulationservicetest.repository;

import com.romario.circulationservicetest.entity.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DayRepository extends CrudRepository<Day, UUID> {

    List<Day> findByDay(String day);
}
