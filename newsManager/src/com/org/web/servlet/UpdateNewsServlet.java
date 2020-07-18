package com.org.web.servlet;
import com.org.domain.News;
import com.org.service.news.NewsService;
import com.org.service.news.impl.NewsServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 更新新闻
 *@author:
 *@create: 2020-05-24 16:05
 */
@WebServlet("/updateNewsServlet")
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        News news = new News();
        try {
            BeanUtils.populate(news, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用service修改
        NewsService service = new NewsServiceImpl();
        service.updateUser(news);

        //5.跳转页面
        response.sendRedirect(request.getContextPath() + "/findNewsByPageServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
