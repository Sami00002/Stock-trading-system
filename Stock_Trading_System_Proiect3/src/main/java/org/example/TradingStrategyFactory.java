package org.example;

class TradingStrategyFactory {
    public static TradingStrategy createTradingStrategy(TradingStrategyType type) {
        switch (type) {
            case DAY_TRADING:
                return new DayTradingStrategy();
            case LONG_TERM_INVESTING:
                return new LongTermInvestingStrategy();
            default:
                throw new IllegalArgumentException("Unknown trading strategy type: " + type);
        }
    }
}
