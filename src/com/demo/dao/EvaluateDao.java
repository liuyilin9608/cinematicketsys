package com.demo.dao;

import com.demo.entity.Evaluate;

import java.util.List;

/**
 * 时间: 2017/11/23 21:38
 * 功能: 
 */

public interface EvaluateDao extends Dao {
    String TABLE_NAEM = DB_NAME + ".evaluate";
    String CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAEM + " (id int primary key auto_increment,time bigint," +
            "username varchar(20),nickname text,movie_name text,content text)";
    String COLUME_ID = "id";
    String COLUME_TIME = "time";
    String COLUME_USERNAME = "username";
    String COLUME_NICKNAME = "nickname";
    String COLUME_MOVIE_NAME = "movie_name";
    String COLUME_CONTENT = "content";

    int delete(String[] columnNames, Object... values);
    
    List<Evaluate> queryEvaluatesByMovieName(String movieName);
    
    void add(Evaluate evaluate);
    
    List<Evaluate> queryAll();

    List<Evaluate> queryMoviesByKeyword(String keyword);

    /**
     * 查询
     * @param columnNames 列名
     * @param values 占位符对应的值
     */
    Evaluate query(String[] columnNames, Object... values);
}
