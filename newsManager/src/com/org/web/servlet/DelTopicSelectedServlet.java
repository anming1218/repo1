package com.org.web.servlet;
import com.org.service.topic.TopicService;
import com.org.service.topic.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@program: NewsManager
 *@description: 删除多条主题
 *@author:
 *@create: 2020-05-25 11:58
 */
@WebServlet("/delTopicSelectedServlet")
public class DelTopicSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取所有id
        String[] ids = request.getParameterValues("tid");
        //调用service删除
        TopicService service = new TopicServiceImpl();
        service.delSelectedTopic(ids);

        //跳转页面
        response.sendRedirect(request.getContextPath() + "/findTopicByPageServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
