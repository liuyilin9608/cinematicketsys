package com.demo.service;

import com.demo.entity.Evaluate;

import java.util.List;

/**
 * 时间: 2017/11/23 21:48
 * 功能:
 */

public interface EvaluateService extends Service {
    /**
     * 根据片名查询该影片的所有评论
     * @param name 片名
     */
    List<Evaluate> queryEvaluatesByMovieName(String name);

    List<Evaluate> queryAll();

    List<Evaluate> queryMoviesByKeyword(String keyword);

    void delete(String id);

    void add(Evaluate evaluate);

    void delete(String movieName, String username, String time);

    Evaluate query(String id);
}
