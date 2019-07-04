package com.championships.Championships.cars.repository;

import com.championships.Championships.cars.model.CarDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<CarDocument, String> {

    CarDocument getById(String id);

    CarDocument getByMake(String make);
}
