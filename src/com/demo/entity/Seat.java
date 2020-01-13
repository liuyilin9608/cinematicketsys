package com.demo.entity;

/**
 * 描述: 座位信息
 * 时间: 2017/11/30 19:20
 */

public class Seat {
    public String seat;
    public boolean sold;

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
