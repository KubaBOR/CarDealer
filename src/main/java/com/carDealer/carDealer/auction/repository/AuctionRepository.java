package com.carDealer.carDealer.auction.repository;

import com.carDealer.carDealer.auction.model.AuctionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuctionRepository extends MongoRepository<AuctionDocument, String> {

    AuctionDocument getById(String id);
//    AuctionDocument getByPriceIsBetween(int priceLowBound, int priceHighBound);


}
