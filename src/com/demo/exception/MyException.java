package com.demo.exception;

/**
 * 时间: 2017/11/19 20:59
 * 功能: 返回操作时的错误信息
 */

public class MyException extends Exception {
    private static final long serialVersionUID = 2881677456399300896L;

    public MyException() {}

    public MyException(String message) {
        super(message);
    }
}
