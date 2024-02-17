package qmoney.portfolio;

//import java.io.File;
//import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import qmoney.portfolio.dto.AnnualizedReturn;
import qmoney.portfolio.dto.Candle;
//import qmoney.portfolio.dto.Candle;
//import qmoney.portfolio.dto.ClosingPrice;
import qmoney.portfolio.dto.Trade;

public interface IPortfolioManager {

    // public List<Trade> readTrades(String fileName) throws IOException,
    // URISyntaxException;

    // public List<AnnualizedReturn> getInsight(String[] args) throws IOException,
    // URISyntaxException;

    // public List<ClosingPrice> getClosingPrice() throws IOException,
    // URISyntaxException;

    public List<Candle> getStockQuotes(String symbol, LocalDate from, LocalDate to) throws URISyntaxException;

    // public File resolveFileFromResources(String fileName);

    // public String buildUrl(String symbol, LocalDate starting, LocalDate closing);

    public List<AnnualizedReturn> getAnnualizedReturns(List<Trade> portfolioTrades, LocalDate endDate)
            throws URISyntaxException;

}
