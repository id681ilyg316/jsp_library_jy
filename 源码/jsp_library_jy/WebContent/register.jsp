<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/3
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
    </script>

</head>
<body>
	<div id="tab">
		<ul class="tab_menu">
			<li class="selected">读者注册</li>
		</ul>
		<div class="tab_box">
			<%--读者注册开始--%>
			<div>
				<div class="reader_error_box"></div>
				<form
					action="/account.do?method=register&type=reader"
					class="reader_login_error" method="post">
					<div id="username">
						<label>用户名：</label> <input type="text" id="reader_username_hide"
							name="username" value="输入用户名" nullmsg="用户名不能为空" datatype="s2-20"
							errormsg="用户名在2~20个字符之间" readermsg="用户名验证通过" />
					</div>
					<div id="password">
						<label>密&nbsp;&nbsp;&nbsp;码：</label> <input type="text"
							id="reader_password_hide" name="password" value="输入密码"
							nullmsg="密码不能为空" datatype="*6-16" errormsg="密码范围在6~16位之间"
							readermsg="密码验证通过" />
					</div>
					<div id="repassword">
						<label>重复密码：</label> <input type="text"
							id="reader_repassword_hide"  value="输入密码" name="repassword"
							nullmsg="密码不能为空" datatype="*6-16" errormsg="密码范围在6~16位之间"
							readermsg="密码验证通过" />
					</div>
					<div id="remember">
						<input type="checkbox" name="remember"> <label>记住密码</label>
					</div>
					<div id="button">
						<button type="submit">注册</button>

					</div>

					<a href="login.jsp">去登录</a>
				</form>
			</div>
			<%--读者注册结束--%>

		</div>
		<%--管理员注册结束--%>
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
        // 读者注册信息验证
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
        
        $("#reader_repassword_hide").focusout(function () {
            var username = $(this).val();
            if (username == "") {
                $(this).val('输入密码');
            }
            var password = $("#reader_password_hide").val();
            var repassword = $("#reader_repassword_hide").val();
            if(password!=repassword){
            	 $(this).val("两次密码不一致");
            }
        });
        $("#reader_repassword_hide").focus(function () {
            var username = $(this).val();
            if (username == "输入密码") {
                $(this).val('');
            }
           
            
        });
 		
        $(".reader_login_error").Validform({
            tiptype: function (msg, o, cssctl) {
                var objtip = $(".reader_error_box");
                cssctl(objtip, o.type);
                objtip.text(msg);
            }
        })
        // 读者注册验证结束
     
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
${ msg }
