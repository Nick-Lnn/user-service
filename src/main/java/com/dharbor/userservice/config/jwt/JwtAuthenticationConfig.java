package com.dharbor.userservice.config.jwt;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Nicolas
 */
@Component
public class JwtAuthenticationConfig extends OncePerRequestFilter {

    private final JwtTokenConfig jwtTokenConfig;

    private final MessageSource messageSource;

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationConfig(JwtTokenConfig jwtTokenConfig, MessageSource messageSource,
                                   UserDetailsService userDetailsService) {
        this.jwtTokenConfig = jwtTokenConfig;
        this.messageSource = messageSource;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        String jwt = extractJwtFromHeader(authorizationHeader);

        if (Objects.nonNull(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                processTokenAuthentication(request, jwt);
            } catch (MalformedJwtException e) {
                String errorMessage = messageSource.getMessage("invalid.token", null,
                        LocaleContextHolder.getLocale());
                throw new RuntimeException(errorMessage);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromHeader(String authorizationHeader) {
        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    private void processTokenAuthentication(HttpServletRequest request,
                                            String jwt) {
        String username = jwtTokenConfig.getUsernameFromToken(jwt);

        if (Objects.nonNull(username)) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenConfig.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }
}
