package com.carDealer.carDealer.configuration.repository;

import com.carDealer.carDealer.configuration.model.ConfigurationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<ConfigurationDocument, String> {

    ConfigurationDocument getById(String id);

    ConfigurationDocument getByAddon(String addon);
}
