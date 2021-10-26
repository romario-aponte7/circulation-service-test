package com.romario.circulationservicetest.presentation.controller;

import com.romario.circulationservicetest.presentation.presenter.CarPresenter;
import com.romario.circulationservicetest.presentation.presenter.DayCirculationPresenter;
import com.romario.circulationservicetest.presentation.presenter.MessagePresenter;
import com.romario.circulationservicetest.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.management.JMRuntimeException;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
public class CarController {

    @Autowired
    private CarsService carsService;

    @GetMapping(value = "/loadCars")
    public Set<CarPresenter> loadCars() {
        return carsService.loadCars();
    }


    @GetMapping(value = "/getCarById")
    public MessagePresenter getCarById(@RequestParam("carId") String  carId,
                                       @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date date)
            throws ValidationException, IOException {

        return carsService.getCarById(UUID.fromString(carId), date);
    }

    @PostMapping(value = "/saveNewCar")
    @ResponseBody
    public CarPresenter saveNewCar(@RequestBody CarPresenter carPresenter) throws ValidationException, IOException {
        return carsService.saveNewCar(carPresenter);
    }

    @PostMapping(value = "/saveDaysConfig")
    @ResponseBody
    public void saveDaysConfig(@RequestBody List<DayCirculationPresenter> dayCirculationPresenters) throws ValidationException, IOException {
        carsService.saveDaysConfig(dayCirculationPresenters);
    }


}
