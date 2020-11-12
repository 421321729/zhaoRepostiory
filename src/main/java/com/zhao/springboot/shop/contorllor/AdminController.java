package com.zhao.springboot.shop.contorllor;

import com.zhao.springboot.shop.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "管理员控制类")
@Controller
@RequestMapping("/admin")
public class AdminController {
    @ApiOperation("测试方法")
    @GetMapping("/test")
    @ApiParam("无")
//    @Role("Adimn")
//    @PreAuthorize("hasRole(ROLE_admin)")
    public Object test(){
        return ResultUtil.success("管理员你好");
    }
}
