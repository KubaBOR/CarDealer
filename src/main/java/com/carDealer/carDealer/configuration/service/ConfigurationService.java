package com.carDealer.carDealer.configuration.service;

import com.carDealer.carDealer.configuration.dto.Configuration;
import com.carDealer.carDealer.configuration.model.ConfigurationDocument;
import com.carDealer.carDealer.configuration.repository.ConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void addBasicOptions(){
        if (configurationRepository.count()<10) {
            configurationRepository.deleteAll();
            configurationRepository.save(new ConfigurationDocument("Bluetooth", 0));
            configurationRepository.save(new ConfigurationDocument("Automatic climatisation, 2 Zones", 3000));
            configurationRepository.save(new ConfigurationDocument("Automatic climatisation", 0));
            configurationRepository.save(new ConfigurationDocument("Apple CarPlay / Android Auto", 2000));
            configurationRepository.save(new ConfigurationDocument("Electric seat adjustment", 5000));
            configurationRepository.save(new ConfigurationDocument("Electric heated front seats", 3000));
            configurationRepository.save(new ConfigurationDocument("Electric heated rear seats", 4000));
            configurationRepository.save(new ConfigurationDocument("Leather seats", 7390));
            configurationRepository.save(new ConfigurationDocument("Start-Stop system", 0));
            configurationRepository.save(new ConfigurationDocument("Electric heated steering wheel", 2400));
            configurationRepository.save(new ConfigurationDocument("Navigation System 7\"", 0));
            configurationRepository.save(new ConfigurationDocument("Navigation System 9\"", 1400));
            configurationRepository.save(new ConfigurationDocument("Seat ventilation", 6000));
            configurationRepository.save(new ConfigurationDocument("Keyless start", 500));
            configurationRepository.save(new ConfigurationDocument("Metallic paint", 5500));
            configurationRepository.save(new ConfigurationDocument("Auto Parking", 4250));
            configurationRepository.save(new ConfigurationDocument("Parking assist - full", 2000));
            configurationRepository.save(new ConfigurationDocument("Dusk sensor", 0));
            configurationRepository.save(new ConfigurationDocument("Rain sensor", 0));
        }
    }
}
