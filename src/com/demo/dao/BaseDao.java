package com.demo.dao;

import com.demo.mgr.DataSourceMgr;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 时间: 2017/11/19 18:33
 * 功能:
 */

abstract class BaseDao implements Dao {
    
    <T> List<T> queryLike(Class<T> clazz, String columnName, Object value) {
        try {
            return getQueryRunner().query("select * from " + getTableName() + " where " + columnName + " like ?",
                    new BeanListHandler<>(clazz), "%" + value + "%");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    <T> T query(Class<T> clazz, String[] columnNames, Object[] values) {
        try {
            if (values == null || values.length == 0) {
                return getQueryRunner().query(generateQuerySql(columnNames), new BeanHandler<>(clazz));
            } else {
                return getQueryRunner().query(generateQuerySql(columnNames), new BeanHandler<>(clazz), values);
            }            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private String generateQuerySql(String[] columnNames) {
        return "select * from " + getTableName() + generateConditionString(columnNames);
    }

    String generateConditionString(String[] columnNames) {
        StringBuilder sb = new StringBuilder();
        if (columnNames != null) {
            for (int i = 0, columnNamesLength = columnNames.length; i < columnNamesLength; i++) {
                String columnName = columnNames[i];
                if (i == 0)
                    sb.append(" where");
                else
                    sb.append(" and");
                sb.append(" ").append(columnName).append("=?");
            }
        }
        return sb.toString();
    }
    
    <T> List<T> queryList(Class<T> clazz, String[] columnNames, Object[] values) {
        try {
            if (values == null || values.length == 0) {
                return getQueryRunner().query(generateQuerySql(columnNames), new BeanListHandler<>(clazz));
            } else {
                return getQueryRunner().query(generateQuerySql(columnNames), new BeanListHandler<>(clazz), values);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    QueryRunner getQueryRunner() {
        return new QueryRunner(DataSourceMgr.getSource());
    }
    
    <T> T query(Class<T> clazz, String sql, Object[] values) {
        try {
            return getQueryRunner().query(sql, new BeanHandler<>(clazz), values);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    <T> T insert(Class<T> clazz, String sql, Object[] values) {
        try {
            return getQueryRunner().insert(sql, new BeanHandler<>(clazz), values);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 更新或删除
     * @param sql 语句
     * @param values 占位符对应的值
     * @return 返回受影响的行数
     */
    int update(String sql, Object[] values) {
        try {
            return getQueryRunner().update(sql, values);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }    
}
