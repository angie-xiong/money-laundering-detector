package com.orange.cat.money_laundering_detector.repository;

import com.orange.cat.money_laundering_detector.domain.Transaction;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAll();
}
