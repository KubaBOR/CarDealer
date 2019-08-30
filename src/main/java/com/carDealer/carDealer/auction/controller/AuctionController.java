package com.carDealer.carDealer.auction.controller;

import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.dto.NewAuctionFormData;
import com.carDealer.carDealer.auction.service.AuctionService;
import com.carDealer.carDealer.cars.dto.Make;
import com.carDealer.carDealer.cars.service.CarService;
import com.carDealer.carDealer.configuration.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        List<Make> makeList = carService.getAllMakes();
        model.addAttribute("allMakes", makeList);
        return "allAuctionsPage";
    }

    @GetMapping("/addNewAuctionPage")
    public String addNewAuctionPage(Model model) {
        model.addAttribute("displayAuctions", auctionService.getAllAuctions());
//        model.addAttribute("allCars", carService.getAllCars());
        model.addAttribute("allConfig", configurationService.getAllConfigurations());
        return "addNewAuctionPage";
    }

/*    public String getCarById(@PathVariable String id) {
        String make = carService.getCarById(id).getMake();
        return make;
    }*/

    @GetMapping("/addAuction")
    public String addAuction(Model model){
        model.addAttribute("addNewAuction", new NewAuctionFormData());
        model.addAttribute("allCars", carService.getAllCars());
        model.addAttribute("allConfig", configurationService.getAllConfigurations());
        model.addAttribute("allMakes", carService.getAllMakes());
        return "addAuction";
    }

    @DeleteMapping("deleteAuctionAction/{idToDelete}")
    public RedirectView deleteAuction(@PathVariable String idToDelete){
        auctionService.deleteById(idToDelete);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allAuctionsPage");
        return redirectView;
    }

    @PostMapping("/addAuction")
    public RedirectView addAuction(@ModelAttribute("addNewAuction") NewAuctionFormData auction, Model model){
        auctionService.addNewAuction(auction);
//        auctionService.createAuction(auction);
        RedirectView view = new RedirectView();
        view.setUrl("/allAuctionsPage");
        return view;
    }

}
