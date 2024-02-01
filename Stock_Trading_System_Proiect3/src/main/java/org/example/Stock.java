package org.example;

public class Stock {
    private String name;
    private String tickerSymbol;
    private double price;
    private int quantity;

    public Stock(String name, String tickerSymbol, double price, int quantity) {
        this.name = name;
        this.tickerSymbol = tickerSymbol;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}