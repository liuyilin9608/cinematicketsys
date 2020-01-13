package com.demo.service;

import com.demo.dao.ReplyDao;
import com.demo.entity.Reply;
import com.demo.factory.BaseFactory;

import java.util.List;

/**
 * 描述:
 * 时间: 2018/3/29 11:38
 */

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao replyDao = BaseFactory.getDao(ReplyDao.class);
    
    @Override
    public List<Reply> queryByEvaluateId(int id) {
        return replyDao.queryByEvaluateId(id);
    }

    @Override
    public void add(Reply reply) {
        replyDao.add(reply);
    }
}
