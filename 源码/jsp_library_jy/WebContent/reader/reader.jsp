<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/31
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆图书借阅管理系统</title>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="/css/reader.css">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <style>
        body {
            background: url("/images/0.jpg");
            background-size: cover;
        }
    </style>
</head>
<body>
    <div class="layui-container-box" style="background: url(../images/bg.jpg);background-size: cover">
        <div style="margin-left: 500px; width: 620px;">
            <a href="/index.jsp">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/admin/bookAdmin.do?method=exportDataInfo&type=reader">导出数据</a>&nbsp;&nbsp;|&nbsp;&nbsp;欢迎回来！<a href="/reader/reader.jsp">${sessionScope.reader.name}</a><a href="/account.do?method=logout"><button class="layui-btn layui-btn-warm layui-btn-radius">注销</button></a>
        </div>
        <table class="layui-hide" id="borrow" lay-filter="borrow"></table>
        <script type="text/html" id="barDemo">
                {{# if (d.state === 1) { }}
                     <a class="layui-btn layui-btn-xs" lay-event="return">还书</a>
                {{# } else { }}
                      <a id="disabled" class="layui-btn layui-btn-xs layui-btn-disabled">还书</a>
                {{# } }}

        </script>
    </div>
</body>
<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#borrow',
            url: '/reader/reader.do',
            // cellMinWidth: 80,//全局定义常规单元格的最小宽度，layui 2.2.1 新增属性
            cols: [[
                {field: 'id',  title: 'ID', sort: true},
                {field: 'book',  title: '图书',templet:function (d) {return d.book.name }},
                {field: '/reader',  title: '读者',templet:function (d) { return d.reader.username }},
                {field: 'borrowtime',  title: '借书时间', sort: true},
                {field: 'returntime',  title: '还书时间'},
                {field: 'state',  title: '状态',templet:"#stateTpl"},
                {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
            , page: true
        });
        table.on('tool(borrow)',function (obj) {
            var data = obj.data;
            if(obj.event === 'return'){
                window.location.href="/reader/reader.do?method=return&borrowId="+data.id;
            }
        })
        $('#disabled').setAttribute("disabled",disabled);
        $('#disabled').on('click',function () {
            return;
        })
    });
</script>
<script type="text/html" id="stateTpl">
    {{# if (d.state === 0) { }}
         <span>借阅审核中</span>
    {{# } else if(d.state === 1) { }}
        <span>借阅成功</span>
    {{# } else if(d.state === 2) { }}
        <span>借阅失败</span>
    {{# } else if(d.state === 3) { }}
        <span>归还成功</span>
    {{# } else if(d.state === 4) { }}
        <span>归还审核中</span>
    {{# } else { }}
        <span>归还失败</span>
    {{# } }}

</script>
</html>
