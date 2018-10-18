package com.pp.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(filterName="defaultFilter",urlPatterns="/*")
public class DefaultFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DefaultFilter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("DefaultFilter doFilter...");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("DefaultFilter destroy...");
    }
}
