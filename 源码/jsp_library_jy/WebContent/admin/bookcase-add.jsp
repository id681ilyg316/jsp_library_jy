<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/8
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书编辑页面</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/book.css">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <style>
        body {
            background: url("/images/bg-3.jpg") no-repeat;
            background-size: cover;
        }

    </style>
</head>
<body>
<div class="layui-container-form-box" style="width: 500px;background-color: #cccccc;height: 300px">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>图书类别信息编辑</legend>
    </fieldset>
    <form action="/admin/bookAdmin.do?method=bookCase&type=add" class="layui-form" style="margin-left: 20px;">

        <div class="layui-form-item">
            <label class="layui-form-label">图书类别</label>
            <div class="layui-input-block">
                <input type="text" name="name"  lay-verify="name" autocomplete="off" placeholder="请输入类别名称" class="layui-input">
            </div>
        </div>



        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1" style="margin: 20px 0px 20px 20px">提交</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    layui.use('form',function () {
        var form = layui.form;
        form.verify({
            name:function (value) {
                if(value.length == 0){
                    return '图书类别名称不能为空';
                }
            }
        })
    })
</script>
