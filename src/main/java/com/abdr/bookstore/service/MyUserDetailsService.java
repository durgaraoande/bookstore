package com.abdr.bookstore.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abdr.bookstore.Repo.UserRepo;
import com.abdr.bookstore.models.User;
import com.abdr.bookstore.models.UserPrinciple;



@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=repo.findByUsername(username);
        if(user==null){
            System.out.println("user 404");
            throw new UsernameNotFoundException("username 404");
        }
        return new UserPrinciple(user);
    }
    
}
