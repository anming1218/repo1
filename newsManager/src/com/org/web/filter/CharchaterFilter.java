package com.org.web.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 解决全站乱码问题，处理所有的请求
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将父接口变成子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String spach = ((HttpServletRequest) req).getServletPath();
        String[] urls = {"/login","/json",".js",".css",".ico",".jpg",".png",".gif"};
        boolean flag = true;
        for (String str : urls) {
            if (spach.indexOf(str) != -1) {
                flag =false;
                break;
            }
        }
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if (method.equalsIgnoreCase("post")) {
            request.setCharacterEncoding("utf-8");
        }
        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
