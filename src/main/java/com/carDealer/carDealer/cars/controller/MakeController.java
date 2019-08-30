package com.carDealer.carDealer.cars.controller;

import com.carDealer.carDealer.cars.dto.Make;
import com.carDealer.carDealer.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MakeController {

    private CarService carService;

    @Autowired
    public MakeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping ("/addDocumentsPage")
    public String displayDocumentsPage (Model model){
        setupModel(model);
        return "addDocumentsPage";
    }

    @PostMapping ("/addMakeAction")
    public RedirectView createMake(@ModelAttribute("addMake")Make make, Model model){
        carService.saveMake(make);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/addDocumentsPage");
        return redirectView;
    }

    private void setupModel (Model model){
        List<Make> makeList = carService.getAllMakes();
        model.addAttribute("allMakes", makeList);
        model.addAttribute("addMake", new Make());
    }
}
