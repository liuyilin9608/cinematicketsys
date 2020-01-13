package com.demo.web;

import com.demo.entity.Hall;
import com.demo.entity.Movie;
import com.demo.entity.MovieSession;
import com.demo.entity.Plan;
import com.demo.factory.BaseFactory;
import com.demo.service.HallService;
import com.demo.service.MoviceService;
import com.demo.service.PlanService;
import com.demo.util.DateUtils;
import com.demo.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 * 时间: 2017/11/29 20:51
 */
@WebServlet(urlPatterns = "/sessions")
public class MovieSessionServlet extends BaseServlet {
    private static final long serialVersionUID = 8082316534539662567L;
    private PlanService planService = BaseFactory.getService(PlanService.class);
    private HallService hallService = BaseFactory.getService(HallService.class);
    private MoviceService moviceService = BaseFactory.getService(MoviceService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("movie");
        if (Utils.isEmpty(movieName)) {
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            Movie movie = moviceService.queryByName(movieName);
            if (movie == null) {
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                List<Plan> plans = planService.queryNotPlayPlansByMovieName(movie.name);
                if (plans != null && !plans.isEmpty()) {
                    List<MovieSession> sessions = new ArrayList<>();
                    for (Plan plan : plans) {
                        MovieSession ms = new MovieSession();
                        ms.planInfo = String.format("%s_%s_%d", plan.movie_name, plan.hall_name, plan.play_time);
                        ms.date = DateUtils.formatDate(plan.play_time, "yyyy-MM-dd");
                        ms.time = DateUtils.formatDate(plan.play_time, "HH:mm");
                        long overTime = plan.play_time + movie.duration * 60000;
                        if (!DateUtils.isSame(Calendar.DATE, plan.play_time, overTime)) {
                            ms.overTime = "次日" + DateUtils.formatDate(overTime, "HH:mm");
                        } else {
                            ms.overTime = DateUtils.formatDate(overTime, "HH:mm");
                        }
                        ms.price = plan.price;
                        ms.hallName = plan.hall_name;
                        Hall hall = hallService.queryByName(plan.hall_name);
                        if (hall == null) {
                            resp.sendRedirect(req.getContextPath() + "/");
                            return;
                        } else {
                            ms.hallType = hall.type;
                        }          
                        sessions.add(ms);
                    }
                    req.setAttribute("sessions", sessions);
                    req.setAttribute("title", "选择场次: " + movie.name);
                    req.getRequestDispatcher("/sessions.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/");
                }
            }
        }
    }
}
