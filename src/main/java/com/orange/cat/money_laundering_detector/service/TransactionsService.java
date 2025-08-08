package com.orange.cat.money_laundering_detector.service;

import com.fasterxml.uuid.Generators;
import com.orange.cat.money_laundering_detector.domain.Transaction;
import com.orange.cat.money_laundering_detector.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionsService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransaction() {
        return transactionRepository.findAll();
        //		return new Transaction(
        //				Generators.timeBasedGenerator().generate(),
        //				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()),
        //				"010",
        //				"8000EBD30",
        //				"010",
        //				"8000EBD30",
        //				new BigDecimal("3697.34"),
        //				"US Dollar",
        //				new BigDecimal("3697.34"),
        //				"US Dollar",
        //				"Reinvestment",
        //				false
        //				);
    }
    ;

    public Transaction save(Transaction transaction) {
        transaction.setId(Generators.timeBasedGenerator().generate());
        return transactionRepository.save(transaction);
    }
}
