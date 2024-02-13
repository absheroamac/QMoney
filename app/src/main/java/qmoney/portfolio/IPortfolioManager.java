package qmoney.portfolio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import qmoney.portfolio.dto.Trade;

public interface IPortfolioManager {

    public List<Trade> readTrades(String fileName) throws IOException, URISyntaxException;
    
}
