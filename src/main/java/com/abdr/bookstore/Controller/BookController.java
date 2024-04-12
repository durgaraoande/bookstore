package com.abdr.bookstore.Controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdr.bookstore.service.BookService;
import com.abdr.bookstore.models.Book;

@RestController
public class BookController {
    
    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @PostMapping("/books")
    public void addBook(@RequestBody Book book) {
        bookService.save(book);
    }
}
