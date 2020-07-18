package com.org.web.servlet;
import com.org.domain.User;
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
 *@description: 根据id查找管理员
 *@author:
 *@create: 2020-05-24 15:59
 */
@WebServlet("/findUserByIdServlet")
public class FindUserByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取id
        String id = request.getParameter("id");
        //2.调用service查询
        UserService service = new UserServiceImpl();
        User user = service.findUserById(id);

        //3.将user存入reques
        request.setAttribute("user", user);
        //4.转发到user_update.jsp
        request.getRequestDispatcher("/newspages/user_update.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
