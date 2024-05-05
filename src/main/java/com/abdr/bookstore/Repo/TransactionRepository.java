package com.abdr.bookstore.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdr.bookstore.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    
}
