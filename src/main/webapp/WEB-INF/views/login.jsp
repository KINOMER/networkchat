<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("APP_PATH", request.getContextPath()); %>
<html lang="en">
<head>
	<meta charset="utf-8">
	
	<title>用户登录</title>
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/css/register-login.css">
	<link rel="icon" type="image/png"
		href="${APP_PATH }/static/plugins/assets/i/favicon.png">
	
</head>
<body>
<div id="box"></div>
<div class="cent-box register-box">
	<div class="cent-box-header">
		<h1>用户登录</h1>
		<h2 class="sub-title"></h2>
	</div>

	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="${APP_PATH }/login" class="active">登录</a>
				<a href="${APP_PATH }/regist">注册</a>
				<div class="slide-bar"></div>				
			</div>
		</div>
		<div>
			<p id="warnMsg"></p>
		</div>
		<div class="login form">
			<form id="login_form" action="${APP_PATH }/login" method="post" >
				<div class="group">
					<div class="group-ipt user" >
						<input type="name" name="username" id="username" class="ipt" 
							onfocus="txtFocus('user')" onblur="txtBlur('user')" placeholder="输入登录用户名" required>
					</div>
					<div class="group-ipt password">
						<input type="password" name="password" id="password" class="ipt" 
							onfocus="txtFocus('password')" onblur="txtBlur('password')" placeholder="输入登录密码" required>
					</div>
					<div class="group-ipt verify">
						<input type="text" name="valistr" id="verify" class="ipt" 
							onfocus="txtFocus('verify')" onblur="txtBlur('verify')" placeholder="输入验证码" required>
						<img src="${APP_PATH }/ValiImg" class="imgcode">
					</div>
				</div>
				<div class="remember clearfix">
					<label class="remember-me">
						<input type="checkbox" name="rememberName" value="OK" id="rememberName" checked="checked"
							style="'cursor': 'pointer';"/>
						<span>记住用户名</span>
					</label>		
				</div>
			</form>
		</div>
		<div class="button">
			<button type="submit" class="login-btn register-btn" id="button">登录</button>
		</div>
	</div>
</div>



<script src='${APP_PATH }/static/js/login/particles.js' type="text/javascript"></script>
<script src='${APP_PATH }/static/js/login/background.js' type="text/javascript"></script>
<script src='${APP_PATH }/static/js/login/jquery.min.js' type="text/javascript"></script>
<script src='${APP_PATH }/static/js/login/layer/layer.js' type="text/javascript"></script>
<script src='${APP_PATH }/static/js/validate.login.js' type="text/javascript"></script>
<script type="text/javascript">
	
	window.onload = function(){
		var username = decodeURI("${cookie.remUsername.value}");
		$("#username").val(username);
	} 
	$('#verify').on('keypress',function(event){ 
        if(event.keyCode == 13){  
        	login();
        }  
    });
	function login(){
		var username = $("#username").val();
		var password = $("#password").val();
		var valistr = $("#verify").val();
		
		
		if(username == ""){
			warnMsg("请输入用户名！");
		}else if(password == ""){
			warnMsg("请输入用户密码！");
		}else if(valistr == ""){
			warnMsg("请输入验证码！");
		}else{
			$("#warnMsg").html("").css({});
			$.ajax({
				url:"login",
				type:"post",
				data:$("#login_form").serialize(),
				success:function(data){
					//eval("var result=" + event.msg);
					var result = eval('(' + data + ')'); 
					var msg = result.msg;
					if(msg == '登陆成功！'){
					//	alert(msg); 已解决
						window.location.href = "index";
					}else{
						warnMsg(msg);
					}
				},
			});
		}
	}
	
</script>
<script>
	
</script>
</body>
</html>