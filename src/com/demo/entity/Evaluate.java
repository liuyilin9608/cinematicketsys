package com.demo.entity;

/**
 * 时间: 2017/11/18 19:07
 * 功能: 影评bean
 */

public class Evaluate {
    public int id;
    public long time;//评价时间
    public String username;//用户名
    public String nickname;//用户昵称
    public String movie_name;//片名
    public String content;//留言内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
