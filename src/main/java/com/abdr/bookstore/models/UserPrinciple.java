package com.abdr.bookstore.models;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;


public class UserPrinciple implements UserDetails {

    private User user;
    private Collection<String> roles;

    public UserPrinciple(User user, Collection<String> roles) {
        this.user = user;
        this.roles = roles;
    }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     return Collections.singleton(new SimpleGrantedAuthority("USER"));
    // }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
