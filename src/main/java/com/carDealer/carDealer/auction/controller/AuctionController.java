package com.carDealer.carDealer.auction.controller;

import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.service.AuctionService;
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


    private final ConfigurationService configurationService;

    @Autowired
    public AuctionController(AuctionService auctionService, ConfigurationService configurationService) {
        this.auctionService = auctionService;
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
        model.addAttribute("addNewAuction", new Auction());
        return "addAuction";
    }

    @PostMapping("/addAuction")
    public RedirectView addAuction(@ModelAttribute("addNewAuction") Auction auction, Model model){
        auctionService.createAuction(auction);
        RedirectView view = new RedirectView();
        view.setUrl("/allAuctionsPage");
        return view;
    }

}
