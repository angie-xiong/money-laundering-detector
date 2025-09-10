package com.orange.cat.money_laundering_detector.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.cat.money_laundering_detector.domain.Transaction;
import com.orange.cat.money_laundering_detector.service.TransactionsService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class TransactionsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionsService transactionsService;

    @InjectMocks
    private TransactionsController transactionsController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionsController).build();
    }

    @Test
    void testGetAllTransactions_ReturnsList() throws Exception {
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
        when(transactionsService.getTransaction()).thenReturn(transactions);

        mockMvc.perform(get("/transactions/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(transactionsService).getTransaction();
    }

    @Test
    void testGetAllTransactions_ReturnsEmptyList() throws Exception {
        when(transactionsService.getTransaction()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/transactions/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(transactionsService).getTransaction();
    }

    @Test
    void testCreateTransaction_ReturnsSavedTransaction() throws Exception {
        Transaction input = new Transaction();
        input.setPayAmount(new BigDecimal("100.0"));
        input.setReceivedAmount(new BigDecimal("100.0"));
        Transaction saved = new Transaction();
        saved.setId(UUID.randomUUID());
        saved.setPayAmount(new BigDecimal("100.0"));
        saved.setReceivedAmount(new BigDecimal("100.0"));

        when(transactionsService.save(any(Transaction.class))).thenReturn(saved);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.payAmount").value(new BigDecimal("100.0")))
                .andExpect(jsonPath("$.receivedAmount").value(new BigDecimal("100.0")));

        verify(transactionsService).save(any(Transaction.class));
    }

    @Test
    void testCreateTransaction_InvalidPayload_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid-json"))
                .andExpect(status().isBadRequest());
    }
}
