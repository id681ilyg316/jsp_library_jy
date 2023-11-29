<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/8
  Time: 14:14
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
            background: url("/images/bg-3.jpg") no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
    <div class="layui-container-box" style="width: 80%;margin: 50px auto 0;border: 1px solid #E6E6E6;background: url(../images/bg.jpg)">
        <fieldset class="layui-elem-field layui-field-title" style="padding-left: 510px;">
            <legend>读者信息表</legend>
        </fieldset>
        <table class="layui-hide" id="readers" lay-filter="readers"></table>
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
            elem:'#readers',
            url:'/reader/reader.do?method=findAll',//数据接口
            title:'读者信息表',
            page:true,//开启分页
            cols:[[//表头
                {field:'id',title:'ID',sort:true,fixed:"left"},
                {field:'username',title:'用户名'},
                {field:'password',title:'密码'},
                {field:'name',title:'真实名字'},
                {field:'tel',title:'电话号码'},
                {field:'cardid',title:'证件编号'},
                {field:'gender',title:'性别'},
                {field:'right', title: '操作', toolbar:"#barDemo"}
            ]]
        });
        table.on('tool(readers)',function (obj) {
            var data = obj.data;
            if (obj.event == 'edit'){
                window.location.href = "/reader/reader.do?method=preEdit&readerId=" + data.id;
            } else if (obj.event == 'del'){
                layer.confirm('确定删除该条数据吗？', function(index){
                    window.location.href = "/reader/reader.do?method=delete&readerId=" + data.id;
                    layer.close(index);
                });
            }
        })
    });
</script>
