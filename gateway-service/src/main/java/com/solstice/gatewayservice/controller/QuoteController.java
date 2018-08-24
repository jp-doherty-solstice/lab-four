package com.solstice.gatewayservice.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteController {

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient client;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/load")
    void loadQuotes() {
        InstanceInfo info = client.getNextServerFromEureka("quote-service", false);
        String baseUrl = info.getHomePageUrl();
        restTemplate.postForEntity(baseUrl + "/load", null, null);
    }

}
