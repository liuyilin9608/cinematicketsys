package com.demo.dao;

import com.demo.entity.Reply;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 描述:
 * 时间: 2018/3/29 11:31
 */

public class ReplyDaoImpl extends BaseDao implements ReplyDao {
    
    @Override
    public List<Reply> queryByEvaluateId(int id) {
        String sql = String.format("select * from %s where %s=? order by %s desc", TABLE_NAEM, COLUME_EVALUATE_ID, COLUME_TIME);
        try {
            return getQueryRunner().query(sql, new BeanListHandler<>(Reply.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void add(Reply reply) {
        String sql = "insert into " + TABLE_NAEM + " values(null,?,?,?,?,?)";
        insert(Reply.class, sql, new Object[]{reply.username, reply.nickname, reply.evaluate_id, reply.time, reply.content});
    }

    @Override
    public String getTableName() {
        return TABLE_NAEM;
    }

    @Override
    public String getCreateTableSql() {
        return CREATE_TABLE_SQL;
    }
}
