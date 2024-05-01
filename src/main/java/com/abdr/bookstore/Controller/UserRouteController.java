package com.abdr.bookstore.Controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abdr.bookstore.models.Book;
import com.abdr.bookstore.service.BookService;

@Controller
@RequestMapping("/user")
public class UserRouteController {

    private BookService bookService;
    public UserRouteController(BookService bookService) {
        this.bookService = bookService;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public String home(Model model) {
        List<Book> books=bookService.findAll();
        model.addAttribute("books", books);
        return "user-home";
    }
}
