<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/9
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆图书借阅管理系统</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <style>
        body {
            background: url(../images/bg-5.jpg) no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
<div class="layui-container" style="background: url(../images/bg.jpg);margin-top: 100px">
    <fieldset class="layui-elem-field layui-field-title" style="padding-left: 460px">
        <legend style="padding-top: 15px;color: #0C0C0C;font-weight: bolder">图书类别信息表</legend>
    </fieldset>
    <table class="layui-hide" id="bookCase-list" lay-filter="bookCase-list" ></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
</body>
</html>
<script type="text/javascript">
    layui.use('table',function () {
        var table = layui.table;
        table.render({
            elem:'#bookCase-list',
            url:'/admin/bookAdmin.do?method=bookCase&type=find',//数据接口
            page:true,//开启分页
            cols:[[//表头
                {field:'id',title:'ID',sort:true,fixed:"left"},
                {field:'name',title:'图书类别名称'},
                {field:'right', title: '操作', toolbar:"#barDemo"}
            ]]
        });
        table.on('tool(bookCase-list)',function (obj) {
            var data = obj.data;
            if (obj.event == 'edit'){
                window.location.href = "/admin/bookAdmin.do?method=bookCase&type=preEdit&bookCaseId=" + data.id;
            } else if (obj.event == 'del'){
               layer.confirm("确定删除该条记录吗？",function (index) {
                   window.location.href = "/admin/bookAdmin.do?method=bookCase&type=delete&bookCaseId=" + data.id;
                    layer.close(index);
               })
            }
        })
    });
</script>
