package com.org.web.servlet;
import com.org.service.news.NewsService;
import com.org.service.news.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@program: NewsManager
 *@description: 删除多条新闻
 *@author:
 *@create: 2020-05-25 11:55
 */
@WebServlet("/delNewsSelectedServlet")
public class DelNewsSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取所有id
        String[] ids = request.getParameterValues("nid");
        //调用service删除
        NewsService service = new NewsServiceImpl();
        service.delSelectedNews(ids);

        //跳转页面
        response.sendRedirect(request.getContextPath()+"/findNewsByPageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
