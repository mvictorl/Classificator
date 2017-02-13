package com.mvictorl.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "JDBCFilter")
public class JDBCFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }
    
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }
}
