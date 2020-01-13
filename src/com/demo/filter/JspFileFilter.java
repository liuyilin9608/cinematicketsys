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

/**
 * 时间: 2017/11/21 11:27
 * 功能: 拦截直接访问jsp文件
 */
@WebFilter(urlPatterns = "*.jsp")
public class JspFileFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if ("/index.jsp".equals(request.getServletPath())) {
            filterChain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/");
        }        
    }

    @Override
    public void destroy() {

    }
}
