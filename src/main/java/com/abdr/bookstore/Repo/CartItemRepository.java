package com.abdr.bookstore.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdr.bookstore.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
    
}
