package com.abdr.bookstore.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdr.bookstore.models.TransactionItem;

public interface TransactionItemRepo extends JpaRepository<TransactionItem,Long>{
    
}
