package com.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 时间: 2017/11/20 10:16
 * 功能: 管理员登录过滤器
 */
@WebFilter(urlPatterns = "/admin/*")
public class AdminLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if ((session == null || session.getAttribute("admin") == null) && !"/admin/login".equals(req.getServletPath())) {
            //使用如果没有登录，跳转到管理员登录界面
            ((HttpServletResponse) resp).sendRedirect(req.getContextPath() + "/admin/login");
        } else {
            filterChain.doFilter(req, resp);
        }        
    }

    @Override
    public void destroy() {

    }
}
