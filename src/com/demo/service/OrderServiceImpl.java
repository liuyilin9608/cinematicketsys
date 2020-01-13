package com.demo.service;

import com.demo.exception.MyException;
import com.demo.dao.OrderDao;
import com.demo.entity.Order;
import com.demo.factory.BaseFactory;

import java.util.List;
import java.util.Map;

/**
 * 时间: 2017/11/24 11:26
 * 功能:
 */

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = BaseFactory.getDao(OrderDao.class);
    
    @Override
    public boolean isSeatSold(String planId, String seat) {
        return orderDao.isSeatSold(planId, seat);
    }

    @Override
    public void add(Order order) throws MyException {
        if (orderDao.add(order) == null) {
            throw new MyException("购买失败，请重新购买。");
        }
    }

    @Override
    public List<Order> queryAll() {
        return orderDao.queryAll();
    }

    @Override
    public Order query(String id) {
        return orderDao.query(new String[]{OrderDao.COLUME_ID}, id);
    }

    @Override
    public List<Order> queryUserOrders(String userId) {
        return orderDao.getUserOrders(userId);
    }

    @Override
    public void delete(String id) {
        orderDao.delete(new String[]{OrderDao.COLUME_ID}, id);
    }

    @Override
    public void update(String id, double amount) {
        orderDao.update(id, amount);
    }

    @Override
    public Map<String, Double> queryRealtimeSales() {
        return orderDao.queryRealtimeSales();
    }

    @Override
    public Map<String, Double> queryTotalSales() {
        return orderDao.queryTotalSales();
    }
}
