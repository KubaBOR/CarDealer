package com.carDealer.carDealer.cars.repository;

import com.carDealer.carDealer.cars.model.MakeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MakeRepository extends MongoRepository<MakeDocument, String> {

    MakeDocument getById (String id);
}
