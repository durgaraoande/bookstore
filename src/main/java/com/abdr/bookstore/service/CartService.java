package com.abdr.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.abdr.bookstore.Repo.CartRepo;
import com.abdr.bookstore.models.Cart;
import com.abdr.bookstore.models.CartItem;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    public void save(Cart cart) {
        cartRepo.save(cart);
    }
    
    public void clearCart(Cart cart) {
        for (CartItem item : cart.getItems()) {
            item.setCart(null);
        }
        cart.getItems().clear();
        cartRepo.save(cart);
    }
}
