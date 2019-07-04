package com.championships.Championships.configuration.service;

import com.championships.Championships.configuration.dto.Configuration;
import com.championships.Championships.configuration.model.ConfigurationDocument;
import com.championships.Championships.configuration.repository.ConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository, ModelMapper modelMapper) {
        this.configurationRepository = configurationRepository;
        this.modelMapper = modelMapper;
    }



    public void deleteById (String id) {
        configurationRepository.deleteById(id);
    }

    public String saveAddon (Configuration configuration) {

        ConfigurationDocument configurationDocument = modelMapper.map(configuration, ConfigurationDocument.class);

        return configurationRepository.save(configurationDocument).getId();
    }

//    public Configuration getConfigurationById (String id){}

    public List<Configuration> getAllConfigurations(){
        return configurationRepository.findAll(Sort.by(Sort.Direction.ASC, "addon")).stream()
                .map(configurationDocument -> modelMapper.map(configurationDocument, Configuration.class))
                .collect(Collectors.toList());
    }
}
