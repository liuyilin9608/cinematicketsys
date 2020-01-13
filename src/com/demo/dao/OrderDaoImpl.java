package com.demo.dao;

import com.demo.entity.Order;
import com.demo.entity.User;
import com.demo.mgr.DataSourceMgr;
import com.demo.util.DateUtils;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 时间: 2017/11/24 11:08
 * 功能: 
 */

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int delete(String[] columnNames, Object... values) {
        return update("delete from " + TABLE_NAEM + generateConditionString(columnNames), values);
    }

    @Override
    public Order query(String[] columnNames, Object... values) {
        return query(Order.class, columnNames, values);
    }

    @Override
    public User add(Order order) {
        String sql = "insert into " + TABLE_NAEM + " values(null,?,?,?,?,?,?)";
        return insert(User.class, sql, new Object[]{order.user_id, order.plan_id, order.movie_name, order.seat, order.amount, order.create_time});
    }

    @Override
    public boolean isSeatSold(String planId, String seat) {
        String sql = String.format("select * from %s where %s=? and %s like ?", TABLE_NAEM, COLUME_PLAN_ID, COLUME_SEAT);
        try {
            List<Order> list = getQueryRunner().query(sql, new BeanListHandler<>(Order.class), planId, "%|" + seat + "|%");
            return list != null && !list.isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Order> queryAll() {
        String sql = String.format("select * from %s order by %s desc", TABLE_NAEM, COLUME_CREATE_TIME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Order> getUserOrders(String userId) {
        String sql = String.format("select * from %s where %s=? order by %s desc", TABLE_NAEM, COLUME_USER_ID, COLUME_CREATE_TIME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Order.class), userId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public int update(String id, double amount) {
        return update(String.format("update %s set %s=? where %s=?", TABLE_NAEM, COLUME_AMOUNT, COLUME_ID), new Object[]{amount, id});
    }

    @Override
    public Map<String, Double> queryRealtimeSales() {
        return querySales(true);
    }

    private Map<String, Double> querySales(boolean isRealtime) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            Map<String, Double> map = new LinkedHashMap<>();
            conn = DataSourceMgr.getSource().getConnection();
            String sql;
            if (isRealtime) {
                sql = "select %s,sum(%s) from %s where %s>=? group by %s order by sum(%s) desc";
                ps = conn.prepareStatement(String.format(sql, COLUME_MOVIE_NAME, COLUME_AMOUNT, TABLE_NAEM,
                        COLUME_CREATE_TIME, COLUME_MOVIE_NAME, COLUME_AMOUNT));
            } else {
                sql = "select %s,sum(%s) from %s group by %s order by sum(%s) desc";
                ps = conn.prepareStatement(String.format(sql, COLUME_MOVIE_NAME, COLUME_AMOUNT, TABLE_NAEM,
                        COLUME_MOVIE_NAME, COLUME_AMOUNT));
            }            
            if (ps != null) {
                if (isRealtime) {
                    ps.setObject(1, DateUtils.getStartOfDay(System.currentTimeMillis()));
                }
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        map.put(rs.getString(1), rs.getDouble(2));
                    }
                }
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Map<String, Double> queryTotalSales() {
        return querySales(false);
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
