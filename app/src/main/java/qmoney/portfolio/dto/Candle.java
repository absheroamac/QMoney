package qmoney.portfolio.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Candle {

    private double open;
    private double close;
    private double high;
    private double low;
    private LocalDate date;

    public double getOpen() {
        return this.open;
    }

    public double getClose() {
        return this.close;
    }

    public double getHight() {
        return this.high;
    }

    public double getLow() {
        return this.low;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
