package com.abdr.bookstore.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdr.bookstore.models.Book;
import com.abdr.bookstore.models.User;
import com.abdr.bookstore.service.BookService;
import com.abdr.bookstore.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserRouteController {

    private BookService bookService;
    private UserService userService;
    public UserRouteController(BookService bookService,UserService userService) {
        this.bookService = bookService;
        this.userService=userService;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public String home(Model model, Principal principal, HttpSession session) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
    
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            if (user != null) {
                model.addAttribute("user", user);
                session.setAttribute("userId", user.getId());
            }
        }
    
        return "user-home";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public String search(Model model, Principal principal, HttpSession session,@RequestParam String title) {
        List<Book> books = bookService.findByTitle(title);
        model.addAttribute("books", books);
    
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            if (user != null) {
                model.addAttribute("user", user);
                session.setAttribute("userId", user.getId());
            }
        }
    
        return "user-home";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/searchform")
    public String searchForm() {
        return "search";
    }
}
