package com.solstice.stockservice.service;

import com.solstice.stockservice.entity.Symbol;
import com.solstice.stockservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        stockRepository.deleteAll();
        List<Symbol> symbols = new ArrayList<>();
        symbols.add(new Symbol("AAPL", 1));
        symbols.add(new Symbol("GOOG", 2));
        symbols.add(new Symbol("MSFT", 3));
        symbols.add(new Symbol("PVTL", 4));
        symbols.add(new Symbol("AMZN", 5));
        stockRepository.saveAll(symbols);
    }

    public int getStockKey(String symbol) {
        return stockRepository.getSymbolId(symbol);
    }

}
