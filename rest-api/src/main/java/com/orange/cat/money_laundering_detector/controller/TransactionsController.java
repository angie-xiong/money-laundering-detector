package com.orange.cat.money_laundering_detector.controller;

import com.orange.cat.money_laundering_detector.domain.Transaction;
import com.orange.cat.money_laundering_detector.service.TransactionsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("/get")
    public List<Transaction> getAll() {
        return transactionsService.getTransaction();
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return transactionsService.save(transaction);
    }
}
