package com.demo.dao;

import com.demo.entity.Plan;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 时间: 2017/11/24 11:24
 * 功能: 排片表
 */

public class PlanDaoImpl extends BaseDao implements PlanDao {
    @Override
    public String getTableName() {
        return TABLE_NAEM;
    }

    @Override
    public String getCreateTableSql() {
        return CREATE_TABLE_SQL;
    }

    @Override
    public List<Plan> queryAll() {
        return queryList(Plan.class, null, null);
    }

    @Override
    public int delete(String[] columnNames, Object... values) {
        return update("delete from " + TABLE_NAEM + generateConditionString(columnNames), values);
    }

    @Override
    public Plan query(String[] columnNames, Object... values) {
        return query(Plan.class, columnNames, values);
    }

    @Override
    public void add(Plan plan) {
        String sql = "insert into " + TABLE_NAEM + " values(null,?,?,?,?)";
        insert(Plan.class, sql, new Object[]{plan.movie_name, plan.hall_name, plan.price, plan.play_time});
    }

    @Override
    public List<Plan> queryPlansByKeyword(String keyword) {
        return queryLike(Plan.class, COLUME_MOVIE_NAME, keyword);
    }

    @Override
    public List<Plan> queryAllGroupByMovieName() {
        String sql = String.format("select * from %s group by %s", TABLE_NAEM, COLUME_MOVIE_NAME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Plan.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Plan> queryPlansByMovieName(String movieName) {
        return queryList(Plan.class, new String[]{COLUME_MOVIE_NAME}, new Object[]{movieName});
    }

    @Override
    public List<Plan> queryNotPlayPlansByMovieName(String name) {
        String sql = String.format("select * from %s where %s=? and %s>? order by %s asc", TABLE_NAEM, COLUME_MOVIE_NAME, COLUME_PLAY_TIME, COLUME_PLAY_TIME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Plan.class), name, System.currentTimeMillis());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Plan> queryAllNotPlayGroupByMovieName() {
        String sql = String.format("select * from %s where %s>? group by %s", TABLE_NAEM, COLUME_PLAY_TIME, COLUME_MOVIE_NAME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Plan.class), System.currentTimeMillis());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
