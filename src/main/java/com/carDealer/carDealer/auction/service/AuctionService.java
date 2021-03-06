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
import com.carDealer.carDealer.photos.model.Photo;
import com.carDealer.carDealer.user.dto.User;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Auction bidAuction(String auctionId, int amount, User user) {
        AuctionDocument getAuction = auctionRepository.getById(auctionId);
        List<Bid> getBids = getAuction.getBiddingList();
        int lastBidIndex = getBids.size();


        int amountOfBids = getAuction.getBiddingList().size();
        int amountToBid = getBids.get(lastBidIndex - 1).getAmount() + (amount);
        getBids.add(new Bid(amountToBid, user));
        getAuction.setBiddingList(getBids);
        auctionRepository.save(getAuction);

        return modelMapper.map(getAuction, Auction.class);
    }

    public boolean validateBidder(String auctionId, User user) {
        AuctionDocument getAuction = auctionRepository.getById(auctionId);
        List <Bid> bidList = getAuction.getBiddingList();
        Bid lastBid = bidList.get(bidList.size() - 1);
        return !lastBid.getUser().getEmail().equals(user.getEmail());
    }

    public String addNewAuction (NewAuctionFormData formData, MultipartFile image) throws IOException {

        String carId = formData.getCar();
        CarDocument carToSave = carRepository.getById(carId);

        List<String> configurationIds = Arrays.asList(formData.getConfigurations());
        List<ConfigurationDocument> configToSave = configurationRepository.findAll()
                .stream()
                .filter(configurationDocument -> configurationIds.contains(configurationDocument.getId()))
                .collect(Collectors.toList());

        int totalPrice = calculatePrice(formData);

        List<Bid> bidList = new ArrayList<>();
        bidList.add(new Bid(totalPrice));

        Photo photo = new Photo();
        photo.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));

        AuctionDocument auctionDocument = new AuctionDocument(
                formData.getDescription(),
                carToSave,
                configToSave,
                formData.getMilleageKm(),
                totalPrice,
                formData.getProductionYear(),
                bidList,
                photo);

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
