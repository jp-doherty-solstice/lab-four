package com.solstice.stockservice.entity;

import javax.persistence.*;

@Entity
public class Symbol {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int symbolKey;

    public Symbol() {
    }

    public Symbol(String name, int symbolKey) {
        this.name = name;
        this.symbolKey = symbolKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSymbolId() {
        return symbolKey;
    }

    public void setSymbolId(int symbolId) {
        this.symbolKey = symbolId;
    }

}
