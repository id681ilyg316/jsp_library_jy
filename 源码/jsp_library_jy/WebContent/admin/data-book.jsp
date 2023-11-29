<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/9
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆图书借阅管理系统</title>
    <script src="/js/echarts.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 80%;height:70%;margin: 150px auto 0"></div>
<script type="text/javascript">
        $.ajax({
            url:'/admin/bookAdmin.do?method=getBookData&type=bar',
            type:'POST',
            dataType: "json",
            success:function (data) {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '图书借阅量图表'
                    },
                    tooltip: {},
                    legend: {
                        data: ['借阅量']
                    },
                    xAxis: {
                        data: data.name
                    },
                    yAxis: {},
                    series: [{
                        name: '借阅量',
                        type: 'bar',
                        data: data.count
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }

        })

</script>
</body>
</html>
