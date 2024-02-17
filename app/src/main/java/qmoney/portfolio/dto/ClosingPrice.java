package qmoney.portfolio.dto;

public class ClosingPrice {
    private String symbol;
    private double closingPrice;

    public ClosingPrice(String symbol, double closingPrice) {
        this.symbol = symbol;
        this.closingPrice = closingPrice;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public double getClosingPrice() {
        return this.closingPrice;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

}
