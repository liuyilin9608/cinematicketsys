package com.demo.web;

import com.demo.entity.Evaluate;
import com.demo.entity.Movie;
import com.demo.entity.Reply;
import com.demo.entity.User;
import com.demo.factory.BaseFactory;
import com.demo.service.EvaluateService;
import com.demo.service.MoviceService;
import com.demo.service.ReplyService;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述: 写影评
 * 时间: 2017/12/1 16:39
 */
@WebServlet(urlPatterns = "/evaluate")
public class WriteEvaluateServlet extends BaseServlet {
    private static final long serialVersionUID = -3514964050010797273L;
    private EvaluateService evaluateService = BaseFactory.getService(EvaluateService.class);
    private MoviceService moviceService = BaseFactory.getService(MoviceService.class);
    private ReplyService replyService = BaseFactory.getService(ReplyService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("movie");
        if (movieName == null) {
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            Movie movie = moviceService.queryByName(movieName);
            String evaId = req.getParameter("eva_id");
            User user = (User) req.getSession().getAttribute("user");
            if (movie == null || evaId == null) {
                resp.sendRedirect(req.getContextPath() + "/");
            } else if (!evaId.isEmpty()) {
                //检查提交的是否相符
                Evaluate eva = evaluateService.query(evaId);
                if (eva != null) {
                    String content = req.getParameter("evaluate_content");
                    //截取回复内容里的昵称
                    Pattern pattern = Pattern.compile("\\[回复\\].+\\[/回复\\]");
                    Matcher matcher = pattern.matcher(content);
                    if (matcher.find()) {
                        String temp = matcher.group();
                        String nick = temp.replace("[回复]", "")
                                .replace("[/回复]", "");
                        if (eva.nickname.equals(nick)) {
                            Reply reply = new Reply();
                            reply.username = user.username;
                            reply.nickname = user.nickname;
                            reply.evaluate_id = Integer.valueOf(evaId);
                            reply.time = System.currentTimeMillis();
                            reply.content = content.substring(temp.length()).trim();
                            replyService.add(reply);
                            req.getSession().setAttribute("lookMovie", movie.name);
                            resp.sendRedirect(req.getContextPath() + "/movie/detail");
                        } else {
                            evaluate(req, resp, movieName, movie, user);
                        }
                    } else {
                        evaluate(req, resp, movieName, movie, user);
                    }
                } else {
                    evaluate(req, resp, movieName, movie, user);
                }
            } else {
                evaluate(req, resp, movieName, movie, user);
            }
        }
    }

    private void evaluate(HttpServletRequest req, HttpServletResponse resp, String movieName, Movie movie, User user) throws IOException {
        Evaluate evaluate = new Evaluate();
        evaluate.movie_name = movieName;
        evaluate.username = user.username;
        evaluate.nickname = user.nickname;
        evaluate.content = req.getParameter("evaluate_content").trim();
        evaluate.time = System.currentTimeMillis();
        evaluateService.add(evaluate);
        req.getSession().setAttribute("lookMovie", movie.name);
        resp.sendRedirect(req.getContextPath() + "/movie/detail");
    }
}
