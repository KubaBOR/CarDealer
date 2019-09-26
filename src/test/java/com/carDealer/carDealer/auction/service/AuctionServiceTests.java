package com.carDealer.carDealer.auction.service;

import com.carDealer.carDealer.auction.controller.AuctionController;
import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.dto.NewAuctionFormData;
import com.carDealer.carDealer.auction.model.AuctionDocument;
import com.carDealer.carDealer.auction.repository.AuctionRepository;
import com.carDealer.carDealer.cars.dto.Car;
import com.carDealer.carDealer.cars.model.CarDocument;
import com.carDealer.carDealer.cars.repository.CarRepository;
import com.carDealer.carDealer.cars.service.CarService;
import com.carDealer.carDealer.configuration.dto.Configuration;
import com.carDealer.carDealer.configuration.model.ConfigurationDocument;
import com.carDealer.carDealer.configuration.repository.ConfigurationRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AuctionServiceTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    AuctionService auctionService;

    @MockBean
    ConfigurationService configurationService;

    @MockBean
    CarService carService;

    @MockBean
    CarRepository carRepository;

    @MockBean
    ConfigurationRepository configurationRepository;

    @MockBean
    AuctionRepository auctionRepository;


    @Test
    public void created_auction_car_details_are_correct() throws Exception {

        CarDocument car = carRepository.getByMakeAndAndModel("BMW", "M340i");

        String config1 = configurationRepository.getByAddon("Bluetooth").getId();
        String config2 = configurationRepository.getByAddon("Rain sensor").getId();

        String[] configs = new String[]{config1, config2};

        NewAuctionFormData formData = new NewAuctionFormData();
        formData.setCar(car.getId());
        formData.setConfigurations(configs);
        formData.setDescription("Bla bla blaaah");
        formData.setMilleageKm(105000);
        formData.setPrice(auctionService.calculatePrice(formData));
        formData.setProductionYear("2017");


        /*this.mvc.perform(MockMvcRequestBuilders
                        .post("addAuction")*/


                //post("/addAuction"), formData).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().contentType("text/html"));

        /*

        auctionService.addNewAuction(formData);


        Assert.assertEquals("2017", auctionRepository.getByProductionYear("2017").getProductionYear());*/

    }
}