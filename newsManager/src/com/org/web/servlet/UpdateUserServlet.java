package com.org.web.servlet;
import com.org.domain.User;
import com.org.service.user.UserService;
import com.org.service.user.impl.UserServiceImpl;
import com.org.util.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 *@program: NewsManager
 *@description: 更新管理员
 *@author:
 *@create: 2020-05-24 16:05
 */
@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        //2.1获取用户输入原密码与用户id
        String oldpassword = request.getParameter("oldpassword");
        String id = request.getParameter("id");

        Map<String, String[]> map = request.getParameterMap();
        //2.3.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.原密码校验,调用service查询
        UserService service = new UserServiceImpl();
        User loginUser = service.findUserById(id);

        //4.判断原密码是否正确
        if (MD5Utils.md5(oldpassword).equals(loginUser.getPassword())) {

            //4.调用service修改
            service.updateUser(user);

            //5.跳转页面
            response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");

        } else {
            //修改失败
            //提示信息
            request.setAttribute("update_msg", "原密码错误!");
            //跳转登录页面
            request.getRequestDispatcher("/findUserByIdServlet").forward(request, response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
