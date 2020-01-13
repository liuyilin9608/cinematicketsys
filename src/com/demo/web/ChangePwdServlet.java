package com.demo.web;

import com.demo.exception.MyException;
import com.demo.entity.User;
import com.demo.factory.BaseFactory;
import com.demo.service.UserService;
import com.demo.util.Utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/22 09:27
 * 功能: 用户修改密码
 */
@WebServlet(urlPatterns = "/my/chgpwd")
public class ChangePwdServlet extends BaseServlet {
    private static final long serialVersionUID = -3970741365071473287L;
    private UserService service = BaseFactory.getService(UserService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().isEmpty()) {
            forwardToChgPwdJsp(req, resp);
        } else {
            User user = (User) req.getSession().getAttribute("user");
            String password = req.getParameter("password");
            String newpwd = req.getParameter("newpwd");
            try {
                service.changePassword(user.username, password, newpwd);
                req.setAttribute("success", true);
            } catch (MyException e) {
                req.setAttribute("msg", e.getMessage());
                Utils.fillAttr(req, "password", password);
                Utils.fillAttr(req, "newpwd", newpwd);
            }
            forwardToChgPwdJsp(req, resp);
        }
    }

    private void forwardToChgPwdJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("chkItem", 4);
        req.getRequestDispatcher("/chgpwd.jsp").forward(req, resp);
    }
}
