package com.test1.art_test1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test1.art_test1.Credentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {
    protected StatelessLoginFilter(String urlMapping, AuthenticationManager authManager, AuthenticationSuccessHandler successHandler) {
        super(new AntPathRequestMatcher(urlMapping));
        setAuthenticationManager(authManager);
        setAuthenticationSuccessHandler(successHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final Credentials user;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
        } catch (IOException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Unable to parse Credentials");
            }
            throw new BadCredentialsException("login problem");
        }

        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPass());

        return getAuthenticationManager().authenticate(loginToken);
    }
}
