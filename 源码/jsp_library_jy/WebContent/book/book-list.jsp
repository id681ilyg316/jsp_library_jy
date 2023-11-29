<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆图书借阅管理系统</title>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/css/book.css">
    <script type="text/javascript" src="/layui/layui.js"></script>
    <script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
    
    <style>
        .layui-layout-body {
            background: url("/images/bg-4.jpg") no-repeat;
            background-size: cover;
        }
        .layui-elem-field {
            font-weight: bolder;
        }

    </style>
</head>
<body class="layui-layout-body">
    <div class="layui-container-box" style="width:80%;background: url(../images/bg.jpg )">
        <fieldset class="layui-elem-field layui-field-title" >
            <legend>图书信息表</legend>
        </fieldset>
          <div class="demoTable">
 搜索图书：
 <div class="layui-inline">
 <input class="layui-input" name="keyword" id="demoReload" autocomplete="off">
 </div>
 <button class="layui-btn" data-type="reload">搜索</button>
</div>
        
        <table class="layui-hide" id="book-list" lay-filter="book-list" ></table>

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
        	 id: 'testReload',
            elem:'#book-list',
            url:'/book/book.do',//数据接口
            title:'图书信息表',
            page:true,//开启分页
            cols:[[//表头
                {field:'id',title:'ID',sort:true,fixed:"left"},
                {field:'name',title:'书名'},
                {field:'author',title:'作者'},
                {field:'pages',title:'页码'},
                {field:'price',title:'价格'},
                {field:'publish',title:'出版社'},
                {field:'bookCase',title:'类别',templet:function (d) {return d.bookCase == null ? '' : d.bookCase.name }},
                {field:'right', title: '操作', toolbar:"#barDemo"}
            ]]
        });
        table.on('tool(book-list)',function (obj) {
            var data = obj.data;
            if (obj.event == 'edit'){
                window.location.href = "/book/book.do?method=preEdit&bookId=" + data.id;
            } else if (obj.event == 'del'){
                layer.confirm('确定删除该条数据吗？', function(index){
                    window.location.href = "/book/book.do?method=delete&bookId=" + data.id;
                    layer.close(index);
                });
            }
        })
    });
    
    $('.layui-btn').click(function () {
  	  var table = layui.table;
  	  var inputVal = $('.layui-input').val();
  	  table.reload('testReload', {
  		
  	  url: '/book/book.do'
  	  // ,methods:"post"
  	  ,request: {
  	   pageName: 'page' //页码的参数名称，默认：page
  	   ,limitName: 'limit' //每页数据量的参数名，默认：limit
  	  }
  	  ,where: {
  	   query : inputVal
  	  }
  	  ,page: {
  	   curr: 1
  	  }
  	  });
    })
</script>
