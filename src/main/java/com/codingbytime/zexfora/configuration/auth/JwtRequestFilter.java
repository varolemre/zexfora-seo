package com.codingbytime.zexfora.configuration.auth;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import com.codingbytime.zexfora.control.exception.CustomUnauthorizedException;
import com.codingbytime.zexfora.control.repository.UserRepository;
import com.codingbytime.zexfora.entity.table.User;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String mail = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                mail = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.warn("Unable to get JWT Token. Path URL:" + request.getRequestURI());
            } catch (ExpiredJwtException e) {
                log.warn("JWT Token has expired. Path URL:" + request.getRequestURI());
            }
        }


        if (mail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> byMail = userRepository.findByMail(mail);
            if (byMail.isEmpty()) {
                log.error("Bu tokene bağlı kullanıcı yetkilendirilemedi: {}", jwtToken);
                throw new CustomUnauthorizedException("Bu tokene bağlı kullanıcı yetkilendirilemedi!");
            }
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(mail);
            if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails))) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}

