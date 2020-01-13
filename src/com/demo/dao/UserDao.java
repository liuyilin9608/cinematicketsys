package com.demo.dao;

import com.demo.entity.User;

import java.util.List;

/**
 * 时间: 2017/11/19 10:17
 * 功能: 操作用户数据库
 */

public interface UserDao extends Dao {
    String TABLE_NAEM = DB_NAME + ".user";
    String CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAEM + " (id int primary key auto_increment," +
            "username varchar(20),password text,nickname text,phone text,reg_time bigint)";
    String COLUME_ID = "id";
    String COLUME_USERNAME = "username";
    String COLUME_PASSWORD = "password";
    String COLUME_NICKNAME = "nickname";
    String COLUME_PHONE = "phone";

    /**
     * 查询用户
     * @param columnNames 列名
     * @param values 占位符对应的值
     */    
    User query(String[] columnNames, Object... values);

    /**
     * 添加用户
     */
    User add(User user);

    /**
     * 获取所有用户
     */
    List<User> queryAll();

    /**
     * 删除用户
     */
    boolean delete(String[] columnNames, Object... values);

    /**
     * 验证用户
     * @param username 用户名
     * @param password 密码
     */
    User valicateUser(String username, String password);

    /**
     * 更新信息
     * @param username 用户名
     * @param password 旧密码
     * @param newpwd 新密码
     */
    int changePassword(String username, String password, String newpwd);

    /**
     * 修改用户资料
     * @param user 用户
     */
    int updateInfo(User user);

    /**
     * 通过关键字查询用户列表
     * @param keyword 关键字
     */
    List<User> queryUsersByKeyword(String keyword);
}
