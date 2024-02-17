package qmoney.portfolio.dto;

import java.time.LocalDate;

public class Trade {

    private String symbol;
    private int quantity;
    private String tradeType;
    private LocalDate purchaseDate;

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTradeType() {
        return tradeType;
    }

    public LocalDate getPurchasDate() {
        return purchaseDate;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
