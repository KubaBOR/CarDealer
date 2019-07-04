package com.carDealer.carDealer.auction.controller;

import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.service.AuctionService;
import com.carDealer.carDealer.cars.service.CarService;
import com.carDealer.carDealer.configuration.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class AuctionController {

    private final AuctionService auctionService;

    private final CarService carService;

    private final ConfigurationService configurationService;

    @Autowired
    public AuctionController(AuctionService auctionService, CarService carService, ConfigurationService configurationService) {
        this.auctionService = auctionService;
        this.carService = carService;
        this.configurationService = configurationService;
    }

    @GetMapping("/allAuctionsPage")
    public String displayAllAuctions(Model model) {
        List<Auction> allAuctions = auctionService.getAllAuctions();
        model.addAttribute("allAuctions", allAuctions);
        return "allAuctionsPage";
    }

    @GetMapping("/addNewAuctionPage")
    public String addNewAuctionPage(Model model) {
        model.addAttribute("allAuctions", carService.getAllCars());
        model.addAttribute("allConfig", configurationService.getAllConfigurations());
        model.addAttribute("newAuction", new NewAuctionCarFormData());
        return "addNewAuctionPage";
    }

    //todo
    /*
    Bug - This method creates empty record
     */
    @PostMapping("/addNewAuctionCarAction")
    public RedirectView addNewAuctionCarAction(@ModelAttribute("newAuction") NewAuctionCarFormData auction, Model model) {
        auctionService.createNewAuction(auction);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allAuctionsPage");
        return redirectView;
    }

    public static class NewAuctionCarFormData {
        private String title;
        private String[] carList;
        private String[] config;
        private String price;
        private String productionYear;



        public NewAuctionCarFormData(String title, String[] carList, String[] config, String price, String productionYear) {
            this.title = title;
            this.carList = carList;
            this.config = config;
            this.price = price;
            this.productionYear = productionYear;
        }

        public NewAuctionCarFormData() {
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String[] getCarList() {
            return carList;
        }

        public void setCarList(String[] carList) {
            this.carList = carList;
        }

        public String[] getConfig() {
            return config;
        }

        public void setConfig(String[] config) {
            this.config = config;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProductionYear() {
            return productionYear;
        }

        public void setProductionYear(String productionYear) {
            this.productionYear = productionYear;
        }
    }
}
