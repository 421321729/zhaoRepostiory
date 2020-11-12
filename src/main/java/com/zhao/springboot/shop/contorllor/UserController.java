package com.zhao.springboot.shop.contorllor;

import com.zhao.springboot.shop.entity.Authority;
import com.zhao.springboot.shop.entity.ResultEntity;
import com.zhao.springboot.shop.entity.User;
import com.zhao.springboot.shop.service.UserService;
import com.zhao.springboot.shop.util.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "用户控制类")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/get/{id}")
    public ResultEntity findUserById(@PathVariable int id) throws Exception {
            return ResultUtil.success(userService.findUserById(id));
    }
    @GetMapping("/getAll")
    public ResultEntity finAllUser() throws Exception {
       return ResultUtil.success(userService.finAllUser());
    }
    @PostMapping("/post")
    public ResultEntity addUser(@RequestBody User user) throws Exception {
        List<Integer> authoritiesId=new ArrayList<>();
        for(Authority i:user.getAuthorities()){
            authoritiesId.add(i.getAuthorityId());
        }
            userService.addUser(user,authoritiesId);
        return ResultUtil.success();
    }
    @DeleteMapping("/delete")
    public ResultEntity deleteUser(@RequestBody User user) throws Exception {
        userService.deleteUserById(user.getUserId());
        return ResultUtil.success();
    }
}
