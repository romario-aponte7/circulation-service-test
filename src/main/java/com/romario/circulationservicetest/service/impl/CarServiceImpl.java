package com.romario.circulationservicetest.service.impl;

import com.romario.circulationservicetest.entity.Car;
import com.romario.circulationservicetest.entity.Day;
import com.romario.circulationservicetest.entity.DayNumber;
import com.romario.circulationservicetest.presentation.presenter.CarPresenter;
import com.romario.circulationservicetest.presentation.presenter.DayCirculationPresenter;
import com.romario.circulationservicetest.presentation.presenter.MessagePresenter;
import com.romario.circulationservicetest.repository.CarRepository;
import com.romario.circulationservicetest.repository.DayNumberRepository;
import com.romario.circulationservicetest.repository.DayRepository;
import com.romario.circulationservicetest.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CarServiceImpl implements CarsService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private DayNumberRepository dayNumberRepository;

    @Override
    public Set<CarPresenter> loadCars() {

        Set<CarPresenter> bankPresenters = new HashSet<>();
        Iterable<Car> carSet = carRepository.findAll();
        carSet.forEach(car -> {
            CarPresenter carPresenter = new CarPresenter();
            carPresenter.setCarId(car.getCarId());
            carPresenter.setLicensePlate(car.getLicensePlate());
            bankPresenters.add(carPresenter);
        });

        return bankPresenters;
    }

    ;

    @Override
    public MessagePresenter getCarById(UUID carId, Date date) throws ValidationException {
        Optional<Car> car = carRepository.findById(carId);
        if (!car.isPresent()) {
            throw new ValidationException("No se encontró el vehículo");
        }
        if (date.before(new Date())) {
            throw new ValidationException("La fecha ingresada no puede ser inferior a la fecha actual");
        }

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);


        MessagePresenter messagePresenter = ValidateCirculation(car.get().getLicensePlate(), startDate);

        return messagePresenter;
    }


    private MessagePresenter ValidateCirculation(String licensePlate, Calendar date) throws ValidationException {

        Integer str = Integer.parseInt(licensePlate.substring(licensePlate.length() - 1, licensePlate.length()));
        MessagePresenter messagePresenter = new MessagePresenter();
        if (Calendar.SATURDAY == date.get(Calendar.DAY_OF_WEEK) || Calendar.SUNDAY == date.get(Calendar.DAY_OF_WEEK)) {
            messagePresenter.setLicensePlate(licensePlate);
            messagePresenter.setMessage("Circula fin de semana");

        } else {
            if (Calendar.MONDAY == date.get(Calendar.DAY_OF_WEEK)) {
                List<Day> days = dayRepository.findByDay("lunes");
                days.forEach(car -> {
                    car.getDayNumbers().forEach(dayNumber -> {
                        if (dayNumber.getNumber() == str) {
                            messagePresenter.setLicensePlate(licensePlate);
                            messagePresenter.setMessage("Circula Lunes");
                        }
                    });

                });

            } else if (Calendar.TUESDAY == date.get(Calendar.DAY_OF_WEEK)) {
                List<Day> days = dayRepository.findByDay("martes");
                days.forEach(car -> {
                    car.getDayNumbers().forEach(dayNumber -> {
                        if (dayNumber.getNumber() == str) {
                            messagePresenter.setLicensePlate(licensePlate);
                            messagePresenter.setMessage("Circula Martes");
                        }
                    });

                });

            } else if (Calendar.WEDNESDAY == date.get(Calendar.DAY_OF_WEEK)) {
                List<Day> days = dayRepository.findByDay("miercoles");
                days.forEach(car -> {
                    car.getDayNumbers().forEach(dayNumber -> {
                        if (dayNumber.getNumber() == str) {
                            messagePresenter.setLicensePlate(licensePlate);
                            messagePresenter.setMessage("Circula Miercoles");
                        }
                    });

                });

            } else if (Calendar.THURSDAY == date.get(Calendar.DAY_OF_WEEK)) {
                List<Day> days = dayRepository.findByDay("jueves");
                days.forEach(car -> {
                    car.getDayNumbers().forEach(dayNumber -> {
                        if (dayNumber.getNumber() == str) {
                            messagePresenter.setLicensePlate(licensePlate);
                            messagePresenter.setMessage("Circula Jueves");
                        }
                    });

                });

            } else if (Calendar.FRIDAY == date.get(Calendar.DAY_OF_WEEK)) {
                List<Day> days = dayRepository.findByDay("viernes");
                days.forEach(car -> {
                    car.getDayNumbers().forEach(dayNumber -> {
                        if (dayNumber.getNumber() == str) {
                            messagePresenter.setLicensePlate(licensePlate);
                            messagePresenter.setMessage("Circula Viernes");
                        }
                    });

                });

            }

            if (messagePresenter.getLicensePlate() == null) {
                messagePresenter.setLicensePlate(licensePlate);
                messagePresenter.setMessage(" no circula el día seleccionado");

            }
        }
        return messagePresenter;
    }


    @Override
    public CarPresenter saveNewCar(CarPresenter carPresenter) throws ValidationException {
        Optional<Car> validate = carRepository.findByLicensePlate(carPresenter.getLicensePlate());
        if (validate.isPresent()) {
            throw new ValidationException("La placa ya se encuentra registrada");
        }
        Car car = validateCarFields(carPresenter);
        carRepository.save(car);
        return CarPresenter.builder()
                .licensePlate(car.getLicensePlate())
                .build();
    }

    @Override
    public void saveDaysConfig(List<DayCirculationPresenter> dayCirculationPresenter) throws ValidationException {
        dayNumberRepository.deleteAll();
        dayNumberRepository.deleteAll();
        dayCirculationPresenter.forEach(dayCirc -> {
            if (dayCirc.getNumbers().size() <= 0) {
                try {
                    throw new ValidationException("El día " + dayCirc.getDay() + " debe tener restricción");
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }
            Day day = new Day();
            day.setDay(dayCirc.getDay());
            Day day1 = dayRepository.save(day);
            List<DayNumber> dayNumbers = new ArrayList<>();
            dayCirc.getNumbers().forEach(integer -> {
                DayNumber dayNumber = new DayNumber();
                dayNumber.setDays(Arrays.asList(day1));
                dayNumber.setNumber(integer);
                dayNumbers.add(dayNumber);
                day1.setDayNumbers(dayNumbers);
                dayNumberRepository.save(dayNumber);
            });

            dayRepository.save(day1);
        });


    }


    private Car validateCarFields(CarPresenter carPresenter) throws ValidationException {

        if (carPresenter.getLicensePlate().isBlank()) {
            throw new ValidationException("La placa debe ser obligatoria");
        }
        if (carPresenter.getColor().isBlank()) {
            throw new ValidationException("El color debe ser obligatoria");
        }
        if (carPresenter.getMotor().isBlank()) {
            throw new ValidationException("N° de motor debe ser obligatoria");
        }
        if (carPresenter.getChassis().isBlank()) {
            throw new ValidationException("N° de chasis debe ser obligatoria");
        }

        Car car = new Car();
        car.setLicensePlate(carPresenter.getLicensePlate());
        car.setEnrollmentDate(carPresenter.getEnrollmentDate());
        car.setBrand(carPresenter.getBrand());
        car.setType(carPresenter.getType());
        car.setColor(carPresenter.getColor());
        car.setOriginCountry(carPresenter.getOriginCountry());
        car.setMotor(carPresenter.getMotor());
        car.setChassis(carPresenter.getChassis());

        return car;
    }


}
