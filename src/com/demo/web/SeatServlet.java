package com.demo.web;

import com.demo.entity.Hall;
import com.demo.entity.Plan;
import com.demo.entity.Seat;
import com.demo.factory.BaseFactory;
import com.demo.service.HallService;
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
 * 描述:选座
 * 时间: 2017/11/30 19:08
 */
@WebServlet(urlPatterns = "/seat")
public class SeatServlet extends BaseServlet {
    private static final long serialVersionUID = -4995938203265286092L;
    private PlanService planService = BaseFactory.getService(PlanService.class);
    private HallService hallService = BaseFactory.getService(HallService.class);
    private OrderService orderService = BaseFactory.getService(OrderService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String session = req.getParameter("session");
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            String[] ss = session.split("_");
            Plan plan = planService.query(ss[0], ss[1], ss[2]);
            if (plan != null) {
                Hall hall = hallService.queryByName(plan.hall_name);
                if (hall != null) {
                    List<List<Seat>> rowSeats = new ArrayList<>();
                    for (int i = 1; i <= hall.rows; i++) {
                        List<Seat> seats = new ArrayList<>();
                        for (int j = 1; j <= hall.columns; j++) {
                            Seat seat = new Seat();
                            seat.seat = i + "_" + j;
                            seat.sold = orderService.isSeatSold(String.valueOf(plan.id), seat.seat);
                            seats.add(seat);
                        }
                        rowSeats.add(seats);
                    }
                    req.setAttribute("price", plan.price);
                    req.setAttribute("session", session);
                    req.setAttribute("seats", rowSeats);
                    req.setAttribute("title", String.format("%s(%s %s)", plan.movie_name, hall.type, DateUtils.formatDate(plan.play_time, "yyyy-MM-dd HH:mm")));
                    req.getRequestDispatcher("/seat.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/");
            }
        }
    }
}
