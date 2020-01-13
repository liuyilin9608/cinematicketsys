package com.demo.entity;

/**
 * 描述: 票房
 * 时间: 2017/12/2 23:27
 */

public class Sale {
    public String movieName;
    public double realtime;
    public double total;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getRealtime() {
        return realtime;
    }

    public void setRealtime(double realtime) {
        this.realtime = realtime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
