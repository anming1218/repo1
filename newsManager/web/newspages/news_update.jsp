<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2020/5/24
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改新闻</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>


    <%
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = format.format(new Date());
    %>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改新闻</h3>
    <form action="${pageContext.request.contextPath}/updateNewsServlet" method="post">
        <!--  隐藏域 提交id-->
        <input type="hidden" name="id" value="${news.id}">

        <div class="form-group">
            <label for="topic">主题：</label>
            <input type="text" class="form-control" id="topic" name="topic" value="${news.topic}" placeholder="请输入主题"/>
        </div>


        <div class="form-group">
            <label for="title">标题：</label>
            <textarea id="title" cols="60" rows="2" name="title" placeholder="请输入标题">${news.title}</textarea>
        </div>

        <div class="form-group">
            <label for="author">作者：</label>
            <input type="text" class="form-control" value="${news.author}" id="author" name="author"
                   placeholder="请输入作者名称"/>
        </div>

        <div class="form-group">
            <label for="summary">摘要：</label>
            <textarea id="summary" cols="60" rows="4" name="summary" placeholder="请输入摘要">${news.summary}</textarea>
        </div>

        <!--  隐藏域 提交发布时间和发布人-->
        <input type="hidden" name="creattime" value="${news.creattime}">
        <input type="hidden" name="creatby" value="${news.creatby}">

        <div class="form-group">
            <input type="hidden" class="form-control" value="<%=date%>" id="modifytime" name="modifytime" readonly="readonly"/>
        </div>

        <div class="form-group">
            <input type="hidden" class="form-control" value="${user.username}" id="modifyby" name="modifyby" placeholder="请输入修改人姓名"/>
        </div>


        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <a href="${pageContext.request.contextPath}/findNewsByPageServlet"><input class="btn btn-default" type="button" value="返回"/></a>
        </div>
    </form>
</div>
</body>
</html>
