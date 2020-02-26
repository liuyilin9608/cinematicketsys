package com.demo.service;

import com.demo.entity.Reply;

import java.util.List;

/**
 * 描述:
 */

public interface ReplyService extends Service {
    /**
     * 根据评论id查询
     * @param id 评论id
     */
    List<Reply> queryByEvaluateId(int id);

    void add(Reply reply);
}
