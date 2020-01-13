package com.demo.dao;

import com.demo.factory.BaseFactory;
import com.demo.mgr.DataSourceMgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 描述:数据库初始化，如创建表
 * 时间: 2017/12/1 11:50
 */

public class DaoInitializer {
    
    public static void init() {
        AdminDao adminDao = BaseFactory.getDao(AdminDao.class);
        EvaluateDao evaluateDao = BaseFactory.getDao(EvaluateDao.class);
        HallDao hallDao = BaseFactory.getDao(HallDao.class);
        MovieDao movieDao = BaseFactory.getDao(MovieDao.class);
        OrderDao orderDao = BaseFactory.getDao(OrderDao.class);
        PlanDao planDao = BaseFactory.getDao(PlanDao.class);
        UserDao userDao = BaseFactory.getDao(UserDao.class);
        ReplyDao replyDao = BaseFactory.getDao(ReplyDao.class);
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DataSourceMgr.getSource().getConnection();
            statement = conn.createStatement();
            statement.execute(adminDao.getCreateTableSql());
            statement.execute(evaluateDao.getCreateTableSql());
            statement.execute(hallDao.getCreateTableSql());
            statement.execute(movieDao.getCreateTableSql());
            statement.execute(orderDao.getCreateTableSql());
            statement.execute(planDao.getCreateTableSql());
            statement.execute(userDao.getCreateTableSql());            
            statement.execute(replyDao.getCreateTableSql());            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
