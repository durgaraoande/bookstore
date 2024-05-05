package com.abdr.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdr.bookstore.Repo.TransactionRepository;
import com.abdr.bookstore.models.Transaction;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    public void save(Transaction transaction) {
        transactionRepo.save(transaction);
    }

    public Optional<Transaction> findById(Long transactionId) {
        return transactionRepo.findById(transactionId);
    }
    
}
