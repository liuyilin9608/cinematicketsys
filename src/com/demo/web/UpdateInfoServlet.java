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
 * 时间: 2017/11/23 10:38
 * 功能: 修改个人资料
 */
@WebServlet(urlPatterns = "/my/update")
public class UpdateInfoServlet extends BaseServlet {
    private static final long serialVersionUID = 5570895161677779080L;
    private UserService service = BaseFactory.getService(UserService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().isEmpty()) {
            forwardToUpdateJsp(req, resp);
        } else {
            User user = new User();
            Utils.populateToBean(user, req.getParameterMap());
            try {
                //更新session里的user对象
                req.getSession().setAttribute("user", service.updateInfo(user));
                req.setAttribute("success", true);
            } catch (MyException e) {
                req.setAttribute("msg", e.getMessage());
                Utils.fillAttr(req, "nickname", user.nickname);
                Utils.fillAttr(req, "phone", user.phone);
            }
            forwardToUpdateJsp(req, resp);
        }
    }

    private void forwardToUpdateJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("chkItem", 3);
        req.getRequestDispatcher("/update_info.jsp").forward(req, resp);
    }
}
