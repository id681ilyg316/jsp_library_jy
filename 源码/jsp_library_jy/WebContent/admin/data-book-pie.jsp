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
    <script type="text/javascript" src="/js/echarts.common.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
</head>
<body>
<div id="main" style="width: 80%;height:60%;margin: 100px auto 0"></div>
    <script type="text/javascript">
        // app.title = '坐标轴刻度与标签对齐';
        var myChart = echarts.init(document.getElementById('main'));
        $.ajax({
            url: '/admin/bookAdmin.do?method=getBookData&type=pie',
            type: 'POST',
            dataType: "JSON",
            success:function(data){
                // 指定图表的配置项和数据
                var option = {
                    backgroundColor: '#2c343c',

                    title: {
                        text: '图书借阅统计',
                        left: 'center',
                        top: 20,
                        textStyle: {
                            color: '#ccc'
                        }
                    },

                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },

                    visualMap: {
                        show: false,
                        min: 80,
                        max: 600,
                        inRange: {
                            colorLightness: [0, 1]
                        }
                    },
                    series : [
                        {
                            name:'访问来源',
                            type:'pie',
                            radius : '55%',
                            center: ['50%', '50%'],
                            data:data.sort(function (a, b) { return a.value - b.value; }),
                            roseType: 'radius',
                            label: {
                                normal: {
                                    textStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    lineStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    },
                                    smooth: 0.2,
                                    length: 10,
                                    length2: 20
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: '#c23531',
                                    shadowBlur: 200,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            },

                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function (idx) {
                                return Math.random() * 200;
                            }
                        }
                    ]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });

    </script>
    <script src="/js/echarts.min.js"></script>
</body>
</html>