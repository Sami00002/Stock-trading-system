package org.example;

interface TradingStrategy {
    void execute(TradingService service, Customer customer, Stock stock, int quantity);
}

enum TradingStrategyType {
    DAY_TRADING,
    LONG_TERM_INVESTING
}
