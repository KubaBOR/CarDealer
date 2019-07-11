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
        model.addAttribute("displayAuctions", auctionService.getAllAuctions());
        model.addAttribute("allCars", carService.getAllCars());
        model.addAttribute("allConfig", configurationService.getAllConfigurations());
        model.addAttribute("newAuction", new NewAuctionCarFormData());
        return "addNewAuctionPage";
    }

    //todo
    /*
    Bug - This method creates empty record
     */
    @PostMapping("/addNewAuctionCarAction")
    public RedirectView addNewAuctionCarAction(@ModelAttribute("newAuction") NewAuctionCarFormData newAuction, Model model) {
        auctionService.createNewAuction(newAuction);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allAuctionsPage");
        return redirectView;
    }

    public static class NewAuctionCarFormData {
        private String title;
        private String carId;
        private String configId;
        private String price;
        private String productionYear;

        public NewAuctionCarFormData() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getConfigId() {
            return configId;
        }

        public void setConfigId(String configId) {
            this.configId = configId;
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
