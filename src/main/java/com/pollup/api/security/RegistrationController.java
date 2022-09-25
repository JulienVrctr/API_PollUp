package com.pollup.api.security;

import com.pollup.api.model.Artist;
import com.pollup.api.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final ArtistService userService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;

    @GetMapping("/sign-in/artist")
    public String registrationUser(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/home";
        }

        model.addAttribute("userForm", new Artist());

        return "registration";
    }

    @PostMapping("/sign-in/artist")
    public String registerUser(@ModelAttribute("userForm") Artist userForm, BindingResult bindingResult, HttpServletRequest request) {
        registrationValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.signUpUser(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword(), request);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        if (securityService.isAuthenticated()) {
            return "redirect:/home";
        }

        return "login";
    }
}