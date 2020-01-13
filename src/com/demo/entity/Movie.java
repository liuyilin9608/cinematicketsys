package com.demo.entity;

import com.demo.util.Utils;

/**
 * 时间: 2017/11/18 18:52
 * 功能: 电影bean
 */

public class Movie implements Comparable<Movie> {
    public int id;
    public String name;//片名
    public String pic_name;//图片文件名
    public String director;//导演
    public String protagonist;//主演
    public String region;//地区
    public String language;//语言
    public String type;//类型
    public int duration;//时长
    public String synopsis;//剧情简介

    public String getPic_name() {
        return pic_name;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(String protagonist) {
        this.protagonist = protagonist;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public int compareTo(Movie o) {
        return Utils.compare(this, name, o, o.name);
    }
}
