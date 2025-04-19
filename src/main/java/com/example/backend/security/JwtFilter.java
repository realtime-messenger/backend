package com.example.backend.security;

import com.example.backend.service.JwtService;
import com.example.backend.service.UserService;
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
    public JwtFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
    }

    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        System.out.println("FILTERING");
        System.out.println(authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("STILL FILTERING");

        try {
            final String jwt = authHeader.substring(7);
            final String username = jwtService.getAccessClaims(jwt).getSubject();

            if (jwtService.validateAccessToken(jwt)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username,
                        null
                );

                System.out.println("MAKING TOKEN VALID");

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            System.out.println("DOING FILTERING");
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}