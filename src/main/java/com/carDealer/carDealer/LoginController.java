package com.carDealer.carDealer;

import com.carDealer.carDealer.user.dto.User;
import com.carDealer.carDealer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(Model model) {
        setupModel(model);
        return "login";
    }

    @GetMapping("signup")
    public String signupPage(Model model) {
        setupModel(model);
        return "signup";
    }


    private UserService userService;


    @GetMapping(name = "/addUserAction")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;

    }

    @PostMapping("/addUserAction")
    public RedirectView addUser(@ModelAttribute("newUser") User user) {

        /*User doesUserExist = userService.getByEmail(user.getEmail());
        if (doesUserExist != null) {
           *//* bindingResult.rejectValue("email", "error.user",
                    "User already exists.");*//*
           RedirectView redirectView = new RedirectView();
           redirectView.setUrl("signup");
           return redirectView;

        } else {*/
            userService.addUser(user);
        //}
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/index.html");
        return redirectView;
    }

    @GetMapping(value = "/dashboard")
    public RedirectView dashboard(Model model) {
        RedirectView redirectView = new RedirectView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByEmail(auth.getName());
        model.addAttribute("currentUser", user);
        model.addAttribute("lastName", "Welcome " + user.getLastName());
        redirectView.setUrl("/management");
        return redirectView;
    }

    private void setupModel(Model model) {
        model.addAttribute("newUser", new User());
    }
}
