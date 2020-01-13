package com.demo.web;

import com.demo.entity.Evaluate;
import com.demo.entity.Movie;
import com.demo.factory.BaseFactory;
import com.demo.service.EvaluateService;
import com.demo.service.MoviceService;
import com.demo.service.ReplyService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/26 22:02
 * 功能: 影片详情
 */
@WebServlet(urlPatterns = "/movie/detail")
public class MovieDetailServlet extends BaseServlet {
    private static final long serialVersionUID = -4869371109392125427L;
    private MoviceService moviceService = BaseFactory.getService(MoviceService.class);
    private EvaluateService evaluateService = BaseFactory.getService(EvaluateService.class);
    private ReplyService replyService = BaseFactory.getService(ReplyService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("movie");
        if (movieName == null) {
            String lookMovie = (String) req.getSession().getAttribute("lookMovie");
            if (lookMovie == null) {
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                back(req, resp, lookMovie);
            }
        } else {
            back(req, resp, movieName);            
        }
    }

    private void back(HttpServletRequest req, HttpServletResponse resp, String movieName) throws IOException, ServletException {
        Movie movie = moviceService.queryByName(movieName);
        if (movie == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getSession().setAttribute("lookMovie", movieName);
            //返回影片信息及该影片的评价、回复
            req.setAttribute("movie", movie);
            List<Evaluate> list = evaluateService.queryEvaluatesByMovieName(movie.name);                
            EvaluateMgrServlet.fillEvaMap(list, req, replyService);
            req.setAttribute("title", "影片详情");
            req.getRequestDispatcher("/movie_detail.jsp").forward(req, resp);
        }
    }
}
