package com.demo.web;

import com.demo.entity.Order;
import com.demo.entity.Ticket;
import com.demo.exception.MyException;
import com.demo.factory.BaseFactory;
import com.demo.service.OrderService;
import com.demo.util.Utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 * 时间: 2018/3/27 10:29
 */
@WebServlet(urlPatterns = "/pay")
public class PayServlet extends BaseServlet {
    private static final long serialVersionUID = -4279351107198360141L;

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = Utils.populateToBean(Order.class, req.getParameterMap());
        String pay = req.getParameter("pay");
        if (order == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
        } else if ("pay".equals(pay)) {//提交支付
            order.create_time = System.currentTimeMillis();
            OrderService service = BaseFactory.getService(OrderService.class);
            try {
                service.add(order);
            } catch (MyException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/my/tickets");
        } else {            
            Ticket ticket = UserTicketServlet.getTicket(order);
            if (ticket != null) {
                req.setAttribute("title", "付款");
                req.setAttribute("ticket", ticket);
                req.setAttribute("order", order);
                req.getRequestDispatcher("/pay.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        }
    }
}
