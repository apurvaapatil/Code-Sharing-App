package com.hyperskill.codesharingplatform.headers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/code/*")
public class WebAddResponseHeaderFilter implements Filter  {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Content-Type", "text/html");
        chain.doFilter(request, response);
    }
}
