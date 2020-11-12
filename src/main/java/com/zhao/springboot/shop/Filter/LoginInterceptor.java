package com.zhao.springboot.shop.Filter;

import com.zhao.springboot.shop.component.EntryComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor  {
@Autowired
    EntryComponent entryComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object token = request.getHeader("token");
        Optional.ofNullable(token).ifPresentOrElse((x)->{
            Object userName = entryComponent.decry(token.toString()).get("userName");
            if(userName !=null){
                request.setAttribute("userName", userName.toString());
                System.out.println("通过，你好"+ request.getAttribute("userName"));
            }else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录！");
            }
        }, ()->{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录！");
        });
        return true;
    }
}
