package com.demo.web;

import com.demo.exception.MyException;
import com.demo.entity.Hall;
import com.demo.factory.BaseFactory;
import com.demo.service.HallService;
import com.demo.util.Utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/23 21:10
 * 功能: 影厅管理
 */
@WebServlet(urlPatterns = {"/admin/halls", "/admin/halls/add", "/admin/halls/delete"})
public class HallMgrServlet extends BaseServlet {
    private static final long serialVersionUID = 6375850281387346655L;
    private HallService service = BaseFactory.getService(HallService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if (servletPath.contains("delete")) {//执行删除
            doDel(req, resp);
        } else if (servletPath.contains("add")) {//添加影厅
            doAdd(req, resp);
        } else {
            forwardToHallsJsp(req, resp);
        }        
    }

    private void forwardToHallsJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Hall> list = service.queryAll();
        if (list != null) {
            req.setAttribute("halls", list);
        }
        req.setAttribute("chkItem", 3);
        req.getRequestDispatcher("/admin/halls.jsp").forward(req, resp);
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hall hall = new Hall();
        Utils.populateToBean(hall, req.getParameterMap());
        try {
            service.add(hall);
            resp.sendRedirect(req.getContextPath() + "/admin/halls");
        } catch (MyException e) {
            req.setAttribute("msg", e.getMessage());
            Utils.fillAttr(req, hall);
            forwardToHallsJsp(req, resp);
        }
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String hallName = req.getParameter("hall");
        //如果带id参数，则是删除此影厅
        if (hallName != null) {
            service.deleteByName(hallName);
        }  
        resp.sendRedirect(req.getContextPath() + "/admin/halls");
    }
}
