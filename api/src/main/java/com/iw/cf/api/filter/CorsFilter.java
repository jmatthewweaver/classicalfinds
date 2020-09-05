package com.iw.cf.api.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter
implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String origin = req.getHeader("Origin");
        resp.setHeader("Access-Control-Allow-Origin", origin);

        if("OPTIONS".equals(req.getMethod())) {
            // Always allow api access (public API)
            resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE");
            resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setHeader("Access-Control-Max-Age", "2592000");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
