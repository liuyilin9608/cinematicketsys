package com.demo.dao;

import com.demo.entity.Order;
import com.demo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 时间: 2017/11/23 21:38
 * 功能: 
 */

public interface OrderDao extends Dao {
    String TABLE_NAEM = DB_NAME + ".orders";
    String CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAEM + " (id int primary key auto_increment," +
            "user_id int,plan_id int,movie_name text,seat text,amount double,create_time bigint)";
    String COLUME_ID = "id";
    String COLUME_USER_ID = "user_id";
    String COLUME_PLAN_ID = "plan_id";
    String COLUME_MOVIE_NAME = "movie_name";
    String COLUME_SEAT = "seat";
    String COLUME_AMOUNT = "amount";
    String COLUME_CREATE_TIME = "create_time";

    /**
     * 根据条件删除
     * @param columnNames 列名
     * @param values 占位符对应的值
     */
    int delete(String[] columnNames, Object... values);

    Order query(String[] columnNames, Object... values);

    User add(Order order);

    boolean isSeatSold(String planId, String seat);

    List<Order> queryAll();

    List<Order> getUserOrders(String userId);
    
    int update(String id, double amount);
    
    Map<String, Double> queryRealtimeSales();

    Map<String, Double> queryTotalSales();
}
