package org.example;

import java.util.List;
import java.util.Scanner;

public class TradingUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockMarket stockMarket = new StockMarket();
        stockMarket.addStock(new Stock("ABC Inc.", "ABC", 50.0, 100));
        stockMarket.addStock(new Stock("XYZ Corp.", "XYZ", 75.0, 50));

        TradingService tradingService = new TradingService(stockMarket);

        // Register a user with an initial balance
        Customer customer1 = tradingService.register("John Doe", "johnD", "pass123", 1000.0);
        Customer customer2 = tradingService.register("Samiii", "Sami", "sam", 2000.0);

        while (true) {
            System.out.println("\n===== Stock Trading System =====");
            System.out.println("1. Register");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner, tradingService);
                    break;
                case 2:
                    loginUser(scanner, tradingService);
                    break;
                case 3:
                    System.out.println("Exiting the Stock Trading System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner, TradingService tradingService) {
        System.out.println("\n===== User Registration =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        Customer customer = tradingService.register(name, username, password, 1000.0);

        if (customer != null) {
            System.out.println("Registration successful! Welcome, " + customer.getName() + "!");
            tradeOptions(scanner, tradingService, customer);
        } else {
            System.out.println("Username already exists. Please choose another one.");
        }
    }

    private static void loginUser(Scanner scanner, TradingService tradingService) {
        System.out.println("\n===== User Login =====");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Customer customer = tradingService.login(username, password);

        if (customer != null) {
            System.out.println("Login successful! Welcome back, " + customer.getName() + "!");
            tradeOptions(scanner, tradingService, customer);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void tradeOptions(Scanner scanner, TradingService tradingService, Customer customer) {
        while (true) {
            System.out.println("\n===== Trading Options =====");
            System.out.println("1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. View Portfolio");
            System.out.println("4. View Transaction History");
            System.out.println("5. Change Trading Strategy");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    buyStock(scanner, tradingService, customer);
                    break;
                case 2:
                    sellStock(scanner, tradingService, customer);
                    break;
                case 3:
                    viewPortfolio(tradingService, customer);
                    break;
                case 4:
                    viewTransactionHistory(tradingService, customer);
                    break;
                case 5:
                    changeTradingStrategy(scanner, tradingService);
                    break;
                case 6:
                    System.out.println("Logging out. Goodbye, " + customer.getName() + "!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void buyStock(Scanner scanner, TradingService tradingService, Customer customer) {
        System.out.println("\n===== Buy Stock =====");
        System.out.print("Enter stock ticker symbol: ");
        String tickerSymbol = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        tradingService.buyStock(customer, tickerSymbol, quantity);
    }

    private static void sellStock(Scanner scanner, TradingService tradingService, Customer customer) {
        System.out.println("\n===== Sell Stock =====");
        System.out.print("Enter stock ticker symbol: ");
        String tickerSymbol = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        tradingService.sellStock(customer, tickerSymbol, quantity);
    }

    private static void viewPortfolio(TradingService tradingService, Customer customer) {
        System.out.println("\n===== Portfolio =====");
        System.out.println(customer.getPortfolio());
    }

    private static void viewTransactionHistory(TradingService tradingService, Customer customer) {
        System.out.println("\n===== Transaction History =====");
        List<Transaction> transactions = tradingService.getTransactionHistory(customer);
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void changeTradingStrategy(Scanner scanner, TradingService tradingService) {
        System.out.println("\n===== Change Trading Strategy =====");
        System.out.println("Available Trading Strategies:");
        List<TradingStrategyType> strategies = tradingService.getAvailableTradingStrategies();
        for (int i = 0; i < strategies.size(); i++) {
            System.out.println((i + 1) + ". " + strategies.get(i));
        }

        System.out.print("Select a trading strategy (1-" + strategies.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice >= 1 && choice <= strategies.size()) {
            TradingStrategyType selectedStrategy = strategies.get(choice - 1);
            tradingService.setTradingStrategy(selectedStrategy);
            System.out.println("Trading strategy changed to: " + selectedStrategy);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}