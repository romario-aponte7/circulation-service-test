package com.romario.circulationservicetest.service;

import com.romario.circulationservicetest.presentation.presenter.CarPresenter;
import com.romario.circulationservicetest.presentation.presenter.DayCirculationPresenter;
import com.romario.circulationservicetest.presentation.presenter.MessagePresenter;
import com.sun.istack.NotNull;

import javax.management.JMRuntimeException;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.*;


public interface CarsService {

    Set<CarPresenter> loadCars();

    MessagePresenter getCarById(@NotNull UUID transferId, Date date) throws IOException, JMRuntimeException, ValidationException;

    CarPresenter saveNewCar(CarPresenter carPresenter) throws IOException, JMRuntimeException, ValidationException;

    void saveDaysConfig(List<DayCirculationPresenter> dayCirculationPresenter) throws IOException, JMRuntimeException, ValidationException;

}
