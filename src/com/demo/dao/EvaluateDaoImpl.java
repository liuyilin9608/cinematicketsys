package com.demo.dao;

import com.demo.entity.Evaluate;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 时间: 2017/11/24 11:22
 * 功能: 评价表操作
 */

public class EvaluateDaoImpl extends BaseDao implements EvaluateDao {
    @Override
    public String getTableName() {
        return TABLE_NAEM;
    }

    @Override
    public String getCreateTableSql() {
        return CREATE_TABLE_SQL;
    }

    @Override
    public int delete(String[] columnNames, Object... values) {
        return update("delete from " + TABLE_NAEM + generateConditionString(columnNames), values);
    }

    @Override
    public List<Evaluate> queryEvaluatesByMovieName(String movieName) {
        String sql = String.format("select * from %s where %s=? order by %s desc", TABLE_NAEM, COLUME_MOVIE_NAME, COLUME_TIME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Evaluate.class), movieName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void add(Evaluate evaluate) {
        String sql = "insert into " + TABLE_NAEM + " values(null,?,?,?,?,?)";
        insert(Evaluate.class, sql, new Object[]{evaluate.time, evaluate.username, evaluate.nickname, evaluate.movie_name, evaluate.content});
    }

    @Override
    public List<Evaluate> queryAll() {
        String sql = String.format("select * from %s order by %s desc", TABLE_NAEM, COLUME_TIME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Evaluate.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Evaluate> queryMoviesByKeyword(String keyword) {
        String sql = "select * from %s where %s like ? or %s like ? order by %s desc";
        sql = String.format(sql, TABLE_NAEM, COLUME_USERNAME, COLUME_MOVIE_NAME, COLUME_TIME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Evaluate.class), "%" + keyword + "%", "%" + keyword + "%");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Evaluate query(String[] columnNames, Object... values) {
        return query(Evaluate.class, columnNames, values);
    }
}
