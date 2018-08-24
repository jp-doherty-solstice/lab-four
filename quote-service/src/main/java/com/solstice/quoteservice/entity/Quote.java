package com.solstice.quoteservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Quotes")
public class Quote {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="symbol")
    private int symbol;

    @Column(name="price")
    private Double price;

    @Column(name="volume")
    private int volume;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss.SSS")
    @Column(name="date")
    private Timestamp date;

    public Quote() {}

    public Quote(int stock, Double price, int volume, Timestamp date) {
        this.symbol = stock;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
