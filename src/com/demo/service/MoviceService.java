package com.demo.service;

import com.demo.exception.MyException;
import com.demo.entity.Movie;

import java.util.List;

/**
 * 时间: 2017/11/23 21:48
 * 功能:
 */

public interface MoviceService extends Service {
    /**
     * 查询影片
     * @param id 影片id
     */
    Movie query(String id);

    /**
     * 检查参数有效性
     */    
    void valicateMovie(Movie movie) throws MyException;

    /**
     * 添加影片
     * @param movie 影片对象
     */
    void addMovie(Movie movie) throws MyException;

    /**
     * 更新影片
     * @param movie 影片对象
     * @return 如果更新成功，返回更新前的影片对象，否则返回null
     */
    Movie updateMovie(Movie movie) throws MyException;

    /**
     * 删除影片
     * @param id 影片id
     * @return 如果删除成功，返回被删除的影片对象，否则返回null
     */
    Movie delete(String id);

    /**
     * 获取所有
     */
    List<Movie> queryAll();

    /**
     * 通过关键字搜索
     */
    List<Movie> queryMoviesByKeyword(String keyword);

    /**
     * 根据片名查找
     */
    Movie queryByName(String name);

    /**
     * 根据片名删除
     */
    Movie deleteByName(String name);
}
