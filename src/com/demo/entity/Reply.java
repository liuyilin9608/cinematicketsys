package com.demo.entity;

/**
 * 描述:
 * 时间: 2018/3/25 22:08
 */

public class Reply {
    public int id;
    public String username;//用户名
    public String nickname;//用户昵称
    public int evaluate_id;//评论id
    public String content;//回复内容
    public long time;//回复时间


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getEvaluate_id() {
        return evaluate_id;
    }

    public void setEvaluate_id(int evaluate_id) {
        this.evaluate_id = evaluate_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
