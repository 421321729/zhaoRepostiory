package com.zhao.springboot.shop.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhao.springboot.shop.entity.User;
import com.zhao.springboot.shop.util.JwtUtil;
import com.zhao.springboot.shop.util.ResultUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JWTUsernameFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;
    private AuthenticationManager authenticationManager;

    public JWTUsernameFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        User user= new User();
        String token;
//        if((token=request.getHeader("token"))!=null){
//            Claims token1 = JwtUtil.checkToken(token);
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(token1.get("username"), token1.get("password"),new HashSet<>()));
//        }else{
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(),user.getAuthorities()));
//        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
         User user=(User) authResult.getPrincipal();
        System.out.println(user.toString());
        String token= JwtUtil.creatToken(user.getUsername(), user.getPassword());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(ResultUtil.success("token:"+token)));
        response.setHeader("token", token);
        out.flush();
        out.close();
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(ResultUtil.fail("账户名密码不正确")));
        out.flush();
        out.close();
    }
}
