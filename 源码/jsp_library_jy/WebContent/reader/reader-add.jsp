<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书馆图书借阅管理系统</title>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/css/book.css">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <style>
        body {
            background: url("/images/bg-3.jpg") no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
<div class="layui-container-box" style="width: 500px;background-color: #cccccc;">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>添加读者信息</legend>
    </fieldset>
    <form action="/reader/reader.do?method=add" class="layui-form" style="margin-left: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" name="password" lay-verify="password" autocomplete="off" placeholder="请输入密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入真实姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-block">
                <input type="text" name="tel" lay-verify="required|tel" autocomplete="off" placeholder="请输入电话" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">证件编号</label>
            <div class="layui-input-block">
                <input type="text" name="cardid" lay-verify="required|cardid" autocomplete="off" placeholder="请输入证件编号" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="gender" value="男" title="男" checked>
                <input type="radio" name="gender" value="女" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="position: relative;">
                <button class="layui-btn" lay-submit="" lay-filter="demo1" style="width: 80px;position:absolute; left: -150px;bottom: -5px">立即提交</button>
                <button type="reset" class="layui-btn" style="width: 80px;position: absolute; bottom: -5px;right: 120px">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    layui.use('form',function () {
        var form = layui.form;
        //自定义验证规则
        form.verify({
            // username: function(value){
            //     if(value.length == 0){
            //         return "用户名不能为空";
            //     }
            // },
            username:[/^[A-Za-z]{2,20}$/,"用户名必须是2-20个英文字符"],
            password: [/^[A-Za-z0-9]+$/,"密码必须由数字和字母组成"],
            name: [/^[\u4e00-\u9fa5]+$/,"请输入正确的姓名"],
            tel: [/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,"请输入正确的电话"],
            cardid: [/^\d{15}|\d{}18$/,"请输入正确的证件编号"]
        })
    })
</script>
