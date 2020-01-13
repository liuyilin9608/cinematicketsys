package com.demo.service;

import com.demo.exception.MyException;
import com.demo.dao.HallDao;
import com.demo.dao.OrderDao;
import com.demo.dao.PlanDao;
import com.demo.entity.Hall;
import com.demo.entity.Plan;
import com.demo.factory.BaseFactory;
import com.demo.util.Utils;

import java.util.Collections;
import java.util.List;

/**
 * 时间: 2017/11/24 11:25
 * 功能:
 */

public class HallServiceImpl implements HallService {
    private HallDao hallDao = BaseFactory.getDao(HallDao.class);
    private PlanDao planDao = BaseFactory.getDao(PlanDao.class);
    private OrderDao orderDao = BaseFactory.getDao(OrderDao.class);
    
    @Override
    public List<Hall> queryAll() {
        List<Hall> list = hallDao.queryAll();
        if (list != null) {
            Collections.sort(list);
        }
        return list;
    }

    @Override
    public void delete(String id) {
        Hall hall = hallDao.query(new String[]{HallDao.COLUME_ID}, id);
        if (hall != null) {
            int rows = hallDao.delete(new String[]{HallDao.COLUME_ID}, id);
            if (rows > 0) {
                //查询排片
                Plan plan = planDao.query(new String[]{PlanDao.COLUME_HALL_NAME}, hall.name);
                if (plan != null) {
                    //删除排片
                    int r = planDao.delete(new String[]{PlanDao.COLUME_HALL_NAME}, hall.name);
                    if (r > 0) {
                        //删除订单
                        orderDao.delete(new String[]{OrderDao.COLUME_PLAN_ID}, plan.id);
                    }
                }
            }
        }
    }

    @Override
    public void add(Hall hall) throws MyException {
        if (Utils.isEmpty(hall.name)) {
            throw new MyException("影厅名不能为空");
        } else if (hall.rows == 0) {
            throw new MyException("总排数只能是大于0的整数");
        } else if (hall.columns == 0) {
            throw new MyException("总列数只能是大于0的整数");
        } else if (hallDao.query(new String[]{HallDao.COLUME_NAME}, hall.name) != null) {
            throw new MyException("影厅已存在");
        } else {
            hallDao.add(hall);
        }
    }

    @Override
    public Hall queryByName(String name) {
        return hallDao.query(new String[]{HallDao.COLUME_NAME}, name);
    }

    @Override
    public void deleteByName(String name) {
        hallDao.delete(new String[]{HallDao.COLUME_NAME}, name);
    }
}
