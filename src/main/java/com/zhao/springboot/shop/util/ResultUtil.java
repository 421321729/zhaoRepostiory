package com.zhao.springboot.shop.util;

import com.zhao.springboot.shop.entity.ResultEntity;
import org.springframework.stereotype.Component;

@Component
public class ResultUtil {
    public static ResultEntity success(Object o){
        ResultEntity resultEntity=new ResultEntity(ResultEntity.SUCCESS, "请求成功", o);
        return resultEntity;
    }
    public static ResultEntity success(){
        ResultEntity resultEntity=new ResultEntity();
        resultEntity.setState(ResultEntity.SUCCESS);
        resultEntity.setMessage("成功但无数据信息");
        resultEntity.setData(null);
        return resultEntity;
    }
    public static ResultEntity fail(String message){
        ResultEntity resultEntity=new ResultEntity();
        resultEntity.setState(ResultEntity.FAIL);
        resultEntity.setMessage(message);
        resultEntity.setData(null);
        return resultEntity;
    }
    public static ResultEntity success(String message){
        ResultEntity resultEntity=new ResultEntity();
        resultEntity.setState(ResultEntity.SUCCESS);
        resultEntity.setMessage(message);
        resultEntity.setData(null);
        return resultEntity;
    }
}
