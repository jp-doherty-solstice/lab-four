package com.solstice.quoteservice.repository;

import com.solstice.quoteservice.entity.Quote;
import com.solstice.quoteservice.entity.StockData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    String baseQuery = "select new com.solstice.quoteservice.entity.StockData(max(q.price), min(q.price), " +
            "sum(q.volume)) from Quote q where q.symbol = ?1";

    @Query(baseQuery + " and day(q.date) = day(?2)")
    StockData getData(int stockKey, Timestamp timestamp);

    @Query(baseQuery + " and month(q.date) = month(?2)")
    StockData getMonthlyData(int stockKey, Timestamp timestamp);

    @Query(value = "select price from quotes where symbol = ?1 and day(date) = day(?2) order by date desc limit 1", nativeQuery = true)
    Double getClosingPrice(int stockKey, Timestamp timestamp);

    @Query(value = "select price from quotes where symbol = ?1 and month(date) = month(?2) order by date desc limit 1",
            nativeQuery = true)
    Double getMonthlyClosingPrice(int stockKey, Timestamp timestamp);

}
