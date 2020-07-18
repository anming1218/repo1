package com.org.web.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 禁止访问者不通过登录页面而强行进入其他页面
 * @author wang
 */
@WebFilter(urlPatterns ={"/newspages/admin.jsp","/newspages/news_add.jsp","/newspages/news_update.jsp","/newspages/news_list.jsp","/newspages/topic_add.jsp","/newspages/topic_update.jsp","/newspages/topic_list.jsp","/newspages/user_add.jsp","/newspages/uesr_update.jsp","/newspages/user_list.jsp","/findNewsByPageServlet","/findTopicByPageServlet","/findUserByPageServlet"})
public class BreakFilter implements Filter {
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


        if(request.getSession().getAttribute("user")==null){
            response.sendRedirect(request.getContextPath()+"/findNewsByPageToHomeServlet");
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
