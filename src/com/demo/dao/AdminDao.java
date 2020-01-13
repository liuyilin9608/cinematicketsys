package com.demo.dao;

import com.demo.entity.Admin;

/**
 * 时间: 2017/11/19 18:26
 * 功能:
 */

public interface AdminDao extends Dao {
    String TABLE_NAEM = DB_NAME + ".admin";
    String CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAEM + " (id int primary key auto_increment,username varchar(20),password text)";
    String COLUME_ID = "id";
    String COLUME_USERNAME = "username";
    String COLUME_PASSWORD = "password";
    
    /**
     * 查询管理员
     * @param columnNames 列名
     * @param values 占位符对应的值
     */
    Admin query(String[] columnNames, String... values);

    /**
     * 添加管理员
     * @param username 用户名
     * @param password 密码
     */
    Admin add(String username, String password);

    /**
     * 添加管理员
     */
    Admin add(Admin user);

    /**
     * 验证管理员用户名和密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return 正确则返回对象
     */
    Admin valicateAdmin(String username, String password);
}
