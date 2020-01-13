package com.demo.mgr;

import com.demo.dao.Dao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import org.apache.commons.dbutils.DbUtils;

import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DataSourceMgr {
	private static ComboPooledDataSource source = new ComboPooledDataSource();
	private static ThreadLocal<Connection> realConnLocal = new ThreadLocal<>();
	private static ThreadLocal<Connection> proxyConnLocal = new ThreadLocal<>();
	
	private static ThreadLocal<Boolean> flagLocal = ThreadLocal.withInitial(() -> false);
	
	static {
        //初始化
        try {
            source.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        source.setJdbcUrl("jdbc:mysql://101.132.103.111:3306?characterEncoding=utf8&useSSL=true");
        source.setUser("root");
        source.setPassword("111111");
        source.setCheckoutTimeout(10000);
        source.setIdleConnectionTestPeriod(30);
        source.setInitialPoolSize(10);
        source.setMinPoolSize(10);
        source.setMaxIdleTime(30);
        source.setMaxPoolSize(80);
        source.setMaxStatements(100);
        //创建数据库
        Connection conn = null;
        Statement stat = null;
        try {
            conn = source.getConnection();
            stat = conn.createStatement();
            stat.execute("create database if not exists " + Dao.DB_NAME + " character set utf8");
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
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
	
	public static void startTransaction() throws SQLException {
		flagLocal.set(true);
		final Connection conn = source.getConnection();
		conn.setAutoCommit(false);
		realConnLocal.set(conn);
		//创建Connection代理类
		Connection proxyConn = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				if ("close".equals(method.getName())) {
					return null;
				} else {
					return method.invoke(conn, args);
				}
			}
		});
		proxyConnLocal.set(proxyConn);
	}
	
	public static void commitTransaction() {
		DbUtils.commitAndCloseQuietly(proxyConnLocal.get());
	}
	
	public static void rollbackTransaction() {
		DbUtils.rollbackAndCloseQuietly(proxyConnLocal.get());
	}
	
	public static void release() {
		DbUtils.closeQuietly(realConnLocal.get());
		realConnLocal.remove();
		proxyConnLocal.remove();
		flagLocal.remove();
	}
	
	public static DataSource getSource() {
		//如果事务开启，返回的是带着不能关闭的connection的代理数据源，否则返回真实数据源
		if (flagLocal.get()) {
			return (DataSource) Proxy.newProxyInstance(source.getClass().getClassLoader(), new Class[]{DataSource.class}, (proxy, method, args) -> {
                if ("getConnection".equals(method.getName())) {
                    return proxyConnLocal.get();
                } else {
                    return method.invoke(source, args);
                }
            });
		} else {
			return source;
		}
	}

    /**
     * 释放连接池资源
     */
	public static void clearUp() {
        try {
            DataSources.destroy(source);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }	
}
