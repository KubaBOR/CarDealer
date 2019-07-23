package com.carDealer.carDealer.auction.service;

import com.carDealer.carDealer.auction.controller.AuctionController;
import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.model.AuctionDocument;
import com.carDealer.carDealer.auction.repository.AuctionRepository;
import com.carDealer.carDealer.configuration.repository.ConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AuctionService {

//    private CarService carService;

    private AuctionRepository auctionRepository;

//    private CarRepository carRepository;

    private ConfigurationRepository configurationRepository;

    private ModelMapper modelMapper;

    public AuctionService(
                          AuctionRepository auctionRepository,
                          ConfigurationRepository configurationRepository,
                          ModelMapper modelMapper) {
        this.auctionRepository = auctionRepository;
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

    public List<Auction> getAllAuctions(){
        List<AuctionDocument> allAuctions = auctionRepository.findAll();
        List<Auction> collect = allAuctions.stream()
                .map(auctionDocument -> modelMapper.map(auctionDocument, Auction.class))
                .collect(Collectors.toList());
        return collect;
    }
}
