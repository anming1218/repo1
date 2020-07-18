package com.org.web.servlet;
import com.org.service.user.UserService;
import com.org.service.user.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@program: NewsManager
 *@description: 删除多个管理员
 *@author:
 *@create: 2020-05-25 11:28
 */
@WebServlet("/delUserSelectedServlet")
public class DelUserSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取所有id
        String[] ids = request.getParameterValues("uid");
        //调用service删除
        UserService service = new UserServiceImpl();
        service.delSelectUser(ids);

        //跳转页面
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
