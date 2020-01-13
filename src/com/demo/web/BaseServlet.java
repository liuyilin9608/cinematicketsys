package com.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/19 19:07
 * 功能: 基类，简化代码
 */
public abstract class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 3605531135277495161L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    protected abstract void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
