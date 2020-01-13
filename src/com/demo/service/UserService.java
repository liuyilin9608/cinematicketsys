package com.demo.service;

import com.demo.exception.MyException;
import com.demo.entity.User;

import java.util.List;

/**
 * 时间: 2017/11/19 19:45
 * 功能:
 */

public interface UserService extends Service {

    /**
     * 获取所有用户
     */
    List<User> queryAll();

    /**
     * 删除用户
     */
    boolean delete(String id);

    /**
     * 验证用户
     * @param username 用户名
     * @param password 密码
     */
    User valicateUser(String username, String password) throws MyException;

    /**
     * 注册用户
     * @param user 用户信息
     * @param confirmPwd 确认密码
     */
    User registerUser(User user, String confirmPwd) throws MyException;

    /**
     * 修改密码
     * @param username 用户名
     * @param password 原密码
     * @param newpwd 新密码
     */
    void changePassword(String username, String password, String newpwd) throws MyException;

    /**
     * 修改用户资料
     * @param user 用户
     */
    User updateInfo(User user) throws MyException;

    /**
     * 根据id查用户
     * @param userId 用户id
     */
    User query(String userId);

    /**
     * 通过关键字查询用户列表
     * @param keyword 关键字
     */
    List<User> queryUsersByKeyword(String keyword);

    User queryByUsername(String name);
    
    void deleteByUsername(String name);
}
