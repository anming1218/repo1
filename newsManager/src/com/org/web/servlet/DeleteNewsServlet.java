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
 *@description: 删除新闻
 *@author:
 *@create: 2020-05-24 14:06
 */
@WebServlet("/deleteNewsServlet")
public class DeleteNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取id
        String id = request.getParameter("id");
        //调用service删除
        NewsService service = new NewsServiceImpl();
        service.deleteNews(id);

        //跳转页面
        response.sendRedirect(request.getContextPath()+"/findNewsByPageServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
