package com.demo.dao;

/**
 * 时间: 2017/11/18 11:10
 * 功能: 数据库操作接口
 */

public interface Dao {
    String DB_NAME = "ticketing";
    
    /**
     * 获取表名
     */
    String getTableName();

    /**
     * 获取创建表的语句
     */
    String getCreateTableSql();
}
