package com.org.web.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *@program: newsManager
 *@description: 退出功能，销毁所有session
 *@author: zi jing
 *@create: 2020-06-17 15:29
 */
@WebServlet("/quitServlet")
public class QuitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        //2.销毁所有session
        session.invalidate();

        //转发页面
        response.sendRedirect(request.getContextPath()+"/findNewsByPageToHomeServlet");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
