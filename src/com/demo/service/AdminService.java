package com.demo.service;

import com.demo.exception.MyException;
import com.demo.entity.Admin;

/**
 * 时间: 2017/11/19 19:46
 * 功能:
 */

public interface AdminService extends Service {

    /**
     * 添加系统管理员
     * @param username 用户名
     * @param password 密码
     */
    void addSysAdmin(String username, String password);

    /**
     * 验证管理员有效性
     * @param username 用户名
     * @param password 密码
     * @return 如果正确，返回此管理员的实例
     */
    Admin valicateAdmin(String username, String password) throws MyException;
}
