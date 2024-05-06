package com.abdr.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdr.bookstore.Repo.TransactionItemRepo;
import com.abdr.bookstore.models.TransactionItem;


@Service
public class TransactionItemService {
    @Autowired
    private TransactionItemRepo itemRepo;

    public void save(TransactionItem item) {
        itemRepo.save(item);
    }
}
