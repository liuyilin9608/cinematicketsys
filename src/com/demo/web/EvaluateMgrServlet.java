package com.demo.web;

import com.demo.entity.Evaluate;
import com.demo.entity.Reply;
import com.demo.factory.BaseFactory;
import com.demo.service.EvaluateService;
import com.demo.service.ReplyService;
import com.demo.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/23 21:27
 * 功能: 影评管理
 */
@WebServlet(urlPatterns = {"/admin/evaluates", "/admin/evaluates/delete"})
public class EvaluateMgrServlet extends BaseServlet {
    private static final long serialVersionUID = -6107691393134136964L;
    private EvaluateService evaluateService = BaseFactory.getService(EvaluateService.class);
    private ReplyService replyService = BaseFactory.getService(ReplyService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据url判断是做什么操作
        String servletPath = req.getServletPath();
        if (servletPath.contains("delete")) {
            doDel(req, resp);
        } else {
            //如果关键字不为空，搜索返回，否则返回全部
            String keyword = req.getParameter("keyword");
            if (Utils.isEmpty(keyword)) {
                forwardToEvaluatesJsp(evaluateService.queryAll(), req, resp);
            } else {
                req.setAttribute("keyword", keyword);
                forwardToEvaluatesJsp(evaluateService.queryMoviesByKeyword(keyword), req, resp);
            }
        }
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String evaluateInfo = req.getParameter("i");
        if (evaluateInfo != null) {
            String[] ss = evaluateInfo.split("_");
            evaluateService.delete(ss[0], ss[1], ss[2]);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/evaluates");
    }

    private void forwardToEvaluatesJsp(List<Evaluate> list, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillEvaMap(list, req, replyService);
        req.setAttribute("chkItem", 5);
        req.getRequestDispatcher("/admin/evaluates.jsp").forward(req, resp);
    }

    public static void fillEvaMap(List<Evaluate> list, HttpServletRequest req, ReplyService replyService) {
        if (list != null) {
            LinkedHashMap<Evaluate, List<Reply>> map = new LinkedHashMap<>();
            for (Evaluate eva : list) {
                List<Reply> li = replyService.queryByEvaluateId(eva.id);
                map.put(eva, li == null ? new ArrayList<>() : li);
            }
            req.setAttribute("evamap", map);
        }
    }
}
