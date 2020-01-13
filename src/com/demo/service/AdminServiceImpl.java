package com.demo.service;

import com.demo.exception.MyException;
import com.demo.dao.AdminDao;
import com.demo.dao.UserDao;
import com.demo.entity.Admin;
import com.demo.factory.BaseFactory;
import com.demo.util.Utils;

/**
 * 时间: 2017/11/19 19:49
 * 功能: 操作数据库的业务
 */

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = BaseFactory.getDao(AdminDao.class);
    
    @Override
    public void addSysAdmin(String username, String password) {
        //添加系统管理员
        if (adminDao.query(new String[]{UserDao.COLUME_USERNAME}, "admin") == null) {
            //如果不存在，添加默认系统管理员
            adminDao.add("admin", "123456");
        }
    }

    @Override
    public Admin valicateAdmin(String username, String password) throws MyException {
        if (Utils.isEmpty(username) || Utils.isEmpty(password)) {
            throw new MyException("用户名或密码不正确");
        } else {
            Admin admin = adminDao.valicateAdmin(username, password);
            if (admin == null) {
                throw new MyException("用户名或密码不正确");
            }
            return admin;
        }
    }
}
