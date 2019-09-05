package com.carDealer.carDealer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourcesController {

    @GetMapping("/fragments")
    public String thymeleafFragments(){ return "fragments"; }

    @GetMapping("/management")
    public String managementPage(){return "management";}
}
