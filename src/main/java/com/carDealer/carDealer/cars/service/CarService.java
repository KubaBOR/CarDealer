package com.carDealer.carDealer.cars.service;

import com.carDealer.carDealer.cars.dto.Car;
import com.carDealer.carDealer.cars.dto.Make;
import com.carDealer.carDealer.cars.model.CarDocument;
import com.carDealer.carDealer.cars.model.MakeDocument;
import com.carDealer.carDealer.cars.repository.CarRepository;
import com.carDealer.carDealer.cars.repository.MakeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final MakeRepository makeRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CarService(CarRepository carRepository, MakeRepository makeRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.makeRepository = makeRepository;
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
        /*List<CarDocument> allCars = carRepository.findAll();
        List<Car> collect = allCars.stream()
                .map(carDocument -> modelMapper.map(carDocument, Car.class))
                .collect(Collectors.toList());
        return collect;*/
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, "make")).stream()
                .map(carDocument -> modelMapper.map(carDocument, Car.class))
                .collect(Collectors.toList());
    }
/**
 * @deprecated
 * */
    public String saveMake(Make make) {
        MakeDocument makeDocument = modelMapper.map(make, MakeDocument.class);
        return makeRepository.save(makeDocument).getId();
    }

    public List<Make> getAllMakes(){
        return makeRepository.findAll(Sort.by(Sort.Direction.ASC, "name")).stream()
                .map(makeDocument -> modelMapper.map(makeDocument, Make.class))
                .collect(Collectors.toList());
    }

    @PostConstruct
    private void addBasicCars(){
        if (carRepository.count()<10) {
            carRepository.deleteAll();
            carRepository.save(new CarDocument("BMW", "320i", "2.0L", 184, 156000));
            carRepository.save(new CarDocument("BMW", "330i", "2.0L", 258, 178000));
            carRepository.save(new CarDocument("BMW", "M340i", "3,0L", 374, 277000));
            carRepository.save(new CarDocument("BMW", "318d", "2.0L", 150, 149000));
            carRepository.save(new CarDocument("BMW", "320d", "2.0L", 190, 161500));
            carRepository.save(new CarDocument("BMW", "330d", "3.0L", 265, 227000));
        }
    }

}
