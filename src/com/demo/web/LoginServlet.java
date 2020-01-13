package com.demo.web;

import com.demo.entity.User;
import com.demo.exception.MyException;
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
 * 时间: 2017/11/20 16:57
 * 功能: 用户登录
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends BaseServlet {
    private static final long serialVersionUID = 8264050085226713309L;
    private UserService service = BaseFactory.getService(UserService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //带了登录界面的上级url的话，回到这个url，否则转到主页
        String from = req.getParameter("from");
        if (from != null) {
            req.setAttribute("from", from);
        }
        if (username == null || password == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            try {
                User user = service.valicateUser(username, password);
                //检查验证码是否正确
                String valicateCode = req.getParameter("valicateCode");
                HttpSession session = req.getSession();
                String sessionValicateCode = (String) session.getAttribute("valicateCode");
                if (valicateCode == null || !valicateCode.equalsIgnoreCase(sessionValicateCode)) {
                   throw new MyException("验证码不正确");
                }
                req.getSession().setAttribute("user", user);
                req.getSession().setMaxInactiveInterval(7200);
                if (from == null) {
                    resp.sendRedirect(req.getContextPath() + "/");
                } else {
                    resp.sendRedirect(req.getContextPath() + from);
                }                
            } catch (MyException e) {
                req.setAttribute("msg", e.getMessage());
                Utils.fillAttr(req, "username", username);
                Utils.fillAttr(req, "password", password);
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }
}
