package com.championships.Championships.cars.service;

import com.championships.Championships.cars.dto.Car;
import com.championships.Championships.cars.model.CarDocument;
import com.championships.Championships.cars.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CarService(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    private String seraviceName;

    public void deleteById(String id){
        carRepository.deleteById(id);
    }
    public String saveCar(Car car){
        CarDocument carDocument = modelMapper.map(car, CarDocument.class);

        return carRepository.save(carDocument).getId();
    }
    public Car getCarById (String id) {
        CarDocument foundCar = carRepository.getById(id);
        Car carToReturn = modelMapper.map(foundCar, Car.class);
        return carToReturn;
    }

    public Car getCarByMake (String make) {
        CarDocument foundMake = carRepository.getByMake(make);
        Car carToReturn = modelMapper.map(foundMake, Car.class);
        return carToReturn;
    }

    public List<Car> getAllCars(){
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, "make")).stream()
                .map(carDocument -> modelMapper.map(carDocument, Car.class))
                .collect(Collectors.toList());
    }
}
