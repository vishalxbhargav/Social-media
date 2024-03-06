package com.vishalxbhargav.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jwtValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader(jwtConstent.JWT_Header);

        if(jwt!=null){
            try {
                String email=JwtProvider.getEmailFromJwtToken(jwt);
                List<GrantedAuthority> authorityList=new ArrayList<>();
                Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,authorityList);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (Exception e){
                throw new BadCredentialsException("Invalid Token...");
            }
        }
        filterChain.doFilter(request,response);
    }

}
