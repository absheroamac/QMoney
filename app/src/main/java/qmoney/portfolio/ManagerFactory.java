package qmoney.portfolio;

import org.springframework.web.client.RestTemplate;

public class ManagerFactory {

    RestTemplate restTemplate;
    String apiKey;

    public ManagerFactory(String apiKey) {
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
    }

    public ManagerFactory() {
        this.restTemplate = new RestTemplate();
    }

    public PortfolioManager getPortfolioManager() {
        return new PortfolioManager(restTemplate, apiKey);
    }

    public PortfolioManager setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return new PortfolioManager(restTemplate, apiKey);
    }

}
