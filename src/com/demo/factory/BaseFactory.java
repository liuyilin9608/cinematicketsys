package com.demo.factory;

import com.demo.annotation.Transaction;
import com.demo.dao.Dao;
import com.demo.mgr.DataSourceMgr;
import com.demo.service.Service;
import com.demo.util.PropertyUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BaseFactory {
	
	/**
	 * 根据类不同返回的实例不同
	 * @param clazz 类的字节码
	 * @return 类对应的实例对象
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Service> T getService(Class<T> clazz) {
		try {
			String simpleName = clazz.getSimpleName();
			String className = PropertyUtils.getProperty(simpleName);
			final T service = (T) Class.forName(className).newInstance();
			return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					if (method.isAnnotationPresent(Transaction.class)) {
						try {
							DataSourceMgr.startTransaction();
							Object obj = method.invoke(service, args);
                            DataSourceMgr.commitTransaction();	
							return obj;
						} catch (InvocationTargetException e) {
                            DataSourceMgr.rollbackTransaction();
							throw new RuntimeException(e.getTargetException());
						} catch (Exception e) {
                            DataSourceMgr.rollbackTransaction();
							throw new RuntimeException(e);
						} finally {
                            DataSourceMgr.release();
						}						
					} else {
						try {
							return method.invoke(service, args);
						} catch (InvocationTargetException e) {
                            DataSourceMgr.rollbackTransaction();
							throw e.getTargetException();
						}						
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Dao> T getDao(Class<T> clazz) {
		try {
			String simpleName = clazz.getSimpleName();
			String className = PropertyUtils.getProperty(simpleName);
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}
	}
}
