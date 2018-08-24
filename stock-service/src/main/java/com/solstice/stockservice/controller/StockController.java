package com.solstice.stockservice.controller;

import com.solstice.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/")
    public @ResponseBody Integer getKey(@RequestBody String symbol) {
        return stockService.getStockKey(symbol);
    }

}
