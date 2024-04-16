package com.abdr.bookstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;

import com.abdr.bookstore.UserFoundException;
import com.abdr.bookstore.models.User;
import com.abdr.bookstore.service.UserService;
import org.springframework.ui.Model;


@Controller
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("register-user")

    public String register(@ModelAttribute User user,Model model){
        try {
            service.saveUser(user);
            return "redirect:/login"; // redirect to success page
        } catch (UserFoundException e) {
            model.addAttribute("fail", true);
            model.addAttribute("errorMessage", "User already exists");
            return "redirect:/register"; // redirect back to the registration page
        }
    }
}
