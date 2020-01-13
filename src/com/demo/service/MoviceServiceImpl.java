package com.demo.service;

import com.demo.exception.MyException;
import com.demo.dao.EvaluateDao;
import com.demo.dao.MovieDao;
import com.demo.dao.OrderDao;
import com.demo.dao.PlanDao;
import com.demo.entity.Movie;
import com.demo.entity.Plan;
import com.demo.factory.BaseFactory;
import com.demo.util.Utils;

import java.util.Collections;
import java.util.List;

/**
 * 时间: 2017/11/24 11:26
 * 功能:
 */

public class MoviceServiceImpl implements MoviceService {
    private MovieDao movieDao = BaseFactory.getDao(MovieDao.class);
    private PlanDao planDao = BaseFactory.getDao(PlanDao.class);
    private OrderDao orderDao = BaseFactory.getDao(OrderDao.class);
    private EvaluateDao evaluateDao = BaseFactory.getDao(EvaluateDao.class);
    
    @Override
    public Movie query(String id) {
        return movieDao.query(new String[]{MovieDao.COLUME_ID}, id);
    }
    
    @Override
    public void valicateMovie(Movie movie) throws MyException {        
        if (Utils.isEmpty(movie.name)) {
            throw new MyException("片名不能为空");
        } else if (movie.duration == 0) {
            throw new MyException("时长必须是大于0的整数");
        }
    }

    @Override
    public void addMovie(Movie movie) throws MyException {
        if (movieDao.query(new String[]{MovieDao.COLUME_NAME}, movie.name) != null) {
            throw new MyException("影片已存在");
        } else {
            movieDao.add(movie);
        }
    }

    @Override
    public Movie updateMovie(Movie movie) throws MyException {
        if (movie.id == 0) {
            throw new MyException("参数不正确");
        } else {
            Movie m = query(String.valueOf(movie.id));
            if (m == null) {
                throw new MyException("影片不存在");
            } else {
                return movieDao.update(movie) > 0 ? m : null;
            }
        }
    }

    @Override
    public Movie delete(String id) {                
        return delete(query(id));
    }

    private Movie delete(Movie movie) {
        if (movie != null) {
            boolean result = movieDao.delete(String.valueOf(movie.id)) > 0;
            if (result) {
                //查询排片
                Plan plan = planDao.query(new String[]{PlanDao.COLUME_MOVIE_NAME}, movie.name);
                if (plan != null) {
                    //删除排片
                    int rows = planDao.delete(new String[]{PlanDao.COLUME_MOVIE_NAME}, movie.name);
                    if (rows > 0) {
                        //删除订单
                        orderDao.delete(new String[]{OrderDao.COLUME_PLAN_ID}, plan.id);
                    }
                }
                //删除评价
                evaluateDao.delete(new String[]{EvaluateDao.COLUME_MOVIE_NAME}, movie.name);
                return movie;
            }
        }
        return null;
    }
    
    @Override
    public List<Movie> queryAll() {
        List<Movie> list = movieDao.queryAll();
        if (list != null) {
            Collections.sort(list);
        }
        return list;
    }

    @Override
    public List<Movie> queryMoviesByKeyword(String keyword) {
        List<Movie> list = movieDao.queryMoviesByKeyword(keyword);
        if (list != null) {
            Collections.sort(list);
        }
        return list;
    }

    @Override
    public Movie queryByName(String name) {
        return movieDao.query(new String[]{MovieDao.COLUME_NAME}, name);
    }

    @Override
    public Movie deleteByName(String movieName) {
        return delete(queryByName(movieName));
    }
}
