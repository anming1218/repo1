package com.org.web.servlet;
import com.org.domain.News;
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
 *@description: 通过id查找新闻
 *@author:
 *@create: 2020-05-24 15:59
 */
@WebServlet("/findNewsByIdServlet")
public class FindNewsByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取id
        String id = request.getParameter("id");
        //2.调用service查询
        NewsService service = new NewsServiceImpl();
        News news = service.findNewsById(id);

        //3.将user存入request
        request.setAttribute("news", news);
        //4.转发到news_update.jsp
        request.getRequestDispatcher("/newspages/news_update.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
