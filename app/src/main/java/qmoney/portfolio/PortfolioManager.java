package qmoney.portfolio;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.client.RestTemplate;

import qmoney.portfolio.dto.Trade;

public class PortfolioManager implements IPortfolioManager{

    final String apiKey = "746f309a712285339cdcbeb991b3b612c1ebf1f7";

    @Override
    public List<Trade> readTrades(String fileName) throws IOException, URISyntaxException {
        
        List<Trade> tradesList = new ArrayList<>();

        File file = resolveFileFromResources(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Trade[] trades = objectMapper.readValue(file, Trade[].class);

        for(Trade trade:trades){
            System.out.println(trade.symbol);
            tradesList.add(trade);
        }

        return tradesList;

        
        
    }

    public void fetchFromAPI(){

        LocalDate closingDate = LocalDate.parse("2020-01-01");
        RestTemplate restTemplate = new RestTemplate();


    }

    public File resolveFileFromResources(String fileName){
        Path path = Paths.get(fileName);
        return path.toFile();
        
    }

    public String buildUrl(String symbol,LocalDate closing){

        String uriTemplate = "https:api.tiingo.com/tiingo/daily/$SYMBOL/prices?"
            + "startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";

        uriTemplate = uriTemplate.replace("$SYMBOL", symbol)
                                 .replace("$STARTDATE", closing.toString())
                                 .replace("$ENDDATE", closing.toString())
                                 .replace("$APIKEY", apiKey);

        return uriTemplate;

    }

    
    
}
