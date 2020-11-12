package com.zhao.springboot.shop.component;

import com.zhao.springboot.shop.util.ResultUtil;
import com.zhao.springboot.shop.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledComponent {
    @Autowired
    RedisTemplate redisTemplate;
    @Scheduled(cron = "*/20 * * * * ?")
    public ResultEntity test(){
        System.out.println(redisTemplate.keys("*").size());
        return ResultUtil.success();
    }
}
