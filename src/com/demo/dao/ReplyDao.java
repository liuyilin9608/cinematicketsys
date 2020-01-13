package com.demo.dao;

import com.demo.entity.Reply;

import java.util.List;

/**
 * 时间: 2017/11/19 18:26
 * 功能:
 */

public interface ReplyDao extends Dao {
    String TABLE_NAEM = DB_NAME + ".reply";
    String CREATE_TABLE_SQL = "create table if not exists " + TABLE_NAEM + " (id int primary key auto_increment,username varchar(20)," +
            "nickname text,evaluate_id int,time bigint,content text)";
    String COLUME_ID = "id";
    String COLUME_USERNAME = "username";
    String COLUME_NICKNAME = "nickname";
    String COLUME_EVALUATE_ID = "evaluate_id";
    String COLUME_CONTENT = "content";
    String COLUME_TIME = "time";

    /**
     * 根据评论id查询
     * @param id 评论id
     */
    List<Reply> queryByEvaluateId(int id);

    void add(Reply reply);
}
