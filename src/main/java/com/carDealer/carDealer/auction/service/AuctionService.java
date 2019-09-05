package com.carDealer.carDealer.auction.service;

import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.dto.NewAuctionFormData;
import com.carDealer.carDealer.auction.model.AuctionDocument;
import com.carDealer.carDealer.auction.repository.AuctionRepository;
import com.carDealer.carDealer.bid.dto.Bid;
import com.carDealer.carDealer.cars.model.CarDocument;
import com.carDealer.carDealer.cars.repository.CarRepository;
import com.carDealer.carDealer.configuration.model.ConfigurationDocument;
import com.carDealer.carDealer.configuration.repository.ConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Auction getById(String auctionId) {
        AuctionDocument getAuction = auctionRepository.getById(auctionId);
        Auction auctionToReturn = modelMapper.map(getAuction, Auction.class);
        return auctionToReturn;
    }

    public Auction bidAuction(String auctionId, int amount) {
        AuctionDocument getAuction = auctionRepository.getById(auctionId);
        List<Bid> getBids = getAuction.getBiddingList();
        getBids.add(new Bid(amount));
        getAuction.setBiddingList(getBids);
        auctionRepository.save(getAuction);

        return modelMapper.map(getAuction, Auction.class);
    }

    public String addNewAuction (NewAuctionFormData formData) {

        String carId = formData.getCar();
        CarDocument carToSave = carRepository.getById(carId);

        List<String> configurationIds = Arrays.asList(formData.getConfigurations());
        List<ConfigurationDocument> configToSave = configurationRepository.findAll()
                .stream()
                .filter(configurationDocument -> configurationIds.contains(configurationDocument.getId()))
                .collect(Collectors.toList());

        List<Bid> bidList = new ArrayList<>();
        bidList.add(new Bid(formData.getPrice()));


        AuctionDocument auctionDocument = new AuctionDocument(
                formData.getDescription(),
                carToSave,
                configToSave,
                formData.getMilleageKm(),
                calculatePrice(formData),
                formData.getProductionYear(),
                bidList
        );

        return auctionRepository.save(auctionDocument).getId();
    }

    public int calculatePrice (NewAuctionFormData formData) {
        CarDocument chosenCar = carRepository.getById(formData.getCar());
        int basePrice = chosenCar.getBasePrice();

        List<String> configPrice = Arrays.asList(formData.getConfigurations());
        int configTotalPrice = configurationRepository.findAll()
                .stream()
                .filter(x -> configPrice.contains(x.getId()))
                .mapToInt(ConfigurationDocument::getPrice)
                .sum();

        int currentYear = LocalDate.now().getYear();
        int ageCost = 5000 * (currentYear - Integer.parseInt(formData.getProductionYear()));
        double milleageCost = 0.38 * formData.getMilleageKm();

        return (int) Math.round(basePrice + configTotalPrice - ageCost - milleageCost);
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
