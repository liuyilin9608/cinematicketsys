package com.demo.dao;

import com.demo.entity.Plan;

import java.util.List;

/**
 * 时间: 2017/11/23 21:38
 * 功能: 
 */

public interface PlanDao extends Dao {
    String TABLE_NAEM = DB_NAME + ".plan";
    String CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAEM + " (id int primary key auto_increment," +
            "movie_name text,hall_name text,price double,play_time bigint)";
    String COLUME_ID = "id";
    String COLUME_MOVIE_NAME = "movie_name";
    String COLUME_HALL_NAME = "hall_name";
    String COLUME_PRICE = "price";
    String COLUME_PLAY_TIME = "play_time";

    List<Plan> queryAll();

    int delete(String[] columnNames, Object... values);
    
    Plan query(String[] columnNames, Object... values);
    
    void add(Plan plan);

    List<Plan> queryPlansByKeyword(String keyword);

    List<Plan> queryAllGroupByMovieName();

    List<Plan> queryPlansByMovieName(String movieName);

    List<Plan> queryNotPlayPlansByMovieName(String name);

    /**
     * 查询所有未放映的排片，根据电影名分组
     */
    List<Plan> queryAllNotPlayGroupByMovieName();
}
