package com.carDealer.carDealer.auction.controller;

import com.carDealer.carDealer.auction.dto.Auction;
import com.carDealer.carDealer.auction.dto.BidAuctionFormData;
import com.carDealer.carDealer.auction.dto.NewAuctionFormData;
import com.carDealer.carDealer.auction.service.AuctionService;
import com.carDealer.carDealer.cars.dto.Make;
import com.carDealer.carDealer.cars.service.CarService;
import com.carDealer.carDealer.configuration.service.ConfigurationService;
import com.carDealer.carDealer.photos.model.Photo;
import com.carDealer.carDealer.user.dto.User;
import com.carDealer.carDealer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
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
        model.addAttribute("allConfig", configurationService.getAllConfigurations());
        return "addNewAuctionPage";
    }

    @GetMapping("getAuction/{auctionId}")
    public String getAuction(@PathVariable String auctionId, Model model) {
        Photo photo = auctionService.getById(auctionId).getPhoto();
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        Auction getAuction = auctionService.getById(auctionId);
        model.addAttribute("getAuction", getAuction);
        model.addAttribute("newBid", new BidAuctionFormData());

        return "auction";
    }

    @PostMapping("/bidAuction/{id}")
    public String bidAuction(@PathVariable String id, @ModelAttribute("newBid") @Valid BidAuctionFormData bidAuction,
                             Model model,
                             BindingResult bindingResult,
                             RedirectAttributes attributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        bidAuction.setUser(userService.getByEmail(auth.getName()));
        model.addAttribute("currentUser", bidAuction.getUser());

        if (auctionService.validateBidder(id, bidAuction.getUser())) {
            auctionService.bidAuction(id, bidAuction.getAmount(), bidAuction.getUser());
        } else {
            FieldError error = new FieldError(
                    "newBid",
                    "email",
                    "ERROR: You cannot outbid yourself."
            );
            bindingResult.addError(error);
            attributes.addFlashAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/getAuction/{id}";
        }

        return "redirect:/getAuction/{id}";
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
    public RedirectView addAuction(@ModelAttribute("addNewAuction") NewAuctionFormData auction, Model model,
                                   @RequestParam("image") MultipartFile file) throws IOException {
        auctionService.addNewAuction(auction, file);

        RedirectView view = new RedirectView();
        view.setUrl("/allAuctionsPage");
        return view;
    }

}
