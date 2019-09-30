package com.carDealer.carDealer.auction.controller;

import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.dto.BidAuctionFormData;
import com.carDealer.carDealer.auction.dto.NewAuctionFormData;
import com.carDealer.carDealer.auction.service.AuctionService;
import com.carDealer.carDealer.cars.dto.Make;
import com.carDealer.carDealer.cars.service.CarService;
import com.carDealer.carDealer.configuration.service.ConfigurationService;
import com.carDealer.carDealer.user.dto.User;
import com.carDealer.carDealer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final UserService userService;

    @Autowired
    public AuctionController(AuctionService auctionService, CarService carService, ConfigurationService configurationService, UserService userService) {
        this.auctionService = auctionService;
        this.carService = carService;
        this.configurationService = configurationService;
        this.userService = userService;
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

    @GetMapping("getAuction/{auctionId}")
    public String getAuction(@PathVariable String auctionId, Model model) {
        Auction getAuction = auctionService.getById(auctionId);
        model.addAttribute("getAuction", getAuction);
        model.addAttribute("newBid", new BidAuctionFormData());

        return "auction";
    }

    @PostMapping("/bidAuction/{id}")
    public RedirectView bidAuction(@PathVariable String id, @RequestParam int amount, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByEmail(auth.getName());
        model.addAttribute("currentUser", user);

        /*if (auctionService.validateBidPrice(amount)) {
            auctionService.bidAuction(id, amount, user);
        }
        else {

        }*/

        auctionService.bidAuction(id, amount, user);


        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/getAuction/{id}");
        return redirectView;
    }

    @GetMapping("/addAuction")
    public String addAuction(Model model) {
        model.addAttribute("addNewAuction", new NewAuctionFormData());
        model.addAttribute("allCars", carService.getAllCars());
        model.addAttribute("allConfig", configurationService.getAllConfigurations());
        model.addAttribute("allMakes", carService.getAllMakes());
        model.addAttribute("message", "validation failored!");
        return "addAuction";
    }

    @DeleteMapping("deleteAuctionAction/{idToDelete}")
    public RedirectView deleteAuction(@PathVariable String idToDelete) {
        auctionService.deleteById(idToDelete);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allAuctionsPage");
        return redirectView;
    }

    @PostMapping("/addAuction")
    public RedirectView addAuction(@ModelAttribute("addNewAuction") NewAuctionFormData auction, Model model) {
        auctionService.addNewAuction(auction);
        RedirectView view = new RedirectView();
        view.setUrl("/allAuctionsPage");
        return view;
    }

}
