/**
 * Using record class for transactions in repository layer. It will map the data row of the database.
 */
package com.orange.cat.money_laundering_detector.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private UUID id;

    @Column(name = "transaction_time")
    private String transactionTime;

    @Column(name = "from_bank")
    private String fromBank;

    @Column(name = "from_account")
    private String fromAccount;

    @Column(name = "to_bank")
    private String toBank;

    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "received_amount", updatable = false, precision = 12, scale = 2)
    private BigDecimal receivedAmount;

    @Column(name = "received_currency")
    private String receivedCurrency;

    @Column(name = "pay_amount", updatable = false, precision = 12, scale = 2)
    private BigDecimal payAmount;

    @Column(name = "pay_currency")
    private String payCurrency;

    @Column(name = "pay_format")
    private String payFormat;

    @Column(name = "is_laundering")
    private Boolean isLaundering;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getFromBank() {
        return fromBank;
    }

    public void setFromBank(String fromBank) {
        this.fromBank = fromBank;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToBank() {
        return toBank;
    }

    public void setToBank(String toBank) {
        this.toBank = toBank;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getReceivedCurrency() {
        return receivedCurrency;
    }

    public void setReceivedCurrency(String receivedCurrency) {
        this.receivedCurrency = receivedCurrency;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayCurrency() {
        return payCurrency;
    }

    public void setPayCurrency(String payCurrency) {
        this.payCurrency = payCurrency;
    }

    public String getPayFormat() {
        return payFormat;
    }

    public void setPayFormat(String payFormat) {
        this.payFormat = payFormat;
    }

    public Boolean getLaundering() {
        return isLaundering;
    }

    public void setLaundering(Boolean laundering) {
        isLaundering = laundering;
    }
}
