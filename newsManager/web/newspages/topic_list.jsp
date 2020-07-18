<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>主题信息管理</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function deleteTopic(id) {

            //用户安全提示
            if (confirm("你确定要删除吗？")) {
                //访问路径
                location.href = "${pageContext.request.contextPath}/deleteTopicServlet?id=" + id;
            }

        }

        window.onload = function () {
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function () {
                if (confirm("您确定要删除选中条目吗？")) {

                    var flag = false;
                    //判断是否有选中条目
                    var cbs = document.getElementsByName("tid");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            //有一个条目选中了
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {//有条目被选中
                        //表单提交
                        document.getElementById("form").submit();
                    }

                }

            }
            //1.获取第一个cb
            document.getElementById("firstCb").onclick = function () {
                //2.获取下边列表中所有的cb
                var cbs = document.getElementsByName("tid");
                //3.遍历
                for (var i = 0; i < cbs.length; i++) {
                    //4.设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;

                }

            }


        }
    </script>
</head>
<body>
<div class="container" onload="startTime()">
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
            <h3 style="text-align: center">主题信息列表</h3>
            <div style="float: left;">

                <form class="form-inline" action="${pageContext.request.contextPath}/findTopicByPageServlet"
                      method="post">
                    <div class="form-group">
                        <label for="exampleInputName2">主题</label>
                        <input type="text" name="topicname" value="${condition.topicname[0]}" class="form-control"
                               id="exampleInputName2">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </div>
            <div style="float: right;margin: 5px;">
                <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
                <a href="${pageContext.request.contextPath}/newspages/admin.jsp">
                    <button type="button" class="btn btn-default">返回上一层</button>
                </a>
            </div>
            <form id="form" action="${pageContext.request.contextPath}/delTopicSelectedServlet" method="post">
                <table border="1" class="table table-bordered table-hover">
                    <tr class="success">
                        <th><input type="checkbox" id="firstCb"></th>
                        <th>编号</th>
                        <th>主题</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach items="${pb.list}" var="topic" varStatus="s">
                        <tr>
                            <td><input type="checkbox" name="tid" value="${topic.id}"></td>
                            <td>${s.count}</td>
                            <td>${topic.topicname}</td>
                            <td><a class="btn btn-default btn-sm"
                                   href="${pageContext.request.contextPath}/findTopicByIdServlet?id=${topic.id}">修改</a>&nbsp;
                                <a class="btn btn-default btn-sm" href="javascript:deleteTopic(${topic.id});">删除</a>
                            </td>
                        </tr>

                    </c:forEach>


                </table>
            </form>

            <div>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${pb.currentPage == 1}">
                        <li class="disabled">
                            </c:if>

                            <c:if test="${pb.currentPage != 1}">
                        <li>
                            </c:if>


                            <a href="${pageContext.request.contextPath}/findTopicByPageServlet?currentPage=${pb.currentPage - 1}&rows=10&topicname=${condition.topicname[0]}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>


                        <c:forEach begin="1" end="${pb.totalPage}" var="i">


                            <c:if test="${pb.currentPage == i}">
                                <li class="active"><a
                                        href="${pageContext.request.contextPath}/findTopicByPageServlet?currentPage=${i}&rows=10&topicname=${condition.topicname[0]}">${i}</a>
                                </li>
                            </c:if>
                            <c:if test="${pb.currentPage != i}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/findTopicByPageServlet?currentPage=${i}&rows=10&topicname=${condition.topicname[0]}">${i}</a>
                                </li>
                            </c:if>

                        </c:forEach>


                        <li>
                            <a href="${pageContext.request.contextPath}/findTopicByPageServlet?currentPage=${pb.currentPage + 1}&rows=10&topicname=${condition.topicname[0]}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

                    </ul>
                </nav>
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
