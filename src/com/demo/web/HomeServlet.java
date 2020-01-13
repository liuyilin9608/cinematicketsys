package com.demo.web;

import com.demo.entity.Movie;
import com.demo.entity.Plan;
import com.demo.factory.BaseFactory;
import com.demo.service.MoviceService;
import com.demo.service.PlanService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/26 20:12
 * 功能: 首页
 */
@WebServlet(urlPatterns = "/home")
public class HomeServlet extends BaseServlet {
    private static final long serialVersionUID = -3647002595419471818L;
    private PlanService planService = BaseFactory.getService(PlanService.class);
    private MoviceService moviceService = BaseFactory.getService(MoviceService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询已排片的影片
        List<Plan> plans = planService.queryAllNotPlayGroupByMovieName();
        List<Movie> list = new ArrayList<>();
        for (Plan plan : plans) {
            //查询影片
            Movie movie = moviceService.queryByName(plan.movie_name);
            if (movie != null) {
                list.add(movie);
            }           
        }
        req.setAttribute("movies", list);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
