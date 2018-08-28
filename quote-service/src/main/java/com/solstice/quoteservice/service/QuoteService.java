package com.solstice.quoteservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.solstice.quoteservice.entity.Quote;
import com.solstice.quoteservice.entity.StockData;
import com.solstice.quoteservice.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient client;

    @Bean
    RestTemplate restTemplate() { return new RestTemplate(); }

    @Autowired
    RestTemplate restTemplate;

    public void loadQuotes() throws IOException {
        Quote[] quotesArray = extractQuotesFromJson();
        for (int i = 0; i < 60; i++) {
            quoteRepository.save(quotesArray[i]);
        }
        // quoteRepository.saveAll(Arrays.asList(quotesArray));
    }

    private Quote[] extractQuotesFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper().setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        URL url = new URL("https://bootcamp-training-files.cfapps.io/week4/week4_stocks.json");
        return mapper.readValue(url, Quote[].class);
    }

    public StockData getSummary(String symbol, String dateString) throws ParseException {
        Integer stockKey = getStockKey(symbol);
        Timestamp timestamp = getTimestamp(dateString);
        StockData data = quoteRepository.getData(stockKey, timestamp);
        data.setClosingPrice(quoteRepository.getClosingPrice(stockKey, timestamp));
        return data;
    }

    public Integer getStockKey(String symbol) {
        InstanceInfo info = client.getNextServerFromEureka("stock-service", false);
        String url = info.getHomePageUrl();
        ResponseEntity responseEntity = restTemplate.postForEntity(url, symbol, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    private static Timestamp getTimestamp(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateString);
        return new Timestamp(date.getTime());
    }

    public StockData getMonthlySummary(String symbol, String dateString) throws ParseException {
        Integer stockKey = getStockKey(symbol);
        Timestamp timestamp = getTimestamp(dateString);
        StockData data = quoteRepository.getMonthlyData(stockKey, timestamp);
        data.setClosingPrice(quoteRepository.getMonthlyClosingPrice(stockKey, timestamp));
        return data;
    }

}
