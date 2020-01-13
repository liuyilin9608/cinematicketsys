package com.demo.service;

import com.demo.dao.EvaluateDao;
import com.demo.entity.Evaluate;
import com.demo.factory.BaseFactory;

import java.util.List;

/**
 * 时间: 2017/11/24 11:25
 * 功能:
 */

public class EvaluateServiceImpl implements EvaluateService {
    private EvaluateDao evaluateDao = BaseFactory.getDao(EvaluateDao.class);
    
    @Override
    public List<Evaluate> queryEvaluatesByMovieName(String movieName) {
        return evaluateDao.queryEvaluatesByMovieName(movieName);
    }

    @Override
    public List<Evaluate> queryAll() {
        return evaluateDao.queryAll();
    }

    @Override
    public List<Evaluate> queryMoviesByKeyword(String keyword) {
        return evaluateDao.queryMoviesByKeyword(keyword);
    }

    @Override
    public void delete(String id) {
        evaluateDao.delete(new String[]{EvaluateDao.COLUME_ID}, id);
    }

    @Override
    public void add(Evaluate evaluate) {
        evaluateDao.add(evaluate);
    }

    @Override
    public void delete(String movieName, String username, String time) {
        evaluateDao.delete(new String[]{EvaluateDao.COLUME_MOVIE_NAME, EvaluateDao.COLUME_USERNAME, 
                EvaluateDao.COLUME_TIME}, movieName, username, time);
    }

    @Override
    public Evaluate query(String id) {
        return evaluateDao.query(new String[]{EvaluateDao.COLUME_ID}, id);
    }
}
