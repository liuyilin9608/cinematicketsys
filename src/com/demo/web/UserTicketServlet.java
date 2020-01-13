package com.demo.web;

import com.demo.entity.Hall;
import com.demo.entity.Movie;
import com.demo.entity.Order;
import com.demo.entity.Ticket;
import com.demo.entity.Plan;
import com.demo.entity.User;
import com.demo.factory.BaseFactory;
import com.demo.service.HallService;
import com.demo.service.MoviceService;
import com.demo.service.OrderService;
import com.demo.service.PlanService;
import com.demo.util.DateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/23 15:25
 * 功能: 用户订单管理
 */
@WebServlet(urlPatterns = "/my/tickets")
public class UserTicketServlet extends BaseServlet {
    private static final long serialVersionUID = 8056006200411003099L;
    private OrderService orderService = BaseFactory.getService(OrderService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToOrderJsp(req, resp);
    }

    private List<Ticket> getTicketList(String userId) {
        List<Ticket> list = new ArrayList<>();
        List<Order> orders = orderService.queryUserOrders(userId);
        if (orders != null) {
            for (Order order : orders) {
                Ticket ticket = getTicket(order);
                if (ticket != null) {
                    list.add(ticket);
                }
            }            
        }
        return list;
    }
    
    public static Ticket getTicket(Order order) {
        PlanService planService = BaseFactory.getService(PlanService.class);
        Plan plan = planService.query(String.valueOf(order.plan_id));
        if (plan != null) {
            MoviceService moviceService = BaseFactory.getService(MoviceService.class);
            Movie movie = moviceService.queryByName(plan.movie_name);
            HallService hallService = BaseFactory.getService(HallService.class);
            Hall hall = hallService.queryByName(plan.hall_name);
            if (movie != null && hall != null) {
                Ticket ticket = new Ticket();
                ticket.orderId = order.id;
                ticket.buyTime = DateUtils.formatDate(order.create_time, "yyyy-MM-dd HH:mm");
                ticket.movieName = plan.movie_name;
                ticket.picName = movie.pic_name;
                ticket.hallName = plan.hall_name;
                ticket.playTime = DateUtils.formatDate(plan.play_time, "yyyy-MM-dd HH:mm");
                ticket.endTime = DateUtils.formatDate(plan.play_time + movie.duration * 60000, "HH:mm");
                ticket.language = movie.language;
                ticket.hallType = hall.type;
                ticket.seats = new ArrayList<>();
                String[] ss = order.seat.split("\\|");
                for (String s : ss) {
                    if (s.matches("\\d+_\\d+")) {
                        String[] split = s.split("_");
                        ticket.seats.add(split[0] + "排" + split[1] + "座");
                    }
                }
                ticket.ticketCount = ticket.seats.size();
                ticket.price = String.format("%.2f", plan.price);
                ticket.totalPrice = String.format("%.2f", order.amount);
                //根据当前时间对比放映时间，判断订单状态
                long currentTime = System.currentTimeMillis();
                if (currentTime < plan.play_time) {
                    ticket.state = "待放映";
                } else if (currentTime > plan.play_time && currentTime < plan.play_time + movie.duration * 60000) {
                    ticket.state = "正在放映";
                } else {
                    ticket.state = "已放映";
                }
                return ticket;                
            }
        }
        return null;
    }

    private void forwardToOrderJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("tickets", getTicketList(String.valueOf(user.id)));
        req.setAttribute("chkItem", 2);
        req.getRequestDispatcher("/tickets.jsp").forward(req, resp);
    }
}
