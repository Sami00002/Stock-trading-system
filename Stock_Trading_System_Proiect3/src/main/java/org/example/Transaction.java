package org.example;

import java.time.LocalDateTime;

public class Transaction {
    private final String customerId;
    private final String stockSymbol;
    private final int quantity;
    private final double pricePerUnit;
    private final TransactionType type;
    private final LocalDateTime timestamp;

    public Transaction(String customerId, String stockSymbol, int quantity, double pricePerUnit, TransactionType type) {
        this.customerId = customerId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.type = type;
        this.timestamp = LocalDateTime.now(); // Capture the transaction time
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "customerId='" + customerId + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", quantity=" + quantity +
                ", pricePerUnit=" + pricePerUnit +
                ", type=" + type +
                ", timestamp=" + timestamp +
                '}';
    }
}

enum TransactionType {
    BUY,
    SELL
}
