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
import java.util.List;

/**
 *@program: NewsManager
 *@description: 新闻详情阅读
 *@author:
 *@create: 2020-06-12 10:14
 */
@WebServlet("/readNewsByIdServlet")
public class ReadNewsByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取id
        String id = request.getParameter("id");
        String local = "国内新闻";
        String forien = "国际新闻";
        String money = "财经新闻";
        //2.调用service查询
        NewsService service = new NewsServiceImpl();
        //记录查看次数
        service.readNewsFrequency(id);
        News news = service.findNewsById(id);
        List<News> localnews = service.findKindNews(local);
        List<News> foriennews = service.findKindNews(forien);
        List<News> moneynews = service.findKindNews(money);

        //3.将news存入request
        request.setAttribute("news", news);
        request.setAttribute("localnews", localnews);
        request.setAttribute("foriennews", foriennews);
        request.setAttribute("moneynews", moneynews);
        //4.转发到news_read.jsp
        request.getRequestDispatcher("/newspages/news_read.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
