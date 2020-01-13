package com.demo.dao;

import com.demo.entity.Hall;

import java.util.List;

/**
 * 时间: 2017/11/24 11:19
 * 功能: 影厅数据表操作
 */

public class HallDaoImpl extends BaseDao implements HallDao {
    @Override
    public String getTableName() {
        return TABLE_NAEM;
    }

    @Override
    public String getCreateTableSql() {
        return CREATE_TABLE_SQL;
    }

    @Override
    public List<Hall> queryAll() {
        return queryList(Hall.class, null, null);
    }

    @Override
    public int delete(String[] columnNames, Object... values) {
        return update("delete from " + TABLE_NAEM + generateConditionString(columnNames), values);
    }

    @Override
    public Hall query(String[] columnNames, Object... values) {
        return query(Hall.class, columnNames, values);
    }

    @Override
    public void add(Hall hall) {
        String sql = "insert into " + TABLE_NAEM + " values(null,?,?,?,?)";
        insert(Hall.class, sql, new Object[]{hall.name, hall.rows, hall.columns, hall.type});
    }
}
