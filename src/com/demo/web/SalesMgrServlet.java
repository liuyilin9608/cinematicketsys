package com.demo.web;

import com.demo.entity.Sale;
import com.demo.factory.BaseFactory;
import com.demo.service.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/23 21:32
 * 功能: 票房统计
 */
@WebServlet(urlPatterns = "/sales")
public class SalesMgrServlet extends BaseServlet {
    private static final long serialVersionUID = -7773418063086768184L;
    private OrderService orderService = BaseFactory.getService(OrderService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Sale> list = new ArrayList<>();
        Map<String, Double> realtimeSales = orderService.queryRealtimeSales();
        Map<String, Double> totalSales = orderService.queryTotalSales();
        for (Map.Entry<String, Double> entry : totalSales.entrySet()) {
            Sale sale = new Sale();
            sale.movieName = entry.getKey();
            sale.total = entry.getValue();
            Double value = realtimeSales.get(entry.getKey());
            if (value != null) {
                sale.realtime = value;
            }
            list.add(sale);
        }
        req.setAttribute("sales", list);
        req.getRequestDispatcher("/sales.jsp").forward(req, resp);
    }
}
