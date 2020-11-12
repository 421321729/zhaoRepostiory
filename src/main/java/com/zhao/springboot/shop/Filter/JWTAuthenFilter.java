package com.zhao.springboot.shop.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhao.springboot.shop.util.JwtUtil;
import com.zhao.springboot.shop.util.ResultUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenFilter extends BasicAuthenticationFilter {
    public JWTAuthenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token=request.getHeader("token");
        if(token!=null){
            Claims token1 = JwtUtil.checkToken(token);
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(token1.get("username"),token1.get("password"));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }else {
            response.getWriter().write(new ObjectMapper().writeValueAsString(ResultUtil.fail("未登录")));
        }

    }

}
