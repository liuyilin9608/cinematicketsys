package com.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/21 14:20
 * 功能: 个人中心
 */
@WebServlet(urlPatterns = "/my")
public class UserInfoServlet extends BaseServlet {
    private static final long serialVersionUID = 2348293882129636411L;

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {   
        req.setAttribute("chkItem", 1);
        req.getRequestDispatcher("/my_info.jsp").forward(req, resp);
    }
}
