package com.demo.service;

import com.demo.exception.MyException;
import com.demo.dao.MovieDao;
import com.demo.dao.OrderDao;
import com.demo.dao.PlanDao;
import com.demo.entity.Movie;
import com.demo.entity.Plan;
import com.demo.factory.BaseFactory;
import com.demo.util.Utils;

import java.util.Collections;
import java.util.List;

/**
 * 时间: 2017/11/24 11:26
 * 功能:
 */

public class PlanServiceImpl implements PlanService {
    private PlanDao planDao = BaseFactory.getDao(PlanDao.class);
    private OrderDao orderDao = BaseFactory.getDao(OrderDao.class);
    private MovieDao movieDao = BaseFactory.getDao(MovieDao.class);
    
    private Plan markPlayedFlag(Plan plan) {
        if (plan != null) {
            plan.played = plan.play_time <= System.currentTimeMillis();
        }
        return plan;
    }
    
    private List<Plan> markPlayedFlag(List<Plan> list) {
        if (list != null) {
            for (Plan plan : list) {
                plan.played = plan.play_time <= System.currentTimeMillis();
            }
        }
        return list;
    }
    
    @Override
    public List<Plan> queryAll() {
        List<Plan> list = planDao.queryAll();
        if (list != null) {
            Collections.sort(list);
            markPlayedFlag(list);
        }
        return list;
    }

    @Override
    public void delete(String id) {
        //删除排片
        int r = planDao.delete(new String[]{PlanDao.COLUME_ID}, id);
        if (r > 0) {
            //删除订单
            orderDao.delete(new String[]{OrderDao.COLUME_PLAN_ID}, id);
        }
    }

    @Override
    public void add(Plan plan) throws MyException {
        if (Utils.isEmpty(plan.movie_name)) {
            throw new MyException("片名不能为空");
        } else if (Utils.isEmpty(plan.hall_name)) {
            throw new MyException("请选择影厅");
        } else if (plan.price == 0) {
            throw new MyException("价格必须是大于0的数字");
        } else if (plan.play_time == 0) {
            throw new MyException("放映时间格式不正确");
        } else if (plan.play_time < System.currentTimeMillis() + 3600000) {
            throw new MyException("放映时间必须比当前时间晚1小时");
        } else {
            //遍历所有排片，看是否有冲突
            List<Plan> list = planDao.queryAll();
            if (list == null) {
                planDao.add(plan);
            } else {
                //获取当前影片片长
                Movie movie = movieDao.query(new String[]{MovieDao.COLUME_NAME}, plan.movie_name);
                long endTime = plan.play_time + movie.duration * 60000;//片长是以分钟单位保存的，转成毫秒
                for (Plan p : list) {
                    //如果使用的是同一个影厅，开始判断时间是否有交叉
                    //同影厅放映电影时间间隔为半小时
                    if (p.hall_name.equals(plan.hall_name)) {
                        //获取此排片的电影
                        Movie m = movieDao.query(new String[]{MovieDao.COLUME_NAME}, p.movie_name);
                        long end = p.play_time + m.duration * 60000;
                        //如果开始时间或者结束时间在其他影片的开始前半小时到结束后半小时这段时间范围内，则说明时间交叉
                        if ((plan.play_time >= p.play_time - 1800000 && plan.play_time <= end + 1800000) || 
                                (endTime >= p.play_time - 1800000 && endTime <= end + 1800000)) {
                            throw new MyException("该影厅同时段已排影片");
                        }
                    }
                }
                //如果没有冲突，添加到数据库
                planDao.add(plan);
            }
        }
    }

    @Override
    public List<Plan> queryPlansByKeyword(String keyword) {
        List<Plan> list = planDao.queryPlansByKeyword(keyword);
        if (list != null) {
            Collections.sort(list);
        }
        return markPlayedFlag(list);
    }

    public List<Plan> queryAllGroupByMovieName() {
        List<Plan> list = planDao.queryAllGroupByMovieName();
        if (list != null) {
            Collections.sort(list);
        }
        return markPlayedFlag(list);
    }

    @Override
    public List<Plan> queryAllNotPlayGroupByMovieName() {
        return planDao.queryAllNotPlayGroupByMovieName();
    }

    @Override
    public List<Plan> queryPlansByMovieName(String name) {
        return markPlayedFlag(planDao.queryPlansByMovieName(name));
    }

    @Override
    public Plan query(String id) {
        return markPlayedFlag(planDao.query(new String[]{PlanDao.COLUME_ID}, id));
    }

    @Override
    public Plan query(String movieName, String hallName, String playTime) {
        return markPlayedFlag(planDao.query(new String[]{PlanDao.COLUME_MOVIE_NAME, PlanDao.COLUME_HALL_NAME, 
                PlanDao.COLUME_PLAY_TIME}, movieName, hallName, playTime));
    }

    @Override
    public void delete(String movieName, String hallName, String playTime) {
        planDao.delete(new String[]{PlanDao.COLUME_MOVIE_NAME, PlanDao.COLUME_HALL_NAME, PlanDao.COLUME_PLAY_TIME}, movieName, hallName, playTime);
    }

    @Override
    public List<Plan> queryNotPlayPlansByMovieName(String name) {
        List<Plan> plans = planDao.queryNotPlayPlansByMovieName(name);
        for (Plan plan : plans) {
            plan.played = true;
        }
        return plans;
    }
}
