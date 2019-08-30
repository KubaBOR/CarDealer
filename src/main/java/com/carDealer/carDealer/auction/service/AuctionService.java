package com.carDealer.carDealer.auction.service;

import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.dto.NewAuctionFormData;
import com.carDealer.carDealer.auction.model.AuctionDocument;
import com.carDealer.carDealer.auction.repository.AuctionRepository;
import com.carDealer.carDealer.cars.model.CarDocument;
import com.carDealer.carDealer.cars.repository.CarRepository;
import com.carDealer.carDealer.configuration.model.ConfigurationDocument;
import com.carDealer.carDealer.configuration.repository.ConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AuctionService {

//    private CarService carService;

    private AuctionRepository auctionRepository;

    private CarRepository carRepository;

    private ConfigurationRepository configurationRepository;

    private ModelMapper modelMapper;

    public AuctionService(
            AuctionRepository auctionRepository,
            CarRepository carRepository, ConfigurationRepository configurationRepository,
            ModelMapper modelMapper) {
        this.auctionRepository = auctionRepository;
        this.carRepository = carRepository;
        this.configurationRepository = configurationRepository;
        this.modelMapper = modelMapper;
    }

    /*public String createNewAuction (AuctionController.NewAuctionCarFormData auction){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        AuctionDocument auctionDocument = modelMapper.map(auction, AuctionDocument.class);

        return auctionRepository.save(auctionDocument).getId();
    }*/

    public String createAuction(Auction auction){
        AuctionDocument auctionDocument = modelMapper.map(auction, AuctionDocument.class);
        return auctionRepository.save(auctionDocument).getId();
    }

    //new method!
    public String addNewAuction (NewAuctionFormData formData) {

        String carId = formData.getCar();
        CarDocument carToSave = carRepository.getById(carId);

        List<String> configurationIds = Arrays.asList(formData.getConfigurations());
        List<ConfigurationDocument> configToSave = configurationRepository.findAll()
                .stream()
                .filter(configurationDocument -> configurationIds.contains(configurationDocument.getId()))
                .collect(Collectors.toList());

        AuctionDocument auctionDocument = new AuctionDocument(
                formData.getDescription(),
                carToSave,
                configToSave,
                formData.getPrice(),
                formData.getProductionYear()
        );

        return auctionRepository.save(auctionDocument).getId();
    }

    public void deleteById(String id){
        auctionRepository.deleteById(id);
    }

    public List<Auction> getAllAuctions(){
        List<AuctionDocument> allAuctions = auctionRepository.findAll();
        List<Auction> collect = allAuctions.stream()
                .map(auctionDocument -> modelMapper.map(auctionDocument, Auction.class))
                .collect(Collectors.toList());
        return collect;
    }
}
