package com.demo.web;

import com.demo.entity.Order;
import com.demo.entity.User;
import com.demo.factory.BaseFactory;
import com.demo.service.OrderService;
import com.demo.service.UserService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/23 21:19
 * 功能: 订单管理
 */
@WebServlet(urlPatterns = {"/admin/orders", "/admin/orders/delete", "/admin/orders/update"})
public class OrderMgrServlet extends BaseServlet {
    private static final long serialVersionUID = 8025712767156179960L;
    private OrderService orderService = BaseFactory.getService(OrderService.class);
    private UserService userService = BaseFactory.getService(UserService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if (servletPath.contains("delete")) {
            doDel(req, resp);
        } else if (servletPath.contains("update")) {
            doUpdate(req, resp);
        } else {
            String keyword = req.getParameter("keyword");
            if (keyword == null) {
                forwardToOrdersJsp(fillFields(orderService.queryAll()), req, resp);
            } else {
                forwardToOrdersJsp(fillFields(Collections.singletonList(orderService.query(keyword))), req, resp);
            }
        }
    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String amount = req.getParameter("amount");
        if (id != null && amount != null) {
            Double value = Double.valueOf(amount);
            value = Math.floor(value * 100) / 100;
            orderService.update(id, value);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/orders");
    }

    private List<Order> fillFields(List<Order> list) {
        if (list != null) {
            for (Order order : list) {
                User user = userService.query(String.valueOf(order.user_id));
                if (user != null) {
                    order.username = user.username;                        
                }
            }
        }        
        return list;
    }
    
    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null) {
            orderService.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/orders");
    }

    private void forwardToOrdersJsp(List<Order> list, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (list != null) {
            req.setAttribute("orders", list);
        }
        req.setAttribute("chkItem", 4);
        req.getRequestDispatcher("/admin/orders.jsp").forward(req, resp);
    }
}
