package com.abdr.bookstore.Repo;

import org.springframework.stereotype.Repository;

import com.abdr.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository; // Import the JpaRepository class



@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{

}
