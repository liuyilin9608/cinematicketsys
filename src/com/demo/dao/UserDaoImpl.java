package com.demo.dao;

import com.demo.entity.User;

import java.util.List;

/**
 * 时间: 2017/11/19 10:39
 * 功能:
 */

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User query(String[] columnNames, Object... values) {
        return query(User.class, columnNames, values);
    }

    @Override
    public User add(User user) {
        String sql = "insert into " + TABLE_NAEM + " values(null,?,password(?),?,?,?)";
        return insert(User.class, sql, new Object[]{user.username, user.password, user.nickname, user.phone, System.currentTimeMillis()});
    }

    @Override
    public List<User> queryAll() {
        return queryList(User.class, null, null);
    }

    @Override
    public boolean delete(String[] columnNames, Object... values) {
        return update("delete from " + TABLE_NAEM + generateConditionString(columnNames), values) > 0;
    }

    @Override
    public User valicateUser(String username, String password) {
        String sql = String.format("select * from %s where %s=? and %s=password(?)", TABLE_NAEM, COLUME_USERNAME, COLUME_PASSWORD);
        return query(User.class, sql, new Object[]{username, password});
    }

    @Override
    public int changePassword(String username, String password, String newpwd) {   
        String sql = String.format("update %s set password=password(?) where %s=? and %s=password(?)", TABLE_NAEM, COLUME_USERNAME, COLUME_PASSWORD);
        return update(sql, new Object[]{newpwd, username, password});
    }

    @Override
    public int updateInfo(User user) {
        String sql = "update %s set %s=?,%s=? where %s=?";
        sql = String.format(sql, TABLE_NAEM, UserDao.COLUME_NICKNAME, COLUME_PHONE, COLUME_USERNAME);
        return update(sql, new Object[]{user.nickname, user.phone, user.username});
    }

    @Override
    public List<User> queryUsersByKeyword(String keyword) {
        return queryLike(User.class, COLUME_USERNAME, keyword);
    }

    @Override
    public String getTableName() {
        return TABLE_NAEM;
    }

    @Override
    public String getCreateTableSql() {
        return CREATE_TABLE_SQL;
    }
}
