package com.ecommerce.accountservice.security;

import com.ecommerce.accountservice.domain.ApplicationUser;
import com.ecommerce.accountservice.service.impl.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import static com.ecommerce.accountservice.security.SecurityConstants.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , FilterChain filterChain) throws ServletException, IOException {
        try {

            String jwt = getJwtFromRequest(httpServletRequest);

            if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody();

                ApplicationUser applicationUser = ApplicationUser.builder()
                        .applicationUserId(UUID.fromString((String)claims.get("id")))
                        .username((String)claims.get("username"))
                        .fullName((String)claims.get("fullName"))
                        .userAddress((String)claims.get("userAddress"))
                        .userPhoneNumber((Long)claims.get("userPhoneNumber"))
                        .build();

                UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(
                        applicationUser,null, Collections.emptyList());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }catch (Exception ex){
            log.error("Kullanici dogrulamasi gerceklestirilemedi",ex);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }


    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }

}