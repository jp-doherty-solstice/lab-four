package com.solstice.quoteservice.controller;

import com.solstice.quoteservice.entity.StockData;
import com.solstice.quoteservice.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/load")
    void loadQuotes() throws IOException {
        quoteService.loadQuotes();
    }

    @GetMapping("/{symbol}/{dateString}")
    @ResponseBody StockData getData(@PathVariable String symbol, @PathVariable String dateString) throws ParseException {
        return quoteService.getSummary(symbol, dateString);
    }

    @GetMapping("/{symbol}/{dateString}/monthly")
    @ResponseBody StockData getMonthlyData(@PathVariable String symbol, @PathVariable String dateString) throws ParseException {
        return quoteService.getMonthlySummary(symbol, dateString);
    }

}
