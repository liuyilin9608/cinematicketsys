package com.demo.service;

import com.demo.exception.MyException;
import com.demo.entity.Hall;

import java.util.List;

/**
 * 时间: 2017/11/23 21:48
 * 功能:
 */

public interface HallService extends Service {
    /**
     * 获取所有影厅
     */
    List<Hall> queryAll();

    /**
     * 删除影厅
     * @param id 影厅id
     */
    void delete(String id);

    /**
     * 添加影厅
     */
    void add(Hall hall) throws MyException;

    Hall queryByName(String name);

    void deleteByName(String name);
}
