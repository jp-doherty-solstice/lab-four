package com.solstice.stockservice.repository;

import com.solstice.stockservice.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Symbol, Long> {

    @Query(value = "select s.symbolKey from Symbol s where s.name = ?1")
    int getSymbolId(String name);

}
