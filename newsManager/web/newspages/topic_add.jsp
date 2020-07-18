<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加主题--管理后台</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    <!--导入本地css-->
    <link href="${pageContext.request.contextPath}/css/mynews.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css">

    <script>
        function startTime() {
            var today = new Date();//定义日期对象
            var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年
            var MM = today.getMonth() + 1;//通过日期对象的getMonth()方法返回年
            var dd = today.getDate();//通过日期对象的getDate()方法返回年
            var hh = today.getHours();//通过日期对象的getHours方法返回小时
            var mm = today.getMinutes();//通过日期对象的getMinutes方法返回分钟
            var ss = today.getSeconds();//通过日期对象的getSeconds方法返回秒
            // 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09
            MM = checkTime(MM);
            dd = checkTime(dd);
            mm = checkTime(mm);
            ss = checkTime(ss);
            var day; //用于保存星期（getDay()方法得到星期编号）
            if (today.getDay() == 0) day = "星期日 "
            if (today.getDay() == 1) day = "星期一 "
            if (today.getDay() == 2) day = "星期二 "
            if (today.getDay() == 3) day = "星期三 "
            if (today.getDay() == 4) day = "星期四 "
            if (today.getDay() == 5) day = "星期五 "
            if (today.getDay() == 6) day = "星期六 "
            document.getElementById('nowDateTimeSpan').innerHTML = yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "   " + day;
            setTimeout('startTime()', 1000);//每一秒中重新加载startTime()方法
        }

        function checkTime(i) {
            if (i < 10) {
                i = "0" + i;
            }
            return i;
        }

        /**
         * 表单校验：
         * 主题名称：4~8位；
         */

        function checkTopicname() {
            //1.获取邮箱
            var topicname = $("#topicname").val();

            //2.定义正则
            var reg_topicname = /[\u4E00-\u9FA5]{4,8}/;

            //3.判断
            var flag = reg_topicname.test(topicname);
            if (flag) {
                //邮箱合法
                $("#topicname").css("border", "1.5px solid SpringGreen");
            } else {
                //邮箱不合法
                $("#topicname").css("border", "1px solid red");
            }
        }

        $(function () {
            //当组件失去焦点是，调用对应的校验方法
            $("#topicname").blur(checkTopicname);
        });
    </script>

    <%
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = format.format(new Date());
    %>

</head>
<body class="col-center-block" onload="startTime()">

<div class="container">
    <div class="row">
        <div class="col-md-12 fl sj " style="background-color: #007BB5;color: #ffffff">
            欢迎使用新闻管理系统！&nbsp;&nbsp;&nbsp;&nbsp;现在时间：<span style="color: #ffffff;"><span
                id="nowDateTimeSpan"></span></span>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <img src="${pageContext.request.contextPath}/images/logo.gif" alt="新闻中国"
                 class="img-responsive center-block"/>
        </div>
        <div class="col-md-9">
            <img src="${pageContext.request.contextPath}/images/a_b01.jpg" class="img-responsive"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 fl sj " style="background-color: #007BB5;color: #ffffff">
            <div id="status">亲爱的管理员${user.username}，欢迎您！ &#160;&#160;&#160;&#160; <a
                    href="${pageContext.request.contextPath}/quitServlet"><span style="color: #ffffff">退出登录</span></a></div>

        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-tabs">
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false" style="font-size: large">
                        新闻管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <a href="${pageContext.request.contextPath}/findTopicListToNewsAddServlet">
                            <li class="myli">增加新闻</li>
                        </a>
                        <a href="${pageContext.request.contextPath}/findNewsByPageServlet">
                            <li class="myli">查看新闻</li>
                        </a>
                    </ul>
                </li>

                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false" style="font-size: large">
                        主题管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <a href="${pageContext.request.contextPath}/newspages/topic_add.jsp">
                            <li class="myli">增加主题</li>
                        </a>
                        <a href="${pageContext.request.contextPath}/findTopicByPageServlet">
                            <li class="myli">查看主题</li>
                        </a>
                    </ul>
                </li>

                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false" style="font-size: large">
                        用户管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <a href="${pageContext.request.contextPath}/newspages/user_add.jsp">
                            <li class="myli">增加管理员</li>
                        </a>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet">
                            <li class="myli">查看管理员</li>
                        </a>
                    </ul>
                </li>

            </ul>
        </div>
    </div>

    <div class="row">
        <div class="jumbotron">
            <div class="container" style="width: 400px">
                <h3 style="text-align: center">添加主题</h3>
                <form id="from" action="${pageContext.request.contextPath}/topicAddServlet" method="post"
                      class="center-block">
                    <table border="1" class="table table-bordered table-hover ">

                        <div class="row form-group">
                            <label for="topicname">主题名称：</label>
                            <input type="text" class="form-control" style=" height :25px;width: 300px" id="topicname"
                                   name="topicname" placeholder="请输入主题名（4~8字）"/>
                        </div>
                        <div class="row form-group">
                            <input class="btn btn-info center-block" type="submit" value="提交">
                        </div>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div id="footer" class="row text-center">
        <p class=""> 24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a> &#160;&#160;&#160;&#160;
            新闻热线：010-627488888 <br/>
            文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a></p>
        <p class="copyright"> Copyright &copy; 1999-2020 News China gov, All Right Reserver <br/>
            新闻中国 版权所有 </p>
    </div>
</div>


</body>
</html>