package qmoney.portfolio.dto;

public class AnnualizedReturn {

    private String symbol;
    private double annualizedReturn;
    private double totalReturn;

    public AnnualizedReturn(String symbol, double annualizedReturn, double totalReturn) {
        this.symbol = symbol;
        this.annualizedReturn = annualizedReturn;
        this.totalReturn = totalReturn;

    }

    public double getAnnualizedReturn() {
        return this.annualizedReturn;
    }

    @Override
    public String toString() {
        return "{Symbol : " + symbol + ", Total Return : " + totalReturn + ", Annualized Return : " + annualizedReturn
                + " }";
    }

}
