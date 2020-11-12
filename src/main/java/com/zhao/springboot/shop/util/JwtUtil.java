package com.zhao.springboot.shop.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    public static final String salt="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
 static String token=null;
 public static  String creatToken(String username,String password){
     token= Jwts.builder().setSubject("user")
             .claim("username",username)
             .claim("password", password)
             .signWith(SignatureAlgorithm.HS256,salt)
             .compact();
     return token;
 }
 public static Claims checkToken(String token){
     Claims claims=Jwts.parser().setSigningKey(salt).parseClaimsJws(token).getBody();
     return claims;
 }
}
