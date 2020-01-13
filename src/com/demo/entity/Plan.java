package com.demo.entity;

import com.demo.util.Utils;

/**
 * 时间: 2017/11/18 18:49
 * 功能: 排片bean
 */

public class Plan implements Comparable<Plan> {
    public int id;
    public String movie_name;//片名
    public String hall_name;//影厅名
    public double price;//价格
    public long play_time;//放映时间
    public boolean played;//是否已放映

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getPlay_time() {
        return play_time;
    }

    public void setPlay_time(long play_time) {
        this.play_time = play_time;
    }

    public boolean getPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    @Override
    public int compareTo(Plan o) {
        return Utils.compare(this, movie_name, o, o.movie_name);
    }
}
