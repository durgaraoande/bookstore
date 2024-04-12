package com.abdr.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abdr.bookstore.Repo.BookRepo;
import com.abdr.bookstore.models.Book;

@Service
public class BookService {

    private BookRepo repo;
    public BookService(BookRepo repo) {
        this.repo = repo;
    }

    public List<Book> findAll() {
        return repo.findAll();
    }

    public void save(Book book) {
        repo.save(book);
    }
    
}
