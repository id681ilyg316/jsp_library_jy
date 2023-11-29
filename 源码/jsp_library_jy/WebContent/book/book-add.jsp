<%--
  User: Administrator
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
            background: url("/images/bg-2.jpg") no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
    <div class="layui-container-box" style="width: 500px;background-color: #cccccc">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>添加图书信息</legend>
        </fieldset>
        <form action="/book/book.do?method=add" class="layui-form" style="margin-left: 20px">
            <div class="layui-form-item">
                <label class="layui-form-label">书名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入书名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">作者</label>
                <div class="layui-input-block">
                    <input type="text" name="author" lay-verify="author" autocomplete="off" placeholder="请输入作者" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">出版社</label>
                <div class="layui-input-block">
                    <input type="text" name="publish" lay-verify="required" autocomplete="off" placeholder="请输入出版社" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">页码</label>
                <div class="layui-input-block">
                    <input type="text" name="pages" lay-verify="required|pages" autocomplete="off" placeholder="请输入页码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">价格</label>
                <div class="layui-input-block">
                    <input type="text" name="price" lay-verify="required|price" autocomplete="off" placeholder="请输入价格" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图书类别</label>
                <div class="layui-input-block">
                    <select name="bookCaseId" lay-verify="required" lay-search>
                        <option value="" selected="selected">请选择分类</option>
                        <c:forEach items="${list}" var="bookCase">
                            <option value="${bookCase.id}">${bookCase.name}</option>
                        </c:forEach>
                    </select>
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
            name:function (value) {
                if (value.length < 1){
                    return '图书名称不能为空';
                }
            },
            author:[/[\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20}/,"作者姓名只能是中文或者英文,且不能超过20个字符"],
            pages: [/^[0-9]*$/, "请输入正确的页数"],
            price: [/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/, "请输入正确的价格"]
        })
    })
</script>
