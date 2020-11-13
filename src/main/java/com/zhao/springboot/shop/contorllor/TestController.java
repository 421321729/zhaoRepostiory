package com.zhao.springboot.shop.contorllor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/ajax")
    public Long ajaxTest(HttpServletRequest request){
        if("application/x-www-form-urlencoded".equals(request.getHeader("Content-Type"))&&
                "x-www-form-urlencoded".equals(request.getHeader("x-www-form-urlencoded"))
        ){
            return System.currentTimeMillis();
        }
        return null;
    }
}
