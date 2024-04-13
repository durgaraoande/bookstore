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

    public void update(Book book) {
        repo.save(book);
    }

    public Book findById(int id) {
        return repo.findById(id).orElse(new Book());
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
    
}
