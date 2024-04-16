package com.abdr.bookstore.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdr.bookstore.models.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    User findByUsername(String username);
}
