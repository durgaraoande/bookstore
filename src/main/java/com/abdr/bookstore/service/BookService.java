package com.abdr.bookstore.service;

import java.util.List;
import java.util.Optional;

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
        Optional<Book> existingBook = repo.findById(book.getId());
        if (existingBook.isPresent()) {
            existingBook.get().setId(book.getId());
            existingBook.get().setTitle(book.getTitle());
            existingBook.get().setAuthor(book.getAuthor());
            existingBook.get().setPrice(book.getPrice());
            repo.save(existingBook.get());
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    public Book findById(int bookId) {
        return repo.findById(bookId).orElse(new Book());
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
    
}
