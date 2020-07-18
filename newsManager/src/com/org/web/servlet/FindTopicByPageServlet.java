package com.org.web.servlet;
import com.org.domain.PageBean;
import com.org.domain.Topic;
import com.org.service.topic.TopicService;
import com.org.service.topic.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 分页查找全部主题
 *@author:
 *@create: 2020-05-25 18:08
 */
@WebServlet("/findTopicByPageServlet")
public class FindTopicByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

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
        TopicService service = new TopicServiceImpl();

        PageBean<Topic> pb = service.findUserByPage(currentPage, rows, condition);

        System.out.println(pb);

        //3.将PageBean存入request
        request.setAttribute("pb", pb);
        //将查询条件存入request
        request.setAttribute("condition", condition);
        //4.转发到topic_list.jsp
        request.getRequestDispatcher("/newspages/topic_list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
