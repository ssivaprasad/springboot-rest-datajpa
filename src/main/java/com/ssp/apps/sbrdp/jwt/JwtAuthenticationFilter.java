package com.ssp.apps.sbrdp.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                obtainUsername(request), obtainPassword(request));

        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException {
        String jwtToken = JwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        response.addHeader("Authorization", "Bearer " + jwtToken);
    }

    // Incase if client send the payload in request body as JSON
    private JwtRequest extractUserDetails(HttpServletRequest request) {
        JwtRequest jwtRequest = null;
        try {
            jwtRequest = new ObjectMapper().readValue(request.getInputStream(), JwtRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Error Occured while reading user credentials from request");
        }
        return jwtRequest;
    }
}
