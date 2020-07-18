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
 *@description: 删除主题
 *@author:
 *@create: 2020-05-24 14:31
 */
@WebServlet("/deleteTopicServlet")
public class DeleteTopicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取id
        String id = request.getParameter("id");
        //调用service方法
        TopicService service = new TopicServiceImpl();
        service.deleteTopic(id);

        //跳转页面
        response.sendRedirect(request.getContextPath() + "/findTopicByPageServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
