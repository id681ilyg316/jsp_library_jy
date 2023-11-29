<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/9
  Time: 8:24
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
        <fieldset class="layui-elem-field layui-field-title" style="padding-left: 500px">
            <legend style="padding-top: 15px;font-weight: bolder;">还书审核</legend>
        </fieldset>
        <table class="layui-hide" id="return-book" lay-filter="return-book"></table>
        <script id="toolBar" type="text/html">
             <a class="layui-btn layui-btn-xs" lay-event="agree">同意归还</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="disagree">拒绝归还</a>
        </script>
    </div>
</body>
</html>
<script type="text/javascript">
    layui.use('table',function () {
        var table = layui.table;
        table.render({
            elem:'#return-book',
            url:'/admin/bookAdmin.do?method=findAll&type=return',//数据接口
            page:true,//开启分页
            cols:[[//表头
                {field:'id',title:'ID',sort:true,fixed:"left"},
                {field:'book',title:'书名',templet:function (d) { return d.book.name }},
                {field:'/reader',title:'借阅人',templet:function (d) { return d.reader.name }},
                {field:'returntime',title:'归还时间'},
                {field:'bookAdmin',title:'审核人',templet:function (d) { return d.bookAdmin == null ? '' : d.bookAdmin.username }},
                {field:'state',title:'状态',templet:"#stateTpl"},
                {field:'right', title: '操作', toolbar:"#toolBar"}
            ]]
        });
        table.on('tool(return-book)',function (obj) {
            var data = obj.data;
            if (obj.event == 'agree'){
                window.location.href = "/admin/bookAdmin.do?method=auditing&type=return&returnId=" + data.id + "&result=agree";
            } else if (obj.event == 'disagree'){
                window.location.href = "/admin/bookAdmin.do?method=auditing&&type=return&returnId=" + data.id + "&result=disagree";
            }
        })
    });
</script>
<script type="text/html" id="stateTpl">
    {{# if (d.state === 4) { }}
         <span>归还审核中</span>
    {{# } else if(d.state === 5) { }}
        <span>归还失败</span>
    {{# } else { }}
        <span>已归还</span>
    {{# } }}
</script>