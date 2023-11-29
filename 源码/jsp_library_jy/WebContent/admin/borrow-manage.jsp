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
            <legend style="padding-top: 15px;color: #0C0C0C;font-weight: bolder">图书借阅审核信息</legend>
        </fieldset>
        <table class="layui-hide" id="borrows-manage" lay-filter="borrows-manage" ></table>
        <script type="text/html" id="barDemo">
            {{# if (d.state === 2) { }}
                   <a class="layui-btn layui-btn-xs layui-btn-disabled">同意</a>
                        <a class="layui-btn layui-btn-danger layui-btn-disabled" >拒绝</a>
            {{# } else { }}
                     <a class="layui-btn layui-btn-xs" lay-event="agree">同意</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="disagree">拒绝</a>
            {{# } }}
        </script>
    </div>
</body>
</html>
<script type="text/javascript">
    layui.use('table',function () {
        var table = layui.table;
        table.render({
            elem:'#borrows-manage',
            url:'/admin/bookAdmin.do?method=findAll&type=borrow',//数据接口
            page:true,//开启分页
            cols:[[//表头
                {field:'id',title:'ID',sort:true,fixed:"left"},
                {field:'book',title:'书名',templet:function (d) { return d.book.name }},
                {field:'/reader',title:'借阅人',templet:function (d) { return d.reader.name }},
                {field:'borrowtime',title:'借阅时间'},
                {field:'bookAdmin',title:'审核人',templet:function (d) { return d.bookAdmin == null ? '' : d.bookAdmin.username }},
                {field:'state',title:'状态',templet:"#stateTpl"},
                {field:'right', title: '操作', toolbar:"#barDemo"}
            ]]
        });
        table.on('tool(borrows-manage)',function (obj) {
            var data = obj.data;
            if (obj.event == 'agree'){
                window.location.href = "/admin/bookAdmin.do?method=auditing&type=borrow&borrowId=" + data.id + "&result=agree";
            } else if (obj.event == 'disagree'){
                window.location.href = "/admin/bookAdmin.do?method=auditing&&type=borrow&borrowId=" + data.id + "&result=disagree";
            }
        })
    });
</script>
<script type="text/html" id="stateTpl">
    {{# if (d.state === 0) { }}
         <span>未审核</span>
    {{# } else if(d.state === 1) { }}
        <span>审核通过</span>
    {{# } else if(d.state === 2) { }}
        <span>审核未通过</span>
    {{# } else { }}
        <span>已归还</span>
    {{# } }}
</script>