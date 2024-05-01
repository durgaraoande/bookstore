package com.abdr.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abdr.bookstore.UserFoundException;
import com.abdr.bookstore.Repo.UserRepo;
import com.abdr.bookstore.models.User;
import java.util.List;



@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public void saveUser(User user, List<String> role) throws UserFoundException {
        user.setRoles(role);
        user.setPassword(encoder.encode(user.getPassword()));
        User existingUser = repo.findByUsername(user.getUsername());
        if(existingUser != null && user.getUsername().equals(existingUser.getUsername())) {
            throw new UserFoundException();
        }
        repo.save(user);
    }
}
