package org.example;

import java.util.*;

public class StockMarket {
    private Map<String, Stock> stocks = new HashMap<>();


    public void addStock(Stock stock) {
        stocks.put(stock.getTickerSymbol(), stock);
    }

    public Stock getStock(String tickerSymbol) {
        return stocks.get(tickerSymbol);
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("StockMarket{\n");
        for (Stock stock : stocks.values()) {
            result.append(stock.toString()).append("\n");
        }
        result.append("}");
        return result.toString();
    }


    public List<Stock> getStocks() {
        return new ArrayList<>(stocks.values());
    }
}
