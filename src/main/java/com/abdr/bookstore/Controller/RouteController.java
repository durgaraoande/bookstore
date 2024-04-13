package com.abdr.bookstore.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abdr.bookstore.models.Book;
import com.abdr.bookstore.service.BookService;

@Controller
public class RouteController {

    private BookService bookService;
    public RouteController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Book> books=bookService.findAll();
        model.addAttribute("books", books);
        return "home";
    }

    @RequestMapping("add")
    public String add(){
        return "add";
    }

    @GetMapping("add/{id}")
    public String edit(@PathVariable int id,Model m){
        m.addAttribute("book", bookService.findById(id));
        return "editform";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        bookService.delete(id);
        return "redirect:/";
    }
}
