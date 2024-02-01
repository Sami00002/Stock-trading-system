package org.example;

public class DayTradingStrategy implements TradingStrategy {
    private static final double DAY_TRADE_FEE_PERCENTAGE = 5.0;

    @Override
    public void execute(TradingService service, Customer customer, Stock stock, int quantity) {
        double totalPrice = stock.getPrice() * quantity;

        if (isDayTrade(stock)) {
            // Apply day trading fee
            double tradingFee = (DAY_TRADE_FEE_PERCENTAGE / 100) * totalPrice;
            if (customer.getPortfolio().getTotalValue() < tradingFee) {
                System.out.println("Insufficient funds to cover day trading fee.");
                return;
            }

            customer.getPortfolio().removeStock(stock, quantity);
            System.out.println("Day trading fee applied. " + customer.getName() +
                    " sold " + quantity + " shares of " + stock.getName() +
                    " for a total of $" + totalPrice +
                    " with a day trading fee of $" + tradingFee);
        } else {
            // Regular selling for non-day trades
            service.sellStock(customer, stock.getTickerSymbol(), quantity);
        }
    }

    private boolean isDayTrade(Stock stock) {
        return true;
    }
}
