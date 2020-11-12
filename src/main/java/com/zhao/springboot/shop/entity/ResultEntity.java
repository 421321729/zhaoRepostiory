package com.zhao.springboot.shop.entity;

import io.swagger.annotations.ApiModel;

@ApiModel("返回实体类")
public class ResultEntity<T> {
    public   static final String SUCCESS="SUCCESS";
    public static final String FAIL="FAIL";
    private String state;
    private String message;
    private T Data;

    public ResultEntity() {

    }

    public ResultEntity(String state, String message, T data) {
        this.state = state;
        this.message = message;
        Data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
