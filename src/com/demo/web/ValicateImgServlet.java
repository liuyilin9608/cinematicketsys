package com.demo.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/code")
public class ValicateImgServlet extends HttpServlet {
    private static final long serialVersionUID = -8609971128908819964L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setIntHeader("Expires", -1);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");

        int width = 100;
        int height = 40;
        int xoffset = 11;
        int yoffset = 26;
        int step = 22;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) img.getGraphics();

        g.setColor(Color.lightGray);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        String charTable = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            Color c = new Color(getRan(155), getRan(155), getRan(155));
            g.setColor(c);
            char ch = charTable.charAt(getRan(charTable.length()));
            sb.append(ch);
            Font font = new Font("粗体", Font.BOLD, 24);
            g.setFont(font);
            double theta = getRan(-45, 45) * Math.PI / 180;
            g.rotate(theta, xoffset + step * i, yoffset);
            g.drawString(ch + "", xoffset + step * i, yoffset);
            g.rotate(-theta, xoffset + step * i, yoffset);
        }

        System.out.println(sb.toString());
        request.getSession().setAttribute("valicateCode", sb.toString());
        g.dispose();
        ImageIO.write(img, "jpg", response.getOutputStream());
    }

    private Random ran = new Random();

    private int getRan(int begin, int end) {
        return ran.nextInt(end - begin) + begin;
    }

    private int getRan(int bound) {
        return ran.nextInt(bound);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
