package com.org.web.servlet;
import com.org.domain.News;
import com.org.service.news.NewsService;
import com.org.service.news.impl.NewsServiceImpl;
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
 *@description: 添加新闻
 *@author:
 *@create: 2020-05-23 15:21
 */
@WebServlet("/newsAddServlet")
public class NewsAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String, String[]> map = request.getParameterMap();
        String topic = request.getParameter("topic");

        //3.封装对象
        News news = new News();
        try {
            BeanUtils.populate(news,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用Service保存
        NewsService service = new NewsServiceImpl();
        boolean flag = service.add(news);

        //通过servlet的方法获取response对象，通过getWriter方法获取PrintWriter对象
        if (flag) {
            PrintWriter out = response.getWriter();
            //5.跳转页面
            out.println("    <script>\n" +
                    "        if (confirm('添加成功，是否继续添加？')) {\n" +
                    "            window.location.href = \"newspages/news_add.jsp\";\n" +
                    "        } else {\n" +
                    "            window.location.replace(\"findNewsByPageServlet\");\n" +
                    "        }\n" +
                    "    </script>");
        }




    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
