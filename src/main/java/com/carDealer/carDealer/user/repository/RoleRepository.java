package com.carDealer.carDealer.user.repository;

import com.carDealer.carDealer.user.model.RoleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<RoleDocument, String> {
    RoleDocument getById(String id);
    RoleDocument getByRole(String role);
}
