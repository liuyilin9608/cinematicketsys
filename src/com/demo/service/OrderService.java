package com.demo.service;

import com.demo.exception.MyException;
import com.demo.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * 时间: 2017/11/23 21:48
 * 功能:
 */

public interface OrderService extends Service {
    /**
     * 此座位是否已售
     * @param planId 排片id
     * @param seat 座位。1_3表示1排3座
     */
    boolean isSeatSold(String planId, String seat);

    /**
     * 生成订单
     * @param order 订单
     */
    void add(Order order) throws MyException;

    /**
     * 获取所有订单
     */
    List<Order> queryAll();

    /**
     * 查询订单
     * @param id 订单id
     */
    Order query(String id);

    /**
     * 查询用户所有订单
     * @param userId 用户id
     */
    List<Order> queryUserOrders(String userId);

    /**
     * 删除订单
     */
    void delete(String id);

    /**
     * 更新金额
     * @param id 订单id
     * @param amount 金额
     */
    void update(String id, double amount);

    /**
     * 获取实时票房
     */
    Map<String,Double> queryRealtimeSales();

    /**
     * 获取累计票房
     */
    Map<String,Double> queryTotalSales();
}
