package com.demo.web;

import com.demo.exception.MyException;
import com.demo.entity.Movie;
import com.demo.factory.BaseFactory;
import com.demo.service.MoviceService;
import com.demo.util.FileUtils;
import com.demo.util.IOUtils;
import com.demo.util.PicUtils;
import com.demo.util.Utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间: 2017/11/23 20:42
 * 功能: 影片管理
 */
@WebServlet(urlPatterns = {"/admin/movies", "/admin/movies/add", "/admin/movies/update", "/admin/movies/delete"})
public class MovieMgrServlet extends BaseServlet {
    private static final long serialVersionUID = 2225249275913675741L;    
    private MoviceService service = BaseFactory.getService(MoviceService.class);

    @Override
    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据url判断是做什么操作
        String servletPath = req.getServletPath();
        if (servletPath.contains("add")) {
            doAddMovie(req, resp);
        } else if (servletPath.contains("update")) {
            doUpdateMovie(req, resp);
        } else if (servletPath.contains("delete")) {
            doDel(req, resp);
        } else {
            //如果关键字不为空，搜索返回，否则返回全部
            String keyword = req.getParameter("keyword");
            if (Utils.isEmpty(keyword)) {
                forwardToMoviesJsp(service.queryAll(), req, resp);
            } else {
                req.setAttribute("keyword", keyword);
                forwardToMoviesJsp(service.queryMoviesByKeyword(keyword), req, resp);
            }
        }
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("movie");
        if (movieName != null) {
            Movie movie = service.deleteByName(movieName);
            //删除成功的话，把原图片一起删除
            if (movie != null) {
                new File(getServletContext().getRealPath(GetImageServlet.IMG_SERVLET_DIR), movie.pic_name).delete();
            }
        }
        forwardToMoviesJsp(service.queryAll(), req, resp);
    }

    private void doUpdateMovie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        String movieName = req.getParameter("movie");
        if (movieName != null) {
            //查询到则返回，没有则返回影片管理
            Movie movie = service.queryByName(movieName);
            if (movie == null) {
                forwardToMoviesJsp(service.queryAll(), req, resp);
            } else {
                Utils.fillAttr(req, movie);
                req.setAttribute("add", false);
                req.getRequestDispatcher("/admin/add_update_movie.jsp").forward(req, resp);
            }
        } else {
            addOrUpdateMovie(false, req, resp);
        }
    }

    private void doAddMovie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(req)) {
            req.setAttribute("add", true);
            req.getRequestDispatcher("/admin/add_update_movie.jsp").forward(req, resp);
        } else {
            addOrUpdateMovie(true, req, resp);            
        }
    }

    private void addOrUpdateMovie(boolean add, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用来存参数
        Map<String, String> map = new HashMap<>();
        //上传图片
        String uploadPath = getServletContext().getRealPath(GetImageServlet.IMG_SERVLET_DIR);
        String tempPath = getServletContext().getRealPath("/WEB-INF/temp");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 100);
        factory.setRepository(new File(tempPath));
        ServletFileUpload upload = new ServletFileUpload(factory);
        String fileName = null;
        try {
            if (!ServletFileUpload.isMultipartContent(req)) {
                throw new MyException("请使用正确的表单");
            }
            upload.setFileSizeMax(1024 * 1024 * 3);
            upload.setHeaderEncoding("utf-8");
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem item : list) {
                //普通字段
                if (item.isFormField()) {
                    map.put(item.getFieldName(), item.getString("utf-8"));
                } else {
                    String realName = FileUtils.getFileName(item.getName());
                    if (realName == null || "".equals(realName)) {
                        continue;
                    }
                    fileName = Utils.generateImgFileName(realName);                        
                    File f = new File(uploadPath);
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    File file = new File(f, fileName);
                    InputStream in = item.getInputStream();
                    PicUtils.resizeByHeight(in, file, 300);
                    IOUtils.close(in);
                    item.delete();
                }
            }
            
            Movie movie = new Movie();
            Utils.populateToBean(movie, map);
            //校验参数有效性
            service.valicateMovie(movie);
            movie.pic_name = fileName;
            if (add) {
                service.addMovie(movie);                
            } else {
                //如果修改了图片，把原来的图片删除
                Movie m = service.updateMovie(movie);
                //修改失败删除图片
                if (m == null) {
                    if (fileName != null) {
                        new File(uploadPath, fileName).delete();
                    }
                } else if (fileName != null && !fileName.equals(m.pic_name)) {
                    new File(uploadPath, m.pic_name).delete();    
                }
            }
            resp.sendRedirect(req.getContextPath() + "/admin/movies");
        } catch (MyException e) {
            req.setAttribute("msg", e.getMessage());
            //将原来填写的返回
            Utils.fillAttr(req, map, "name");
            Utils.fillAttr(req, map, "director");
            Utils.fillAttr(req, map, "protagonist");
            Utils.fillAttr(req, map, "region");
            Utils.fillAttr(req, map, "language");
            Utils.fillAttr(req, map, "type");
            Utils.fillAttr(req, map, "duration");
            Utils.fillAttr(req, map, "synopsis");
            if (add) {
                req.setAttribute("add", true);
            } else {
                req.setAttribute("add", false);                
                Utils.fillAttr(req, map, "id");
            }            
            req.getRequestDispatcher("/admin/add_update_movie.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void forwardToMoviesJsp(List<Movie> list, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (list != null) {
            req.setAttribute("movies", list);
        }
        req.setAttribute("chkItem", 2);
        req.getRequestDispatcher("/admin/movies.jsp").forward(req, resp);
    }
}
