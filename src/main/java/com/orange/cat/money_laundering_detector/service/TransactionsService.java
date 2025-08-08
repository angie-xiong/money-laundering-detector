package com.orange.cat.money_laundering_detector.service;

import com.fasterxml.uuid.Generators;
import com.orange.cat.money_laundering_detector.domain.Transaction;
import com.orange.cat.money_laundering_detector.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionsService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransaction() {
        return transactionRepository.findAll();
    }

    public Transaction save(Transaction transaction) {
        transaction.setId(Generators.timeBasedGenerator().generate());
        return transactionRepository.save(transaction);
    }
}
