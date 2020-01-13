package com.demo.web;

import com.demo.exception.MyException;
import com.demo.entity.Admin;
import com.demo.factory.BaseFactory;
import com.demo.service.AdminService;
import com.demo.util.Utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/19 19:30
 * 功能: 管理员登录
 */
@WebServlet(urlPatterns = "/admin/login")
public class AdminLoginServlet extends BaseServlet {
    private static final long serialVersionUID = 6233552650658433301L;
    private AdminService service = BaseFactory.getService(AdminService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().isEmpty()) {
            req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
        } else {            
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            try {
                Admin admin = service.valicateAdmin(username, password);
                req.getSession().setAttribute("admin", admin);
                resp.sendRedirect(req.getContextPath() + "/admin");
            } catch (MyException e) {
                req.setAttribute("msg", e.getMessage());
                Utils.fillAttr(req, "username", username);
                req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
            }            
        }
    }
}
