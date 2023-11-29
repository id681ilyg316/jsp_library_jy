<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/3
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆图书借阅管理系统</title>
    <%--<link rel="stylesheet" type="text/css" href="css/login-bak.css" media="all">--%>
    <link rel="stylesheet" type="text/css" href="css/login.css" media="all">
    <script type="text/javascript" src="js/jQuery1.7.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/jquery1.42.min.js"></script>
    <script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
    <script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var $tab_li = $("#tab ul li");
            $tab_li.hover(function () {
                $(this).addClass('selected').siblings().removeClass('selected');
                var index = $tab_li.index(this);
                $('div.tab_box > div').eq(index).show().siblings().hide();
            })
        })
        
        function check()
{
	if(document.form1.username.value=="" || document.form1.pwd.value=="" || document.form1.pagerandom.value=="")
	{
		alert('请输入完整');
		return false;
	}
}

        function loadimage1(){ 
        	document.getElementById("randImage1").src = "image.jsp?"+Math.random(); 
        	} 
        
        function loadimage2(){ 
        	document.getElementById("randImage2").src = "image.jsp?"+Math.random(); 
        	} 

    </script>

</head>
<body>
<div id="tab">
    <ul class="tab_menu">
        <li class="selected">读者登录</li>
        <li>管理员登录</li>
    </ul>
    <div class="tab_box">
        <%--读者登录开始--%>
        <div>
            <div class="reader_error_box"></div>
            <form action="/account.do?method=login&type=reader" class="reader_login_error" method="post">
                <div id="username">
                    <label>用户名：</label>
                    <input type="text" id="reader_username_hide" name="username" value="输入用户名" nullmsg="用户名不能为空"
                           datatype="s2-20" errormsg="用户名在2~20个字符之间" readermsg="用户名验证通过"/>
                </div>
                <div id="password">
                    <label>密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="text" id="reader_password_hide" name="password" value="输入密码" nullmsg="密码不能为空"
                           datatype="*6-16"rrormsg="密码范围在6~16位之间" readermsg="密码验证通过"/>
                </div>
                <div id="code">
                    <label>验证码：</label>
                    <input type="text" id="reader_code_hide" name="randImage1" value="输入验证码" nullmsg="验证码不能为空！"
                           datatype="*4-4" errormsg="验证码有4位数！" readermsg="验证码验证通过！"/>
                    <a href="javascript:loadimage1();"><img alt="看不清请点我！"  id="randImage1" src="image.jsp" width="60" height="20" border="1" align="absmiddle"> </a>
                </div>
                <div id="remember">
                    <input type="checkbox" name="remember">
                    <label>记住密码</label>
                </div>
                <div id="button">
                    <button type="submit">登录</button>
                   
                </div>
                
               
            </form>
             <a href="/register.jsp">读者注册</a>
        </div>
        <%--读者登录结束--%>
        <%--管理员登录开始--%>
        <div class="hide">
            <div class="admin_error_box"></div>
            <form action="/account.do?method=login&type=bookadmin" class="admin_login_error" method="post">
                <div id="username">
                    <label>用户名：</label>
                    <input type="text" id="admin_username_hide" name="username" value="输入用户名" nullmsg="用户名不能为空"
                           datatype="s2-20" errormsg="用户名在2~20个字符之间" readermsg="用户名验证通过"/>
                </div>
                <div id="password">
                    <label>密&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="text" id="admin_password_hide" name="password" value="输入密码" nullmsg="密码不能为空"
                           datatype="*6-16" errormsg="密码范围在6~16位之间" readermsg="密码验证通过"/>
                </div>
                <div id="code">
                    <label>验证码：</label>
                    <input type="text" id="admin_code_hide"  name="randImage2"  value="输入验证码" nullmsg="验证码不能为空！"
                           datatype="*4-4" errormsg="验证码有4位数！" readermsg="验证码验证通过！"/>
                   <a href="javascript:loadimage2();"><img alt="看不清请点我！"id="randImage2" src="image.jsp" width="60" height="20" border="1" align="absmiddle"> </a>
                </div>
                <div id="remember">
                    <input type="checkbox" name="remember">
                    <label>记住密码</label>
                </div>
                <div id="button">
                    <button type="submit">登录</button>
                </div>
            </form>
        </div>
    </div>
    <%--管理员登录结束--%>
</div>
</div>
<div class="screenbg">
    <ul>
        <li><a href="javascript:;"><img src="images/0.jpg"></a></li>
        <li><a href="javascript:;"><img src="images/1.jpg"></a></li>
        <li><a href="javascript:;"><img src="images/2.jpg"></a></li>
    </ul>
</div>
</body>
</html>
<script type="text/javascript">
    $(function () {
        // 读者登录信息验证
        $("#reader_username_hide").focus(function () {
            var username = $(this).val();
            if (username == "输入用户名") {
                $(this).val('');
            }
        });
        $("#reader_username_hide").focusout(function () {
            var username = $(this).val();
            if (username == "") {
                $(this).val('输入用户名');
            }
        });
        $("#reader_password_hide").focus(function () {
            var username = $(this).val();
            if (username == "输入密码") {
                $(this).val('');
            }
        });
        $("#reader_password_hide").focusout(function () {
            var username = $(this).val();
            if (username == "") {
                $(this).val('输入密码');
            }
        });
        $("#reader_code_hide").focus(function () {
            var username = $(this).val();
            if (username == "输入验证码") {
                $(this).val('');
            }
        });
        $("#reader_code_hide").focusout(function () {
            var username = $(this).val();
            if (username == "") {
                $(this).val('输入验证码');
            }
        });
        $(".reader_login_error").Validform({
            tiptype: function (msg, o, cssctl) {
                var objtip = $(".reader_error_box");
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        })
        // 读者登录验证结束
        // 管理员登录验证开始
        $("#admin_username_hide").focus(function () {
            var username = $(this).val();
            if (username == "输入用户名") {
                $(this).val('');
            }
        });
        $("#admin_username_hide").focusout(function () {
            var username = $(this).val();
            if (username == "") {
                $(this).val('输入用户名');
            }
        });
        $("#admin_password_hide").focus(function () {
            var username = $(this).val();
            if (username == "输入密码") {
                $(this).val('');
            }
        });
        $("#admin_password_hide").focusout(function () {
            var username = $(this).val();
            if (username == "") {
                $(this).val('输入密码');
            }
        });
        $("#admin_code_hide").focus(function () {
            var username = $(this).val();
            if (username == "输入验证码") {
                $(this).val('');
            }
        });
        $("#admin_code_hide").focusout(function () {
            var username = $(this).val();
            if (username == "") {
                $(this).val('输入验证码');
            }
        });
        $(".admin_login_error").Validform({
            tiptype: function (msg, o, cssctl) {
                var objtip = $(".admin_error_box");
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        })
        // 管理员登录验证结束
    })
</script>
<script type="text/javascript">
    $(function(){
        $(".screenbg ul li").each(function(){
            $(this).css("opacity","0");
        });
        $(".screenbg ul li:first").css("opacity","1");
        var index = 0;
        var t;
        var li = $(".screenbg ul li");
        var number = li.size();
        function change(index){
            li.css("visibility","visible");
            li.eq(index).siblings().animate({opacity:0},3000);
            li.eq(index).animate({opacity:1},3000);
        }
        function show(){
            index = index + 1;
            if(index<=number-1){
                change(index);
            }else{
                index = 0;
                change(index);
            }
        }
        t = setInterval(show,8000);
        //根据窗口宽度生成图片宽度
        var width = $(window).width();
        $(".screenbg ul img").css("width",width+"px");
    });
</script>
${msg}