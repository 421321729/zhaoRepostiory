package com.zhao.springboot.shop.Exception;

import com.zhao.springboot.shop.util.ResultUtil;
import com.zhao.springboot.shop.entity.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//controller加强
@ControllerAdvice
public class MyExceptionHandler {
    //拦截异常
    @ExceptionHandler(Exception.class)
    @ResponseBody//有这个注解后不会返回视图而时json
    public ResultEntity handle(Exception e){

        return ResultUtil.fail(e.getClass()+"|||||"+e.getMessage());
    }

}
