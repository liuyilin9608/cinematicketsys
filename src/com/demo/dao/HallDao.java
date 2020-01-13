package com.demo.dao;

import com.demo.entity.Hall;

import java.util.List;

/**
 * 时间: 2017/11/23 21:38
 * 功能: 
 */

public interface HallDao extends Dao {
    String TABLE_NAEM = DB_NAME + ".hall";
    String CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAEM + " (id int primary key auto_increment,name text,rows int,columns int,type text)";
    String COLUME_ID = "id";
    String COLUME_NAME = "name";
    String COLUME_ROWS = "rows";
    String COLUME_COLUMNS = "columns";
    String COLUME_TYPE = "type";

    /**
     * 查询所有影厅
     */
    List<Hall> queryAll();

    int delete(String[] columnNames, Object... values);

    /**
     * 查询影厅
     * @param columnNames 列名
     * @param values 占位符对应的值
     */
    Hall query(String[] columnNames, Object... values);

    /**
     * 添加影厅
     */
    void add(Hall hall);
}
