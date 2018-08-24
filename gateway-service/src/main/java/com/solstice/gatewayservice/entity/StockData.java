package com.solstice.gatewayservice.entity;

import static java.lang.Math.toIntExact;

public class StockData {

    private Double dailyHigh;
    private Double dailyLow;
    private int totalVolume;
    private Double closingPrice;

    public StockData(Double dailyHigh, Double dailyLow, Long totalVolume) {
        this.dailyHigh = dailyHigh;
        this.dailyLow = dailyLow;
        this.totalVolume = toIntExact(totalVolume);
        this.closingPrice = null;
    }

    public Double getDailyHigh() {
        return dailyHigh;
    }

    public void setDailyHigh(Double dailyHigh) {
        this.dailyHigh = dailyHigh;
    }

    public Double getDailyLow() {
        return dailyLow;
    }

    public void setDailyLow(Double dailyLow) {
        this.dailyLow = dailyLow;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }

}
