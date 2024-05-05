package com.abdr.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdr.bookstore.Repo.CartItemRepository;
import com.abdr.bookstore.models.CartItem;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public void save(CartItem item) {
        cartItemRepository.save(item);
    }

    public CartItem findById(int itemId) {
        return cartItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public void delete(CartItem item) {
        cartItemRepository.delete(item);
    }

   
    
}
