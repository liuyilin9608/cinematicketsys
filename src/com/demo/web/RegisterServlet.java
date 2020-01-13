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
import javax.servlet.http.HttpSession;

/**
 * 时间: 2017/11/21 14:07
 * 功能: 用户注册
 */
@WebServlet(urlPatterns = "/reg")
public class RegisterServlet extends BaseServlet {
    private static final long serialVersionUID = -6923825645904193768L;
    private UserService service = BaseFactory.getService(UserService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().isEmpty()) {
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } else {
            User user = new User();
            String confirmPwd = req.getParameter("confirmPwd");
            try {
                Utils.populateToBean(user, req.getParameterMap());
                //检查验证码是否正确
                String valicateCode = req.getParameter("valicateCode");
                HttpSession session = req.getSession();
                String sessionValicateCode = (String) session.getAttribute("valicateCode");
                if (valicateCode == null || !valicateCode.equalsIgnoreCase(sessionValicateCode)) {
                    backError(req, resp, user, confirmPwd, "验证码不正确");
                    return;
                }
                //检查信息并存到数据库
                user = service.registerUser(user, confirmPwd);
                //将用户添加到session
                session.setAttribute("user", user);
                //重定向回主页
                resp.sendRedirect(req.getContextPath() + "/");
            } catch (MyException e) {
                backError(req, resp, user, confirmPwd, e.getMessage());
            }
        }
    }

    private void backError(HttpServletRequest req, HttpServletResponse resp, User user, String confirmPwd, String msg) throws ServletException, IOException {
        req.setAttribute("msg", msg);
        req.setAttribute("regUser", user);
        Utils.fillAttr(req, "confirmPwd", confirmPwd);
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}
