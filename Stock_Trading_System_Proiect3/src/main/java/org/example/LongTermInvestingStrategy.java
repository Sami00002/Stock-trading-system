package org.example;

public class LongTermInvestingStrategy implements TradingStrategy {
    private static final int MIN_HOLDING_DAYS = 1; // Minimum holding days to be considered long-term

    @Override
    public void execute(TradingService service, Customer customer, Stock stock, int quantity) {
        int holdingDays = calculateHoldingDays(stock);

        if (holdingDays >= MIN_HOLDING_DAYS) {
            // Apply long-term investing logic
            System.out.println(customer.getName() +
                    " is a long-term investor. " +
                    "No action taken for selling " + quantity + " shares of " + stock.getName());
        } else {
            // Regular selling for non-long-term investments
            service.sellStock(customer, stock.getTickerSymbol(), quantity);
        }
    }

    private int calculateHoldingDays(Stock stock) {
        return (int) (Math.random() * 10); // Random number for demonstration purposes
    }
}
