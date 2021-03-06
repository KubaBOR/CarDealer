package com.carDealer.carDealer.user.controller;

import com.carDealer.carDealer.user.dto.User;
import com.carDealer.carDealer.user.dto.UserFormData;
import com.carDealer.carDealer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

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
    public String signupPage(Model model, UserFormData userFormData) {
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
    public String addUser(@ModelAttribute("newUser") @Valid UserFormData formData, BindingResult bindingResult,
                          RedirectAttributes attributes) {

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/signup");

        if (userService.isEmailTaken(formData.getEmail())) {
            FieldError error = new FieldError(
                    "email",
                    "email",
                    "This email is already taken!");
            bindingResult.addError(error);

            return "signup";
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userService.addUser(formData);
        attributes.addFlashAttribute("message", "Account created successfuly!");

        return "redirect:/signup";

    }

    private void setupModel(Model model) {
        model.addAttribute("newUser", new UserFormData());
    }

}