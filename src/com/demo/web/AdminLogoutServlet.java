package com.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 时间: 2017/11/20 21:33
 * 功能: 管理员退出
 */
@WebServlet(urlPatterns = "/admin/logout")
public class AdminLogoutServlet extends BaseServlet {
    private static final long serialVersionUID = -1678866267451982875L;

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("admin");
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
