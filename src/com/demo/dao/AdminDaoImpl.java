package com.demo.dao;

import com.demo.entity.Admin;

/**
 * 时间: 2017/11/19 18:30
 * 功能:
 */

public class AdminDaoImpl extends BaseDao implements AdminDao {
    @Override
    public String getCreateTableSql() {
        return CREATE_TABLE_SQL;
    }

    @Override
    public Admin query(String[] columnNames, String... values) {
        return query(Admin.class, columnNames, values);
    }

    @Override
    public Admin add(String username, String password) {
        String sql = "insert into " + TABLE_NAEM + " values(null,?,password(?))";
        return insert(Admin.class, sql, new Object[]{username, password});
    }

    @Override
    public Admin add(Admin user) {
        return null;
    }

    @Override
    public Admin valicateAdmin(String username, String password) {
        String sql = String.format("select * from %s where %s=? and %s=password(?)",
                TABLE_NAEM, COLUME_USERNAME, COLUME_PASSWORD);    
        return query(Admin.class, sql, new Object[]{username, password});
    }

    @Override
    public String getTableName() {
        return TABLE_NAEM;
    }
}
