package com.org.web.servlet;
import com.org.domain.News;
import com.org.domain.PageBean;
import com.org.service.news.NewsService;
import com.org.service.news.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 分页查找各种分类新闻到达主页
 *@author:
 *@create: 2020-05-25 17:58
 */
@WebServlet("/findNewsByPageToHomeServlet")
public class FindNewsByPageToHomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");

        /**
         * 查找全部新闻
         */
        //1.获取参数
        //当前页码
        String currentPage = request.getParameter("currentPage");
        //每页显示条数
        String rows = request.getParameter("rows");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        if (rows == null || "".equals(rows)) {
            rows = "10";
        }

        //获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();

        //2.调用service查询
        NewsService service = new NewsServiceImpl();

        PageBean<News> pb = service.findUserByPage(currentPage,rows,condition);


        //3.将PageBean存入request
        request.setAttribute("pb",pb);
        //将查询条件存入request
        request.setAttribute("condition",condition);

        /**
         * 分类查找新闻
         */
        String local = "国内新闻";
        String forien = "国际新闻";
        String money = "财经新闻";

        //1.调用NewsService查询
        List<News> localnews = service.findKindNews(local);
        List<News> foriennews = service.findKindNews(forien);
        List<News> moneynews = service.findKindNews(money);

        //2.将List存入request
        request.setAttribute("localnews", localnews);
        request.setAttribute("foriennews", foriennews);
        request.setAttribute("moneynews", moneynews);

        /**
         * 查找实时新闻（新闻直播间）
         */
        List<News> realtimenews = service.findRealTimeNews();
        request.setAttribute("realtimenews",realtimenews);

        /**
         * 查找实时热搜新闻，依照查看次数
         */
        List<News> hotnews = service.findHotNews();
        request.setAttribute("hotnews",hotnews);


        //转发到home.jsp
        request.getRequestDispatcher("/home.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
