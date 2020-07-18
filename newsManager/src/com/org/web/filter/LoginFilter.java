package com.org.web.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 保证顺利进入home.jsp
 * @author wang
 */
@WebFilter(urlPatterns ={"/home.jsp","/index.jsp"})
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;

        String spach = ((HttpServletRequest) req).getServletPath();
        String[] urls = {"/login","/json",".js",".css",".ico",".jpg",".png",".gif"};
        boolean flag = true;
        for (String str : urls) {
            if (spach.indexOf(str) != -1) {
                flag =false;
                break;
            }
        }

        if(request.getSession().getAttribute("news")==null){
            response.sendRedirect(request.getContextPath()+"/findNewsByPageToHomeServlet");
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
