package com.zhao.springboot.shop.dao;

import com.zhao.springboot.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor {
    User findUserByUserName(String userName);
}
