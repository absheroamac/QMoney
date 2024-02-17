package qmoney.portfolio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.fasterxml.jackson.core.exc.StreamReadException;
//import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.client.RestTemplate;

import qmoney.portfolio.comparators.AnnualizedReturnComparator;
import qmoney.portfolio.comparators.ClosingPriceComparator;
import qmoney.portfolio.dto.AnnualizedReturn;
import qmoney.portfolio.dto.Candle;
import qmoney.portfolio.dto.ClosingPrice;
import qmoney.portfolio.dto.Trade;

public class PortfolioManager implements IPortfolioManager {

    RestTemplate restTemplate;
    String apiKey;

    public PortfolioManager(RestTemplate restTemplate, String apiKey) {

        this.restTemplate = restTemplate;
        this.apiKey = apiKey;

    }

    // final String apiKey = "746f309a712285339cdcbeb991b3b612c1ebf1f7";
    final String fileName = "trade.json";
    // final String absolutePath = "C:\\Users\\abshe\\OneDrive\\Documents\\Learning
    // Crio\\QMoney\\QMoney\\app\\src\\main\\resources\\trade.json";
    // final String relativePath = "app\\src\\main\\resources\\trade.json";

    // https:api.tiingo.com/tiingo/daily/AAPL/prices?startDate=2020-01-01&endDate=2020-01-01&token=746f309a712285339cdcbeb991b3b612c1ebf1f7

    public List<AnnualizedReturn> getInsight(String[] args) throws IOException, URISyntaxException {

        List<Trade> tradesList = readTrades(args[0]);
        return getAnnualizedReturns(tradesList, LocalDate.parse(args[1]));

    }

    // @Override
    public List<Trade> readTrades(String fileName) throws IOException, URISyntaxException {

        List<Trade> tradesList = new ArrayList<>();

        // File file = resolveFileFromResources(fileName);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).toURI());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Trade[] trades = objectMapper.readValue(file, Trade[].class);

        for (Trade trade : trades) {
            System.out.println(trade.getSymbol());
            tradesList.add(trade);
        }

        return tradesList;

    }

    public List<ClosingPrice> getClosingPrice() throws IOException, URISyntaxException {

        LocalDate closingDate = LocalDate.parse("2020-01-01");
        List<Trade> trades = readTrades(fileName);
        List<ClosingPrice> result = new ArrayList<>();

        for (Trade trade : trades) {

            List<Candle> fetchedTrades = getStockQuotes(trade.getSymbol(), trade.getPurchasDate(), closingDate);
            Candle fetchedTrade = fetchedTrades.get(fetchedTrades.size() - 1);
            ClosingPrice closingPrice = new ClosingPrice(trade.getSymbol(), fetchedTrade.getClose());
            result.add(closingPrice);

        }

        result.sort(new ClosingPriceComparator());

        for (ClosingPrice trade : result) {
            System.out.println(trade.getSymbol());
        }
        return result;

    }

    public List<Candle> getStockQuotes(String symbol, LocalDate from, LocalDate to) throws URISyntaxException {
        List<Candle> candles = new ArrayList<>();
        // RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(buildUrl(symbol, from, to));
        Candle[] fetchCandles = restTemplate.getForObject(uri, Candle[].class);
        candles = Arrays.asList(fetchCandles);

        return candles;

    }

    public File resolveFileFromResources(String fileName) {
        Path path = Paths.get(fileName);
        return path.toFile();

    }

    public String buildUrl(String symbol, LocalDate starting, LocalDate closing) {

        String uriTemplate = "https://api.tiingo.com/tiingo/daily/$SYMBOL/prices?"
                + "startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";

        uriTemplate = uriTemplate.replace("$SYMBOL", symbol)
                .replace("$STARTDATE", starting.toString())
                .replace("$ENDDATE", closing.toString())
                .replace("$APIKEY", apiKey);

        return uriTemplate;

    }

    public double getBuyPrice(List<Candle> candles) {
        return candles.get(0).getOpen();
    }

    public double getSellPrice(List<Candle> candles) {
        return candles.get(candles.size() - 1).getClose();
    }

    @Override
    public List<AnnualizedReturn> getAnnualizedReturns(List<Trade> portfolioTrades, LocalDate endDate)
            throws URISyntaxException {

        List<AnnualizedReturn> output = new ArrayList<>();
        for (Trade portfolioTrade : portfolioTrades) {
            List<Candle> candles = getStockQuotes(portfolioTrade.getSymbol(), portfolioTrade.getPurchasDate(), endDate);

            double buyPrice = getBuyPrice(candles);
            double sellPrice = getSellPrice(candles);

            output.add(calculateAnnualizedReturn(buyPrice, sellPrice, portfolioTrade, endDate));
        }
        output.sort(new AnnualizedReturnComparator());
        return output;
    }

    public AnnualizedReturn calculateAnnualizedReturn(double buyPrice, double sellPrice, Trade trade, LocalDate end) {

        long daysInBetween = end.toEpochDay() - trade.getPurchasDate().toEpochDay();
        double years = daysInBetween / 365.25;

        double totalReturn = (sellPrice - buyPrice) / buyPrice;
        double base = totalReturn + 1;
        double exponent = 1 / years;

        double AnnualizedReturn = Math.pow(base, exponent);

        return new AnnualizedReturn(trade.getSymbol(), AnnualizedReturn, totalReturn);

    }

}
