package com.orange.cat.money_laundering_detector.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.orange.cat.money_laundering_detector.domain.Transaction;
import com.orange.cat.money_laundering_detector.repository.TransactionRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TransactionsServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionsService transactionsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTransaction_ReturnsAllTransactions() {
        List<Transaction> mockTransactions = Arrays.asList(new Transaction(), new Transaction());
        when(transactionRepository.findAll()).thenReturn(mockTransactions);

        List<Transaction> result = transactionsService.getTransaction();

        assertEquals(2, result.size());
        verify(transactionRepository).findAll();
    }

    @Test
    void testGetTransaction_ReturnsEmptyList() {
        when(transactionRepository.findAll()).thenReturn(Collections.emptyList());

        List<Transaction> result = transactionsService.getTransaction();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(transactionRepository).findAll();
    }

    @Test
    void testSave_AssignsUUIDAndSavesTransaction() {
        Transaction transaction = new Transaction();
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Transaction savedTransaction = transactionsService.save(transaction);

        assertNotNull(savedTransaction.getId(), "Transaction ID should be assigned");
        verify(transactionRepository).save(transaction);
    }

    @Test
    void testSave_NullTransaction_ThrowsException() {
        assertThrows(NullPointerException.class, () -> transactionsService.save(null));
    }

    @Test
    void testSave_RepositoryThrowsException() {
        Transaction transaction = new Transaction();
        when(transactionRepository.save(any(Transaction.class))).thenThrow(new RuntimeException("DB error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> transactionsService.save(transaction));
        assertEquals("DB error", exception.getMessage());
    }
}
