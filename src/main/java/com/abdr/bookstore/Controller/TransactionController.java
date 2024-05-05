package com.abdr.bookstore.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.abdr.bookstore.models.Transaction;
import com.abdr.bookstore.models.User;
import com.abdr.bookstore.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {

    @Autowired
    private UserService userService;
    @GetMapping("/user/transactions")
    public ModelAndView getUserTransactions(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("transactions");
        Long id = (Long) session.getAttribute("userId");
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            List<Transaction> transactions = user.get().getTransactions();
            Collections.reverse(transactions);
            modelAndView.addObject("transactions", transactions);
        }
        return modelAndView;
    }
}
