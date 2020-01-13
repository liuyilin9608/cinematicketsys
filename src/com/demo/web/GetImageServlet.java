package com.demo.web;

import com.demo.util.IOUtils;
import com.demo.util.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/24 21:32
 * 功能: 获取图片
 */
@WebServlet(urlPatterns = "/img")
public class GetImageServlet extends BaseServlet {
    private static final long serialVersionUID = -6081400957219577063L;
    public static final String IMG_SERVLET_DIR = "/WEB-INF/images";

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        String fileName = req.getParameter("file");
        if (!Utils.isEmpty(fileName)) {
            String uploadDir = getServletContext().getRealPath(IMG_SERVLET_DIR);
            File file = new File(uploadDir, fileName);
            if (file.exists()) {
                OutputStream out = resp.getOutputStream();
                InputStream in = new FileInputStream(file);
                IOUtils.inToOut(in, out);
                IOUtils.closeQuietly(in, out);
            }
        }
    }
}
