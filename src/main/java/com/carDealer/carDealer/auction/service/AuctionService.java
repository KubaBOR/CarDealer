package com.carDealer.carDealer.auction.service;

import com.carDealer.carDealer.auction.controller.AuctionController;
import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.model.AuctionDocument;
import com.carDealer.carDealer.auction.repository.AuctionRepository;
import com.carDealer.carDealer.cars.repository.CarRepository;
import com.carDealer.carDealer.cars.service.CarService;
import com.carDealer.carDealer.configuration.repository.ConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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