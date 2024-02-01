package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class TradingService {
    private StockMarket market;
    private List<Transaction> transactionHistory;
    private Map<String, Customer> customerAccounts;
    private TradingStrategy tradingStrategy;

    public TradingService(StockMarket market) {
        this.market = market;
        this.transactionHistory = new ArrayList<>();
        this.customerAccounts = new HashMap<>();
        this.tradingStrategy = TradingStrategyFactory.createTradingStrategy(TradingStrategyType.DAY_TRADING);
    }

    public void setTradingStrategy(TradingStrategyType type) {
        this.tradingStrategy = TradingStrategyFactory.createTradingStrategy(type);
    }

    private void recordTransaction(String customerId, String stockSymbol, int quantity, double pricePerUnit, TransactionType type) {
        Transaction transaction = new Transaction(customerId, stockSymbol, quantity, pricePerUnit, type);
        transactionHistory.add(transaction);
    }

    public void buyStock(Customer customer, String tickerSymbol, int quantity) {
        Stock stock = market.getStock(tickerSymbol);
        if (stock == null) {
            System.out.println("Stock with ticker symbol " + tickerSymbol + " not found.");
            return;
        }

        double totalPrice = stock.getPrice() * quantity;
        if (customer.getBalance() < totalPrice) {
            System.out.println("Insufficient funds to buy " + quantity + " shares of " + stock.getName());
            return;
        }

        customer.getPortfolio().addStock(stock, quantity);
        customer.setBalance(customer.getBalance() - totalPrice);
        recordTransaction(customer.getUsername(), tickerSymbol, quantity, stock.getPrice(), TransactionType.BUY);
        System.out.println(customer.getName() + " bought " + quantity + " shares of " + stock.getName() +
                " for a total of $" + totalPrice);
    }

    public void sellStock(Customer customer, String tickerSymbol, int quantity) {
        Stock stock = market.getStock(tickerSymbol);
        if (stock == null) {
            System.out.println("Stock with ticker symbol " + tickerSymbol + " not found.");
            return;
        }

        int ownedQuantity = customer.getPortfolio().getStockQuantity(stock);
        if (quantity > ownedQuantity) {
            System.out.println("You don't own enough shares of " + stock.getName() + " to sell.");
            return;
        }

        customer.getPortfolio().removeStock(stock, quantity);
        double totalPrice = stock.getPrice() * quantity;
        customer.setBalance(customer.getBalance() + totalPrice);  // Add the selling revenue
        recordTransaction(customer.getUsername(), tickerSymbol, quantity, stock.getPrice(), TransactionType.SELL);
        System.out.println(customer.getName() + " sold " + quantity + " shares of " + stock.getName() +
                " for a total of $" + totalPrice);
    }


    public List<TradingStrategyType> getAvailableTradingStrategies() {
        return Arrays.asList(TradingStrategyType.values());
    }

    public Customer register(String name, String username, String password, double v) {
        if (customerAccounts.containsKey(username)) {
            System.out.println("Username already exists.");
            return null;
        }
        Customer customer = new Customer(name, username, password, 1000.0);  // Initial balance of $1000
        customerAccounts.put(username, customer);
        return customer;
    }

    public Customer login(String username, String password) {
        Customer customer = customerAccounts.get(username);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }


    public List<Transaction> getTransactionHistory(Customer customer) {
        return transactionHistory.stream()
                .filter(t -> t.getCustomerId().equals(customer.getUsername()))
                .collect(Collectors.toList());
    }
}
