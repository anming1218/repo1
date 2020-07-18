package com.org.web.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ErrorFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            //将状态码置为200，不然状态码依然是404
            //  response.setStatus(HttpServletResponse.SC_OK);
            request.getRequestDispatcher("/error/error404.jsp").forward(request, response);

        } else if (response.getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            //将状态码置为200，不然状态码依然是500
            //  response.setStatus(HttpServletResponse.SC_OK);
            request.getRequestDispatcher("/error/error500.jsp").forward(request, response);

        } else {

            chain.doFilter(req, resp);

        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
