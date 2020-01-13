package com.demo.web;

import com.demo.entity.Hall;
import com.demo.entity.Order;
import com.demo.entity.Plan;
import com.demo.entity.User;
import com.demo.exception.MyException;
import com.demo.factory.BaseFactory;
import com.demo.service.HallService;
import com.demo.service.PlanService;
import com.demo.service.UserService;
import com.demo.util.DateUtils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:购票
 * 时间: 2017/11/30 22:20
 */
@WebServlet(urlPatterns = "/order")
public class GenerateOrderServlet extends BaseServlet {
    private static final long serialVersionUID = -989924899669241621L;
    private HallService hallService = BaseFactory.getService(HallService.class);
    private PlanService planService = BaseFactory.getService(PlanService.class);
    private UserService userService = BaseFactory.getService(UserService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String session = req.getParameter("session");
        if (session != null) {
            //查询排片
            String[] ss = session.split("_");
            Plan plan = planService.query(ss[0], ss[1], ss[2]);
            if (plan != null) {
                //查询影厅
                Hall hall = hallService.queryByName(plan.hall_name);
                if (hall != null) {
                    User user = (User) req.getSession().getAttribute("user");
                    //看数据库是否还有用户信息，可能被删除了
                    if (userService.query(String.valueOf(user.id)) == null) {
                        resp.sendRedirect(req.getContextPath() + "/logout");
                        return;
                    }
                    Order order = new Order();
                    order.movie_name = plan.movie_name;
                    order.plan_id = plan.id;
                    order.user_id = user.id;
                    Map<String, String[]> map = req.getParameterMap();
                    int count = 0;
                    try {
                        StringBuilder sb = new StringBuilder();
                        for (Map.Entry<String, String[]> entry : map.entrySet()) {
                            String key = entry.getKey();
                            String[] value = entry.getValue();
                            if (key.matches("\\d+_\\d+") && "1".equals(value[0])) {
                                //查询这个座位是否正确
                                String[] rc = key.split("_");
                                if (hall.rows >= Integer.valueOf(rc[0]) && hall.columns >= Integer.valueOf(rc[1])) {  
                                    if (count == 0) {
                                        sb.append("|");
                                    }                 
                                    sb.append(key);
                                    sb.append("|");
                                    count++;
                                } else {
                                    throw new MyException("选座失败，座位号与实际不符，请重新选座。");
                                }
                            }
                        }
                        if (sb.length() == 0) {
                            throw new MyException("请选择座位。");
                        }
                        order.seat = sb.toString();
                        order.amount = Math.floor(plan.price * count * 100) / 100;                        
                    } catch (MyException e) {
                        req.setAttribute("failed", e.getMessage());
                        req.setAttribute("session", session);
                        req.setAttribute("title", String.format("%s(%s)", plan.movie_name, DateUtils.formatDate(plan.play_time, "yyyy-MM-dd HH:mm")));
                        req.getRequestDispatcher("/seat.jsp").forward(req, resp);
                        return;
                    }
                    //选座成功
                    req.setAttribute("order", order);
                    req.setAttribute("session", session);
                    req.setAttribute("title", String.format("%s(%s)", plan.movie_name, DateUtils.formatDate(plan.play_time, "yyyy-MM-dd HH:mm")));
                    req.getRequestDispatcher("/seat.jsp").forward(req, resp);
                    return;
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
