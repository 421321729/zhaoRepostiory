package com.zhao.springboot.shop.contorllor;

import com.zhao.springboot.shop.component.EntryComponent;
import com.zhao.springboot.shop.entity.User;
import com.zhao.springboot.shop.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Api(tags = "登录控制类")
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    EntryComponent entryComponent;
    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user.getUserName());
        Map<String,String> map=new HashMap<>();
        map.put("userName", user.getUserName());
        String token = entryComponent.encry(map);
        return token;
    }
}
