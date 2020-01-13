package com.demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 时间: 2017/11/21 17:00
 * 功能: 用户退出
 */
@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends BaseServlet {
    private static final long serialVersionUID = 5603737643235976664L;

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
        String from = req.getParameter("from");
        if (from != null) {
            resp.sendRedirect(req.getContextPath() + from);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
