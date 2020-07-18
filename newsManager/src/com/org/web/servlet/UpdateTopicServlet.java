package com.org.web.servlet;
import com.org.domain.Topic;
import com.org.service.topic.TopicService;
import com.org.service.topic.impl.TopicServiceImpl;
import com.org.service.user.UserService;
import com.org.service.user.impl.UserServiceImpl;
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
 *@description: 更新主题
 *@author:
 *@create: 2020-05-24 22:48
 */
@WebServlet("/updateTopicServlet")
public class UpdateTopicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        Topic topic = new Topic();
        try {
            BeanUtils.populate(topic, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用service修改
        TopicService service = new TopicServiceImpl();
        service.updateTopic(topic);

        //5.跳转页面
        response.sendRedirect(request.getContextPath() + "/findTopicByPageServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
