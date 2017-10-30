<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">    
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>用户注册</title>
	<link rel="stylesheet" type="text/css" href="${APP_PATH }/static/css/register-login.css">
</head>
<body>
<div id="box"></div>
<div class="cent-box register-box">
	<div class="cent-box-header">
		<h1>用户注册</h1>
		<h2 class="sub-title">心灵的展示--Communication</h2>
	</div>

	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="${APP_PATH }/login">登录</a>
				<a href="${APP_PATH }/regist" class="active">注册</a>
				<div class="slide-bar slide-bar1"></div>				
			</div>
		</div>
		<div>
			<p id="warnMsg"></p>
		</div>
		<div class="regist form">
			<form id="regist_form" action="${APP_PATH }/regist" method="post">
				<div class="group">
					<div class="group-ipt user">
						<input type="text" name="username" id="username" class="ipt" 
							onfocus="txtFocus('user')" onblur="txtBlur('user')" placeholder="选择一个用户名" required>
					</div>
					<div class="group-ipt password">
						<input type="password" name="password" id="password" class="ipt" 
							onfocus="txtFocus('password')" onblur="txtBlur('password')" placeholder="设置登录密码" required>
					</div>
					<div class="group-ipt password1">	
						<input type="password" name="rePassword" id="password1" class="ipt" 
							onfocus="txtFocus('password1')" onblur="txtBlur('password1')" placeholder="重复密码" required>
					</div>
				</div>
			</form>
		</div>

		<div class="button">
			<button type="submit" class="login-btn register-btn" id="button">注册</button>
		</div>
	</div>
</div>



<script src='${APP_PATH }/static/js/login/particles.js' type="text/javascript"></script>
<script src='${APP_PATH }/static/js/login/background.js' type="text/javascript"></script>
<script src='${APP_PATH }/static/js/login/jquery.min.js' type="text/javascript"></script>
<script src='${APP_PATH }/static/js/login/layer/layer.js' type="text/javascript"></script>
<script src="${APP_PATH }/static/js/validate.regist.js"></script>

</body>
</html>