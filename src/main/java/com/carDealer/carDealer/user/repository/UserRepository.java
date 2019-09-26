package com.carDealer.carDealer.user.repository;

import com.carDealer.carDealer.user.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    UserDocument getById(String id);
    UserDocument getByEmail(String email);
}
