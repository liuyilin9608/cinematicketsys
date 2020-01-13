package com.demo.entity;

import com.demo.util.Utils;

/**
 * 时间: 2017/11/18 19:11
 * 功能: 放映厅
 */

public class Hall implements Comparable<Hall> {
    public int id;
    public String name;//影厅名
    public int rows;//总排数
    public int columns;//总列数
    public String type;//影厅类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Hall o) {
        return Utils.compare(this, name, o, o.name);
    }
}
