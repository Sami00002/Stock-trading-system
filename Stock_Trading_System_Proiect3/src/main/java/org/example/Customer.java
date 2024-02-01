package org.example;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String name;
    private String username;
    private String password;
    private Portfolio portfolio = new Portfolio();
    private double balance;  // Holds the customer's balance
    private static Map<String, Customer> customers = new HashMap<>();

    public Customer(String name, String username, String password, double initialBalance) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.balance = initialBalance;  // Set the initial balance
        customers.put(username, this);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
