package com.demo.web;

import com.demo.exception.MyException;
import com.demo.entity.Hall;
import com.demo.entity.Movie;
import com.demo.entity.Plan;
import com.demo.factory.BaseFactory;
import com.demo.service.HallService;
import com.demo.service.MoviceService;
import com.demo.service.PlanService;
import com.demo.util.Utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/23 21:30
 * 功能: 排片管理
 */
@WebServlet(urlPatterns = {"/admin/plans", "/admin/plans/add", "/admin/plans/cancel"})
public class PlanMgrServlet extends BaseServlet {
    private static final long serialVersionUID = -6874708411049815744L;
    private PlanService planService = BaseFactory.getService(PlanService.class);
    private HallService hallService = BaseFactory.getService(HallService.class);
    private MoviceService moviceService = BaseFactory.getService(MoviceService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if (servletPath.contains("cancel")) {//取消排片
            doDel(req, resp);
        } else if (servletPath.contains("add")) {//执行添加
            doAdd(req, resp);
        } else {
            String keyword = req.getParameter("keyword");
            if (Utils.isEmpty(keyword)) {
                backListToPlansJsp(planService.queryAll(), req, resp);
            } else {
                req.setAttribute("keyword", keyword);
                backListToPlansJsp(planService.queryPlansByKeyword(keyword), req, resp);
            }            
        }
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String planInfo = req.getParameter("plan");
        if (planInfo != null) {
            //从数据库删除
            String[] ss = planInfo.split("_");
            planService.delete(ss[0], ss[1], ss[2]);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/plans");
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("movie");
        if (movieName != null) {
            Movie movie = moviceService.queryByName(movieName);
            if (movie != null) {
                req.setAttribute("movie_name", movie.name);
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/movies");
                return;
            }                        
        } else {
            Plan plan = new Plan();
            Utils.populateToBean(plan, req.getParameterMap());
            try {
                planService.add(plan);
                req.setAttribute("success", true);
            } catch (MyException e) {
                req.setAttribute("msg", e.getMessage());
                Utils.fillAttr(req, plan);
            }
        }
        //查询所有影厅，并返回
        List<Hall> list = hallService.queryAll();
        if (list != null) {
            req.setAttribute("halls", list);
        }
        req.getRequestDispatcher("/admin/plan_movie.jsp").forward(req, resp);
    }
    
    private void backListToPlansJsp(List<Plan> list, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (list != null) {
            req.setAttribute("plans", list);
        }
        req.setAttribute("chkItem", 6);
        req.getRequestDispatcher("/admin/plans.jsp").forward(req, resp);
    }
}
