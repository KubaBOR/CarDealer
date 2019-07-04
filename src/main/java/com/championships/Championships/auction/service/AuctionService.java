package com.championships.Championships.auction.service;

import com.championships.Championships.auction.controller.AuctionController;
import com.championships.Championships.auction.dto.Auction;
import com.championships.Championships.auction.model.AuctionDocument;
import com.championships.Championships.auction.repository.AuctionRepository;
import com.championships.Championships.cars.dto.Car;
import com.championships.Championships.cars.model.CarDocument;
import com.championships.Championships.cars.repository.CarRepository;
import com.championships.Championships.cars.service.CarService;
import com.championships.Championships.configuration.dto.Configuration;
import com.championships.Championships.configuration.model.ConfigurationDocument;
import com.championships.Championships.configuration.repository.ConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AuctionService {

    private CarService carService;

    private AuctionRepository auctionRepository;

    private CarRepository carRepository;

    private ConfigurationRepository configurationRepository;

    private ModelMapper modelMapper;

    public AuctionService(CarService carService,
                          AuctionRepository auctionRepository,
                          CarRepository carRepository,
                          ConfigurationRepository configurationRepository,
                          ModelMapper modelMapper) {
        this.carService = carService;
        this.auctionRepository = auctionRepository;
        this.carRepository = carRepository;
        this.configurationRepository = configurationRepository;
        this.modelMapper = modelMapper;
    }

    public String createNewAuction (AuctionController.NewAuctionCarFormData auction){

        AuctionDocument auctionDocument = modelMapper.map(auction, AuctionDocument.class);

        return auctionRepository.save(auctionDocument).getId();
    }

/*    @PostConstruct
    void init(){
        Auction auction1 = new Auction("Super Audi!!", 74000, "2014");
        AuctionDocument auctionDocument = modelMapper.map(auction1, AuctionDocument.class);

        List<CarDocument> allCars = carRepository.findAll();
        List<ConfigurationDocument> allConfig = configurationRepository.findAll();

        auctionDocument.setCarList(allCars);
        auctionDocument.setConfigurationList(allConfig);

        auctionRepository.save(auctionDocument);
    }*/

    public List<Auction> getAllAuctions(){
        List<AuctionDocument> allAuctions = auctionRepository.findAll();
        List<Auction> collect = allAuctions.stream()
                .map(auctionDocument -> modelMapper.map(auctionDocument, Auction.class))
                .collect(Collectors.toList());
        return collect;
    }
}
