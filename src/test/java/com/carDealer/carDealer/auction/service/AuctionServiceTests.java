package com.carDealer.carDealer.auction.service;

import com.carDealer.carDealer.auction.controller.AuctionController;
import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.model.AuctionDocument;
import com.carDealer.carDealer.cars.dto.Car;
import com.carDealer.carDealer.configuration.dto.Configuration;
import com.carDealer.carDealer.configuration.service.ConfigurationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AuctionServiceTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    AuctionService auctionService;

    @MockBean
    ConfigurationService configurationService;

   /* @Test
    public void created_auction_car_details_are_correct() {

        Auction auction = new Auction();
        auction.setTitle("This incredible car is for sale!");
        auction.setCar(new Car("Skoda", "Fabia", "1.4L Diesel", 85));
        Configuration firstConfig = new Configuration("Klimatyzacja");
        Configuration secondConfig = new Configuration("Podgrzewane fotele");
        auction.setConfigurationList(Arrays.asList(firstConfig, secondConfig));
        auction.setPrice(9500);
        auction.setProductionYear("2009");

        auctionService.createAuction(auction);

        Assert.assertEquals("Skoda", auction.getCar().getMake());
        Assert.assertEquals("Fabia", auction.getCar().getModel());
        Assert.assertEquals(85, auction.getCar().getHorsePower());

    }*/
}
