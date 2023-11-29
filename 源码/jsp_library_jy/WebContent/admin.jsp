<%--
  Created by IntelliJ IDEA.
  User: southwind
  Date: 2018/10/23
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>图书馆图书借阅管理系统</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#book_manage").click(function(){
                $("iframe").attr("src","/book/book-list.jsp");
            });
            $("#book_add").click(function(){
                $("iframe").attr("src","/book/book.do?method=preAdd");
            });
            $("#reader_add").click(function(){
                $("iframe").attr("src","/reader/reader-add.jsp");
            });
            $("#reader_manage").click(function(){
                $("iframe").attr("src","/reader/reader-list.jsp");
            });
            $("#borrow_manage").click(function(){
                $("iframe").attr("src","/admin/borrow-manage.jsp");
            });
            $("#book_count").click(function(){
                $("iframe").attr("src","/admin/data-book.jsp");
            });
            $("#book_count2").click(function(){
                $("iframe").attr("src","/admin/data-book-pie.jsp");
            });
            $("#borrow_back").click(function(){
                $("iframe").attr("src","/admin/return-manage.jsp");
            });
            $("#book-class-manage").click(function () {
                $("iframe").attr("src","/admin/bookcase-list.jsp");
            });
            $("#book-class-add").click(function () {
                $("iframe").attr("src","/admin/bookcase-add.jsp");
            });
        })
    </script>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部菜单开始 -->
    <div class="layui-header">
        <div class="layui-logo">Library后台管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item layui-this"><a href="javascript:;">控制台</a></li>
            <%--<li class="layui-nav-item"><a href="javascript:;">信息管理</a></li>--%>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="images/img.jpeg" class="layui-nav-img">
                    ${bookAdmin.username}
                </a>
                <%--<dl class="layui-nav-child">--%>
                    <%--<dd><a href="">基本资料</a></dd>--%>
                    <%--<dd><a href="">安全设置</a></dd>--%>
                <%--</dl>--%>
            </li>
            <li class="layui-nav-item"><a href="/account.do?method=logout">退出</a></li>
        </ul>
    </div>

    <!-- 顶部菜单结束 -->

    <!-- 左侧菜单开始 -->
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">图书管理</a>
                    <dl class="layui-nav-child">
                        <dd class="layui-this"><a id="book_manage" href="javascript:void(0)">查询图书</a></dd>
                        <dd><a id="book_add" href="javascript:void(0)">添加图书</a></dd>
                        <dd><a id="borrow_manage" href="javascript:void(0)">借阅审核</a></dd>
                        <dd><a id="borrow_back" href="javascript:void(0)">还书审核</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a id="reader_manage" href="javascript:void(0)">查询用户</a></dd>
                        <dd><a id="reader_add" href="javascript:void(0)">添加用户</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">图书类别管理</a>
                    <dl class="layui-nav-child">
                        <dd><a id="book-class-manage" href="javascript:void(0)">查询类别</a></dd>
                        <dd><a id="book-class-add" href="javascript:void(0)">添加类别</a></dd>
                    </dl>
                </li>
                
                <!-- <li class="layui-nav-item">
                    <a href="javascript:;">数据管理</a>
                    <dl class="layui-nav-child">
                        <dd><a id="book_count" href="javascript:void(0)">柱状图</a></dd>
                        <dd><a id="book_count2" href="javascript:void(0)">饼图</a></dd>
                        <dd><a id="book_export" href="/admin/bookAdmin.do?method=exportDataInfo&type=book">导出图书数据</a></dd>
                    </dl>
                </li> -->
            
            </ul>
        </div>
    </div>
    <!-- 左侧菜单结束 -->

    <!-- 主体开始 -->
    <div class="layui-body">

        <iframe src="/book/book-list.jsp" style="width: 100%;height: 100%;border: 0px"></iframe>

    </div>
    <!-- 主体结束 -->

    <!-- 底部开始 -->
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 图书馆图书借阅管理系统
    </div>
    <!-- 底部结束 -->
</div>
<script>
    //二级菜单联动
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
