package com.championships.Championships.cars.controller;

import com.championships.Championships.cars.dto.Car;
import com.championships.Championships.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class  CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/allCarsPage")
    public String displayAllCars(Model model) {
        setupModel(model);
        return "allCarsPage";
    }
    @PostMapping("/addCarAction")
    public RedirectView addNewCar(@ModelAttribute("newCar") Car car, Model model){
        carService.saveCar(car);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allCarsPage");
        return redirectView;
    }

    @DeleteMapping("/deleteCarAction/{deleteCarId}")
    public RedirectView deleteCarAction(@PathVariable String deleteCarId, Model model){
        carService.deleteById(deleteCarId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/allCarsPage");
        return redirectView;
    }

    private void setupModel(Model model){
        List<Car>allCars = carService.getAllCars();
        model.addAttribute("allCars", allCars);
        model.addAttribute("newCar", new Car());
        model.addAttribute("deleteCarId", "");
    }
}
