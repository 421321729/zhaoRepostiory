package com.zhao.springboot.shop.dao;

import com.zhao.springboot.shop.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao extends JpaRepository<Authority,Integer> {
}
