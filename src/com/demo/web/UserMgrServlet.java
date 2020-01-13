package com.demo.web;

import com.demo.entity.User;
import com.demo.factory.BaseFactory;
import com.demo.service.UserService;
import com.demo.util.Utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/20 21:45
 * 功能: 用户管理
 */
@WebServlet(urlPatterns = {"/admin", "/admin/users/delete"})
public class UserMgrServlet extends BaseServlet {
    private static final long serialVersionUID = -2620630721364272563L;
    private UserService service = BaseFactory.getService(UserService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if (servletPath.contains("delete")) {//执行删除
            doDel(req, resp);
        } else {
            String keyword = req.getParameter("keyword");
            if (Utils.isEmpty(keyword)) {
                backUserList(service.queryAll(), req, resp);
            } else {
                req.setAttribute("keyword", keyword);
                backUserList(service.queryUsersByKeyword(keyword), req, resp);
            }
        }
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if (name != null) {
            service.deleteByUsername(name);                       
        }
       resp.sendRedirect(req.getContextPath() + "/admin");
    }

    private void backUserList(List<User> list, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (list != null) {
            req.setAttribute("users", list);
        }
        req.setAttribute("chkItem", 1);
        req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
    }
}
