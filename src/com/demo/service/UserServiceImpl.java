package com.demo.service;

import com.demo.exception.MyException;
import com.demo.dao.OrderDao;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.factory.BaseFactory;
import com.demo.util.Utils;

import java.util.Collections;
import java.util.List;

/**
 * 时间: 2017/11/19 20:37
 * 功能: 业务
 */

public class UserServiceImpl implements UserService {
    private UserDao userDao = BaseFactory.getDao(UserDao.class);
    private OrderDao orderDao = BaseFactory.getDao(OrderDao.class);

    @Override
    public List<User> queryAll() {
        List<User> list = userDao.queryAll();
        if (list != null) {
            Collections.sort(list);
        }
        return list;
    }

    @Override
    public boolean delete(String id) {
        //如果删除成功，将用户相关的也删除
        boolean result = userDao.delete(new String[]{UserDao.COLUME_ID}, id);
        if (result) {
            orderDao.delete(new String[]{OrderDao.COLUME_USER_ID}, id);
        }
        return result;
    }

    @Override
    public User valicateUser(String username, String password) throws MyException {
        if (Utils.isEmpty(username)) {
            throw new MyException("用户名不能为空");
        } else if (Utils.isEmpty(password)) {
            throw new MyException("密码不能为空");
        } else if (userDao.query(new String[]{UserDao.COLUME_USERNAME}, username) == null) {
            throw new MyException("用户不存在");
        } else {
            User user = userDao.valicateUser(username, password);
            if (user == null) {
                throw new MyException("用户名或密码不正确");
            }
            return user;
        }
    }

    @Override
    public User registerUser(User user, String confirmPwd) throws MyException {
        if (Utils.isEmpty(user.username)) {
            throw new MyException("用户名不能为空");
        } else if (Utils.isEmpty(user.password)) {
            throw new MyException("密码不能为空");
        } else if (Utils.isEmpty(user.nickname)) {
            throw new MyException("昵称不能为空");
        } else if (Utils.isEmpty(user.phone)) {
            throw new MyException("手机号不能为空");
        } else if (!user.phone.matches("^\\d{11}$")) {
            throw new MyException("请输入正确的手机号");
        } else if (!Utils.isPasswordValid(user.password)) {
            throw new MyException("密码格式: 6-30位，包含两种以上字符");
        } else if (!user.password.equals(confirmPwd)) {
            throw new MyException("输入的两次密码不一致");
        } else if (userDao.query(new String[]{UserDao.COLUME_USERNAME}, user.username) != null) {
            throw new MyException("用户已存在");
        } else {
            userDao.add(user);
            User u = userDao.query(new String[]{UserDao.COLUME_USERNAME}, user.username);
            if (u == null) {
                throw new MyException("注册失败");
            }
            return u;
        }
    }

    @Override
    public void changePassword(String username, String password, String newpwd) throws MyException {
        if (Utils.isEmpty(username)) {
            throw new MyException("用户名不能为空");
        } else if (Utils.isEmpty(password)) {
            throw new MyException("原密码不能为空");
        } else if (Utils.isEmpty(newpwd)) {
            throw new MyException("新密码不能为空");
        } else if (!Utils.isPasswordValid(newpwd)) {
            throw new MyException("密码格式: 6-30位，包含两种以上字符");
        } else if (userDao.query(new String[]{UserDao.COLUME_USERNAME}, username) == null) {
            throw new MyException("用户不存在");
        } else if (userDao.valicateUser(username, password) == null) {
            throw new MyException("原密码不正确");
        } else {
            userDao.changePassword(username, password, newpwd);
        }
    }

    @Override
    public User updateInfo(User user) throws MyException {
        if (Utils.isEmpty(user.nickname)) {
            throw new MyException("昵称不能为空");
        } else if (Utils.isEmpty(user.phone)) {
            throw new MyException("手机号不能为空");
        } else if (!user.phone.matches("^((1[358][0-9])|(14[57])|(17[0678])|(19[7]))\\d{8}$")) {
            throw new MyException("请输入正确的手机号");
        } else {
            userDao.updateInfo(user);
            return userDao.query(new String[]{UserDao.COLUME_USERNAME}, user.username);
        }
    }

    @Override
    public User query(String userId) {        
        return userDao.query(new String[]{UserDao.COLUME_ID}, userId);
    }

    @Override
    public List<User> queryUsersByKeyword(String keyword) {
        List<User> list = userDao.queryUsersByKeyword(keyword);
        if (list != null) {
            Collections.sort(list);
        }
        return list;
    }

    @Override
    public User queryByUsername(String name) {
        return userDao.query(new String[]{UserDao.COLUME_USERNAME}, name);
    }

    @Override
    public void deleteByUsername(String name) {
        userDao.delete(new String[]{UserDao.COLUME_USERNAME}, name);
    }
}
