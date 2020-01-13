package com.demo.service;

import com.demo.exception.MyException;
import com.demo.entity.Plan;

import java.util.List;

/**
 * 时间: 2017/11/23 21:48
 * 功能:
 */

public interface PlanService extends Service {
    /**
     * 获取所有已排的影片
     */
    List<Plan> queryAll();

    /**
     * 删除排片计划
     */
    void delete(String id);

    /**
     * 排片
     */
    void add(Plan plan) throws MyException;

    /**
     * 通过片名关键字查询
     * @param keyword 关键字
     */
    List<Plan> queryPlansByKeyword(String keyword);

    /**
     * 获取去重复电影名的排片
     */
    List<Plan> queryAllGroupByMovieName();

    /**
     * 查询所有未放映的排片，根据电影名分组
     */
    List<Plan> queryAllNotPlayGroupByMovieName();

    /**
     * 根据片名查询
     * @param name 片名
     */
    List<Plan> queryPlansByMovieName(String name);

    /**
     * 根据排片id查询
     * @param id 排片id
     */
    Plan query(String id);

    /**
     * 查询排片
     * @param movieName 片名
     * @param hallName 影厅名
     * @param playTime 放映时间
     */
    Plan query(String movieName, String hallName, String playTime);

    /**
     * 取消排片
     * @param movieName 片名
     * @param hallName 影厅名
     * @param playTime 放映时间
     */
    void delete(String movieName, String hallName, String playTime);

    /**
     * 只查询未放映的排片
     * @param name 片名
     */
    List<Plan> queryNotPlayPlansByMovieName(String name);
}
