package com.zhao.springboot.shop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.zhao.springboot.shop.component.EntryComponent;
import com.zhao.springboot.shop.contorllor.UserController;
import com.zhao.springboot.shop.dao.AuthorityDao;
import com.zhao.springboot.shop.dao.UserDao;
import com.zhao.springboot.shop.entity.Authority;
import com.zhao.springboot.shop.entity.User;
import com.zhao.springboot.shop.service.UserService;
import com.zhao.springboot.shop.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ShopApplication.class)
class ShopApplicationTests {
    @Value("${mypassword}")
    String password;
    @Value("${mysalt}")
    String salt;
    @Autowired
    UserService userService;
    @Autowired
    UserController userController;
    @Autowired
    EntryComponent entryComponent;
    @Autowired
    AuthorityDao authorityDao;
    @Autowired
    UserDao userDao;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    void Test3(){
        Map<String,String> map=new HashMap<>();
        map.put("userName", "test");
        System.out.println(entryComponent.encry(map));//a26ac92e194df0e1f3e0afeaed1e6b61ca2b3d15687a866392aedea6e11a89e3
    }
    @Test
    void Test4(){
        //User fd106c4cea55669668d268af97e4dcd729037f048d6f68bfbaba2f4ba1cb604e16d48ff3c3e34c233e55081d7a0a36aa
//       test test "a26ac92e194df0e1f3e0afeaed1e6b61ca2b3d15687a866392aedea6e11a89e3"
       //  test1 test "270be88871db0222cee220ecceb8debee9faf7866b5610b6f32dcf121609fdf0e79e557d07ed70ccc782b188d918adf9"
        System.out.println(entryComponent.decry("ee1f2681064d65d6247993bc16d9aab25861caa44779e988719ec4ad3e5934f6").get("test"));
    }
    @Test
    void contextLoads() {
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void Test()  {

        //authorityDao.deleteById(0);
        //userDao.deleteById(1);
           //userController.findUserById(2);
        System.out.println("正序排列======================");
        List<User> users = userDao.findAll();
        System.out.println(userDao.count());
        for(User i:users){
            System.out.println(i.toString());
        }
        System.out.println("倒序排列=======================");
        //倒序排序
        Sort sort =Sort.by(Sort.Direction.DESC,"userId");
        List<User> users1=userDao.findAll(sort);
        for(User i:users1){
            System.out.println(i.toString());
        }
        //分页
        Sort sort1=Sort.by(Sort.Direction.ASC,"userId");
        Pageable pageable= PageRequest.of(0, 8, sort1);
        List<User> users2 = userDao.findAll(pageable).toList();
        for(User i:users2){
            System.out.println(i.toString());
        }
        Pageable pageable2= PageRequest.of(0, 8, sort1);
        List<User> users3 = userDao.findAll(pageable2.next()).toList();

        for(User i:users3){
            System.out.println(i.toString());
        }
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void Test1() throws Exception {
//        Authority authority=new Authority();
//        authority.setAuthorityName("user");
//        authorityDao.save(authority);
//       Optional<Authority> authority= authorityDao.findById(0);
//        System.out.println(authority.get().getAuthorityName());
//          User user= new User();
//          user.setUserName("多对多");
//          user.setPsw("123456");
//        Set<Authority> set=new HashSet<>();
//        set.add(authority.get());
//        user.setAuthorities(set);
//        userService.addUser(user);
//        System.out.println();
        Authority authority = authorityDao.findById(0).get();
        System.out.println(authority);
    }
    @Test
    void testRedis(){
        JsonMapper mapper=new JsonMapper();
        System.out.println(redisTemplate.getConnectionFactory().getConnection().ping());
        Optional<User> user = userDao.findById(4);

        user.ifPresent(user1 -> {
            System.out.println(user1.toString());
            try {
                stringRedisTemplate.opsForValue().set("user"+user1.getUserId(), mapper.writeValueAsString(user1));
                redisTemplate.expire("user"+user1.getUserId(), 20, TimeUnit.SECONDS);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        System.out.println(redisTemplate.opsForValue().get("user4").toString());
//        long start = System.currentTimeMillis();
//        System.out.println(redisTemplate.getConnectionFactory().getConnection().ping());
//        System.out.println(redisTemplate.opsForValue().get("1"));
//        long end = System.currentTimeMillis();
//        System.out.println(start-end);
//        start = System.currentTimeMillis();
//        userDao.findById(1);
        //redisTemplate.opsForValue().set("y", "1");
//        Object o = redisTemplate.opsForValue().get("y");
//        System.out.println(o);

    }
    @Test
    void TestCompare(){
        long l = System.currentTimeMillis();
        userDao.findById(5);
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
        long l2 = System.currentTimeMillis();
        redisTemplate.opsForValue().get("user4");
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);
    }
    @Test
    void TestJWT(){
        String username="123";
        String password="123";
        String token = JwtUtil.creatToken(username, password);
        System.out.println(token);
        System.out.println(JwtUtil.checkToken(token).toString());
        System.out.println(JwtUtil.checkToken(token).get("username"));
    }

}
