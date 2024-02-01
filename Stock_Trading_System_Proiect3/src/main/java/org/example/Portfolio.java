package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Portfolio {
    private Map<Stock, Integer> stocks = new HashMap<>();

    public void addStock(Stock stock, int quantity) {
        stocks.put(stock, stocks.getOrDefault(stock, 0) + quantity);
    }

    public boolean removeStock(Stock stock, int quantity) {
        if (!stocks.containsKey(stock)) {
            System.out.println("Stock not present in the portfolio.");
            return false;
        }
        int currentQuantity = stocks.get(stock);
        if (quantity > currentQuantity) {
            System.out.println("Not enough stock quantity to remove. Current quantity: " + currentQuantity);
            return false;
        }
        stocks.put(stock, currentQuantity - quantity);
        if (stocks.get(stock) == 0) {
            stocks.remove(stock);  // Remove stock entry if quantity becomes zero
        }
        return true;
    }

    public int getStockQuantity(Stock stock) {
        return stocks.getOrDefault(stock, 0);
    }

    public double getTotalValue() {
        return stocks.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "stocks=" + stocks.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": " + entry.getValue())
                .collect(Collectors.joining(", ")) +
                '}';
    }
}
