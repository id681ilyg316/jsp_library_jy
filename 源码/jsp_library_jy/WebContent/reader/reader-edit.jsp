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
<div class="layui-container-form-box" style="width: 500px;background-color: #cccccc">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>读者信息编辑</legend>
    </fieldset>
    <form action="/reader/reader.do?method=edit" class="layui-form" style="margin-left: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">用户编号</label>
            <div class="layui-input-block">
                <input type="text" name="id" value="${requestScope.reader.id}"  autocomplete="off" placeholder="请输入" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" lay-verify="username" value="${requestScope.reader.username}"  autocomplete="off" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" name="password" value="${requestScope.reader.password}" lay-verify="password" autocomplete="off" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" value="${requestScope.reader.name}" lay-verify="name" autocomplete="off" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话号码</label>
            <div class="layui-input-block">
                <input type="text" name="tel" value="${requestScope.reader.tel}" lay-verify="tel" autocomplete="off" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">证件编号</label>
            <div class="layui-input-block">
                <input type="text" name="cardid" value="${requestScope.reader.cardid}" lay-verify="cardid" autocomplete="off" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <c:if test="${requestScope.reader.gender == '男'}">
                    <input type="radio" name="gender" value="男" title="男" checked>
                    <input type="radio" name="gender" value="女" title="女">
                </c:if>
                <c:if test="${requestScope.reader.gender == '女'}">
                    <input type="radio" name="gender" value="男" title="男">
                    <input type="radio" name="gender" value="女" title="女" checked>
                </c:if>
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
        var element = layui.element;
        //自定义验证规则
        form.verify({
            username: function(value){
                if(value.length == 0){
                    return "用户名不能为空";
                }
            },
            password: [/^[A-Za-z0-9]+$/,"密码必须由数字和字母组成"],
            name: [/^[\u4e00-\u9fa5]+$/,"请输入正确的姓名"],
            tel: [/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,"请输入正确的电话"],
            cardid: [/^\d{15}|\d{}18$/,"请输入正确的证件编号"]
        })
    })
</script>
