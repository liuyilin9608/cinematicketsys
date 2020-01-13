package com.demo.dao;

import com.demo.entity.Movie;

import java.util.List;

/**
 * 时间: 2017/11/24 11:23
 * 功能: 电影表操作
 */

public class MovieDaoImpl extends BaseDao implements MovieDao {
    @Override
    public String getTableName() {
        return TABLE_NAEM;
    }

    @Override
    public String getCreateTableSql() {
        return CREATE_TABLE_SQL;
    }

    @Override
    public Movie query(String[] columnNames, Object... values) {
        return query(Movie.class, columnNames, values);
    }

    @Override
    public void add(Movie movie) {
        String sql = "insert into " + TABLE_NAEM + " values(null,?,?,?,?,?,?,?,?,?)";
        insert(Movie.class, sql, new Object[]{movie.name, movie.pic_name, movie.director, movie.protagonist,
                movie.region, movie.language, movie.type, movie.duration, movie.synopsis});
    }

    @Override
    public int update(Movie movie) {
        if (movie.pic_name == null) {
            String sql = "update %s set %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? where %s=?";
            sql = String.format(sql, TABLE_NAEM, COLUME_NAME, COLUME_DIRECTOR, COLUME_PROTAGONIST, 
                    COLUME_REGION, COLUME_LANGUAGE, COLUME_TYPE, COLUME_DURATION, COLUME_SYNOPSIS, COLUME_ID);
            return update(sql, new Object[]{movie.name, movie.director, movie.protagonist, movie.region, 
                    movie.language, movie.type, movie.duration, movie.synopsis, movie.id});
        } else {
            String sql = "update %s set %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? where %s=?";
            sql = String.format(sql, TABLE_NAEM, COLUME_NAME, COLUME_PIC_NAME, COLUME_DIRECTOR, COLUME_PROTAGONIST, 
                    COLUME_REGION, COLUME_LANGUAGE, COLUME_TYPE, COLUME_DURATION, COLUME_SYNOPSIS, COLUME_ID);
            return update(sql, new Object[]{movie.name, movie.pic_name, movie.director, movie.protagonist,
                    movie.region, movie.language, movie.type, movie.duration, movie.synopsis, movie.id});
        }        
    }

    @Override
    public int delete(String id) {
        return update("delete from " + TABLE_NAEM + " where " + COLUME_ID + "=?", new Object[]{id});
    }

    @Override
    public List<Movie> queryAll() {
        return queryList(Movie.class, null, null);
    }

    @Override
    public List<Movie> queryMoviesByKeyword(String keyword) {
        return queryLike(Movie.class, COLUME_NAME, keyword);
    }
}
