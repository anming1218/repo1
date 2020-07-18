package com.org.web.servlet;
import com.org.domain.Topic;
import com.org.service.topic.TopicService;
import com.org.service.topic.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *@program: NewsManager
 *@description: 查找主题到达新闻添加页面
 *@author:
 *@create: 2020-05-24 09:49
 */
@WebServlet("/findTopicListToNewsAddServlet")
public class FindTopicListToNewsAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用TopicServi完成查询
        TopicService service = new TopicServiceImpl();
        List<Topic> topics = service.findAll();
        //2.将List存入reques域
        request.setAttribute("topics", topics);
        //3.转发到topic_list.jsp
        request.getRequestDispatcher("/newspages/news_add.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
