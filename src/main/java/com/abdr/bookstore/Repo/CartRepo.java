package com.abdr.bookstore.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdr.bookstore.models.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long>{
    
}
