package com.demo.listener;

import com.demo.dao.DaoInitializer;
import com.demo.factory.BaseFactory;
import com.demo.mgr.DataSourceMgr;
import com.demo.service.AdminService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 时间: 2017/11/19 10:25
 * 功能: 做一些初始化的操作
 */
@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DaoInitializer.init();
        AdminService service = BaseFactory.getService(AdminService.class);
        service.addSysAdmin("admin", "123456");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DataSourceMgr.clearUp();
    }
}
