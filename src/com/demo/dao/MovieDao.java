package com.demo.dao;

import com.demo.entity.Movie;

import java.util.List;

/**
 * 时间: 2017/11/23 21:38
 * 功能: 
 */

public interface MovieDao extends Dao {
    String TABLE_NAEM = DB_NAME + ".movie";
    String CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAEM + " (id int primary key auto_increment,name text,pic_name text," +
            "director text,protagonist text,region text,language text,type text,duration int,synopsis text)";
    String COLUME_ID = "id";
    String COLUME_NAME = "name";
    String COLUME_PIC_NAME = "pic_name";
    String COLUME_DIRECTOR = "director";
    String COLUME_PROTAGONIST = "protagonist";
    String COLUME_REGION = "region";
    String COLUME_LANGUAGE = "language";
    String COLUME_TYPE = "type";
    String COLUME_DURATION = "duration";
    String COLUME_SYNOPSIS = "synopsis";

    /**
     * 查询影片
     * @param columnNames 列名
     * @param values 占位符对应的值
     */
    Movie query(String[] columnNames, Object... values);

    /**
     * 添加影片
     */
    void add(Movie movie);

    /**
     * 更新影片信息
     */
    int update(Movie movie);

    /**
     * 删除影片
     * @param id 影片id
     */
    int delete(String id);

    /**
     * 获取所有
     */
    List<Movie> queryAll();

    /**
     * 通过关键字搜索
     */
    List<Movie> queryMoviesByKeyword(String keyword);
}
