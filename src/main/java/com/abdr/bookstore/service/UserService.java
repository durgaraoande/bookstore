package com.abdr.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abdr.bookstore.UserFoundException;
import com.abdr.bookstore.Repo.UserRepo;
import com.abdr.bookstore.models.Cart;
import com.abdr.bookstore.models.User;
import java.util.List;
import java.util.Optional;



@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public void saveUser(User user, List<String> role) throws UserFoundException {
        Cart cart = new Cart();
        user.setCart(cart);
        user.setRoles(role);
        user.setPassword(encoder.encode(user.getPassword()));
        User existingUser = repo.findByUsername(user.getUsername());
        if(existingUser != null && user.getUsername().equals(existingUser.getUsername())) {
            throw new UserFoundException();
        }
        repo.save(user);
    }

    public User findByUsername(String name) {
        return repo.findByUsername(name);
    }

    public void save(User user) {
        repo.save(user);
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }


}
