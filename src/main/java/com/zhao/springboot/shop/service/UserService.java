package com.zhao.springboot.shop.service;

import com.zhao.springboot.shop.dao.AuthorityDao;
import com.zhao.springboot.shop.dao.UserDao;
import com.zhao.springboot.shop.entity.Authority;
import com.zhao.springboot.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserService{
    @Autowired
    UserDao userDao;
    @Autowired
    AuthorityDao authorityDao;
    public List<User> finAllUser() throws Exception {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            throw new Exception("暂无用户数据");
        }
    }
    public User findUserById(int id) throws Exception {
        try {
            return userDao.findById(id).get();
        } catch (Exception e) {
            throw new Exception("用户不存在");
        }
    }
    public User addUser(User user,List<Integer> authorityId) throws Exception {
        try {

            List<Authority> authority = authorityDao.findAllById(authorityId);
            Set<Authority> set=new HashSet<>(authority);
            user.setAuthorities(set);
            return userDao.save(user);
        } catch (Exception e){
            throw new Exception("该用户已存在，请重试");
        }
    }
    public User findUserByUserName(String userName) throws Exception {
        try {
            return userDao.findUserByUserName(userName);
        } catch (Exception e) {
            throw new Exception("该用户不存在");
        }

    }
    public void deleteUserById(int id) throws Exception {
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            throw new Exception("删除失败");
        }
    }
}
