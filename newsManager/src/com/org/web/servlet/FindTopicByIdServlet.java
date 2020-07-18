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

/**
 *@program: NewsManager
 *@description: 根据id查找主题
 *@author:
 *@create: 2020-05-24 22:41
 */
@WebServlet("/findTopicByIdServlet")
public class FindTopicByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取id
        String id = request.getParameter("id");
        //2.调用service查询
        TopicService service = new TopicServiceImpl();
        Topic topics = service.findTopicById(id);

        //3.将user存入reques
        request.setAttribute("topic", topics);
        //4.转发到topic_update.jsp
        request.getRequestDispatcher("/newspages/topic_update.jsp").forward(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
