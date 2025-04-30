package com.example.backend.security;

import com.example.backend.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Autowired
    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ") || authHeader.length() <= 7) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);

        try {
            final String id = jwtService.getAccessClaims(jwt).getSubject();
            if (jwtService.validateAccessToken(jwt)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        id,
                        jwt,
                        null
                );
                var context = SecurityContextHolder.getContext();
                context.setAuthentication(authToken);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        filterChain.doFilter(request, response);
    }
}