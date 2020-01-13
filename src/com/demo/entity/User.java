package com.demo.entity;

import com.demo.util.Utils;

/**
 * 时间: 2017/11/18 18:32
 * 功能: 用户bean
 */

public class User implements Comparable<User> {    
    public int id;
    public String username;//用户名
    public String password;//密码
    public String nickname;//昵称
    public String phone;//手机号
    public long reg_time;//注册时间

    public long getReg_time() {
        return reg_time;
    }

    public void setReg_time(long reg_time) {
        this.reg_time = reg_time;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int compareTo(User o) {
        return Utils.compare(this, username, o, o.username);
    }
}
