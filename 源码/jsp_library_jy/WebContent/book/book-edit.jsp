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
            background: url("/images/bg-2.jpg") no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
    <div class="layui-container-form-box" style="width: 500px;background-color: #cccccc">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>图书信息编辑</legend>
        </fieldset>
        <form action="/book/book.do?method=edit" class="layui-form" style="margin-left: 20px">

            <div class="layui-form-item">
                <label class="layui-form-label">编号</label>
                <div class="layui-input-block">
                    <input readonly name="id" value="${requestScope.book.id}" lay-verify-="" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">书名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" value="${requestScope.book.name}" lay-verify-="" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">作者</label>
                <div class="layui-input-block">
                    <input type="text" name="author" value="${requestScope.book.author}" lay-verify-="" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">出版社</label>
                <div class="layui-input-block">
                    <input type="text" name="publish" value="${requestScope.book.publish}" lay-verify-="" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">页码</label>
                <div class="layui-input-block">
                    <input type="text" name="pages" value="${requestScope.book.pages}" lay-verify-="" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">价格</label>
                <div class="layui-input-block">
                    <input type="text" name="price" value="${requestScope.book.price}" lay-verify-="" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图书分类</label>
                <div class="layui-input-block">
                    <select name="bookCaseId">
                        <c:forEach items="${bookCaseList}" var="bookCase">
                            <option value="${bookCase.id}"
                                    <c:if test="${requestScope.book.bookCase.id == bookCase.id}">
                                        selected
                                    </c:if>
                            >${bookCase.name}</option>
                        </c:forEach>
                    </select>
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
    })
</script>
