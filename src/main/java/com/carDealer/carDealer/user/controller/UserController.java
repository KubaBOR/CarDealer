package com.carDealer.carDealer.user.controller;

import com.carDealer.carDealer.user.dto.User;
import com.carDealer.carDealer.user.dto.UserFormData;
import com.carDealer.carDealer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String loginPage(Model model) {
        setupModel(model);
        return "login";
    }


    @GetMapping("/signup")
    public String signupPage(Model model) {
        setupModel(model);
        model.addAttribute("newUser", new UserFormData());
        return "signup";
    }

    @GetMapping("/management")
    public String managementPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByEmail(auth.getName());
        model.addAttribute("currentUser", user);
        return "management";
    }


    @PostMapping("/signup")
    public RedirectView addUser(@ModelAttribute("newUser") UserFormData formData) {


        userService.addUser(formData);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/signup");
        return redirectView;

    }

/*User doesUserExist = userService.getByEmail(user.getEmail());
        if (doesUserExist != null) {
          bindingResult.rejectValue("email", "error.user",
                    "User already exists.");
           RedirectView redirectView = new RedirectView();
           redirectView.setUrl("signup");
           return redirectView;

        } else {
            userService.addUser(user);
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/welcome.html");
        return redirectView;*//*

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
    */

    private void setupModel(Model model) {
        model.addAttribute("newUser", new UserFormData());
    }

}