<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>WeChat在线聊天室</title>

<link rel="stylesheet"
	href="${APP_PATH }/static/plugins/layui/css/layui.css">

<meta name="description" content="">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- Bootstrap -->
<link
	href="${APP_PATH }/static/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap -->
<link rel="icon" type="image/png"
	href="${APP_PATH }/static/plugins/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${APP_PATH }/static/plugins/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet"
	href="${APP_PATH }/static/plugins/assets/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${APP_PATH }/static/plugins/assets/css/admin.css">
<link rel="stylesheet"
	href="${APP_PATH }/static/plugins/sketchpad/css/sketchpad.css">
<!--<link rel="stylesheet" type="text/css" href="plugins/sketchpad/css/normalize.css" />-->
<!--<link rel="stylesheet" type="text/css" href="plugins/sketchpad/css/htmleaf-demo.css">-->
<link rel="stylesheet" href="${APP_PATH }/static/css/common.css" />

</head>
<style>
.sketchpad {
	border: 1px solid #000;
}
</style>
<body>
	<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

	<header class="am-topbar am-topbar-inverse admin-header">
	<div class="am-topbar-brand">
		<strong>WeChat在线聊天室</strong>
	</div>

	<button
		class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: '#topbar-collapse'}">
		<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
	</button>

	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			<li class="am-dropdown" data-am-dropdown><a
				class="am-dropdown-toggle" data-am-dropdown-toggle
				href="javascript:;"> <span class="glyphicon glyphicon-user"></span>
					${userInfo.username } <span class="am-icon-caret-down"></span>
			</a>
				<ul class="am-dropdown-content">
					<li><a href="${APP_PATH }/userInfo"><span
							class="am-icon-user"></span> 资料</a></li>
					<li><a href="${APP_PATH }/userInfoSet"><span
							class="am-icon-cog"></span> 设置</a></li>
					<li><a href="#" id="out"><span class="am-icon-power-off"></span>
							退出</a></li>
				</ul></li>
		</ul>
	</div>
	</header>

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
			<div class="am-offcanvas-bar admin-offcanvas-bar">
				<ul class="am-list admin-sidebar-list">
					<li><a href="${APP_PATH }/index"><span
							class="am-icon-weixin"></span> 聊天</a></li>
					<li><a href="${APP_PATH }/userInfo" class="am-cf"><span
							class="am-icon-book"></span> 个人信息<span
							class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav'}"><span
							class="am-icon-cogs"></span> 设置 <span
							class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<ul class="am-list am-collapse admin-sidebar-sub am-in"
							id="collapse-nav">
							<li><a href="${APP_PATH }/userInfoSet" class="am-cf"><span
									class="glyphicon glyphicon-pencil"></span> 个人资料<span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
							<li><a href="system-setting.html" class="am-cf"><span
									class="am-icon-cog"></span> 系统设置<span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>

						</ul></li>
					<li><a href="help.html"><span class="am-icon-globe"></span>
							帮助</a></li>
					<li><a href="${APP_PATH }/aboutUs"><span
							class="am-icon-leaf"></span> 关于</a></li>
					<li><a href="${APP_PATH }/logOut"><span
							class="am-icon-sign-out"></span> 注销</a></li>
				</ul>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="am-icon-tag"></span> 签名
						</p>
						<p id="autograph">
							<c:if
								test="${userInfo.autograph == null || userInfo.autograph == ''}">
									你可以到个人资料中设置自己的签名.</c:if>
							<c:if
								test="${userInfo.autograph != null && userInfo.autograph != ''}">
                               		${userInfo.autograph}
                            	</c:if>
						</p>
					</div>
				</div>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="am-icon-bookmark"></span> 公告
						</p>
						<p>${anno}</p>
					</div>
				</div>
			</div>
		</div>
		<!-- sidebar end -->

		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal
							information</small>
					</div>
				</div>

				<hr />

				<div class="am-g">
					<div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
						<div class="am-panel am-panel-default">
							<div class="am-panel-bd">
								<div class="am-g">
									<div class="am-u-md-4">
										<img id="user_avator" class="am-circle" width="140"
											height="140" />
									</div>
									<div class="am-u-md-8">
										<p>你可以从本地上传头像。</p>
										<form class="am-form" id="upLoadPic_Form" 
											action="${APP_PATH}/uploadPic" enctype="multipart/form-data"
											method="POST">
											<div class="am-form-group">
												<input type="file" id="userPic" name="avator">
												<p class="am-form-help">请选择要上传的图片</p>
												<button type="button" id="uploadPic_btn"
													class="am-btn am-btn-primary am-btn-xs">
													保存
												</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">

						<form class="am-form am-form-horizontal" id="updateUserInfoForm">
							<div class="am-form-group">
								<label for="user-name" class="am-u-sm-3 am-form-label">用户名</label>
								<div class="am-u-sm-9">
									<input type="hidden" id="userId" name="userId"
										value="${userInfo.userId }"> <input type="text"
										id="username" name="username" value="${userInfo.username}"
										disabled="disabled">
									<!--<small>输入你的用户名</small>-->
								</div>
							</div>

							<div class="am-form-group">
								<label for="sex" class="am-u-sm-3 am-form-label">性别</label>
								<div class="col-sm-4">
									<select class="form-control" id="sex" name="sex">
										<option value="1" selected="selected">男</option>
										<option value="0">女</option>
									</select>
								</div>
							</div>


							<div class="am-form-group">
								<label for="age" class="am-u-sm-3 am-form-label">年龄</label>
								<div class="am-u-sm-9">
									<input type="number" id="age" name="age" min="1" max="120"
										class="form-control" value="${userInfo.age}"
										placeholder="这里输入你的年龄..."> <span class="help-block"></span>
								</div>
							</div>

							<div class="am-form-group">
								<label for="user-email" class="am-u-sm-3 am-form-label">Email</label>
								<div class="am-u-sm-9">
									<input type="email" value="${userInfo.email }" id="userEmail"
										name="email" placeholder="输入你的Email"> <span
										class="help-block"></span>
								</div>
							</div>

							<div class="am-form-group">
								<label for="user-phone" class="am-u-sm-3 am-form-label">手机号码</label>
								<div class="am-u-sm-9">
									<input type="tel" id="userPhone" value="${userInfo.phone }"
										name="phone" placeholder="输入你的手机号码"> <span
										class="help-block"></span>
								</div>
							</div>

							<div class="am-form-group">
								<label for="profile" class="am-u-sm-3 am-form-label">个人签名</label>
								<div class="am-u-sm-9">
									<textarea class="" id="profile" name="autograph" rows="2"
										placeholder="这里可以写下你的个人签名..."></textarea>
								</div>
							</div>

							<div class="am-form-group">
								<label for="user-intro" class="am-u-sm-3 am-form-label">个人简介</label>
								<div class="am-u-sm-9">
									<textarea class="" rows="5" id="userIntro" name="summary"
										placeholder="输入个人简介"></textarea>
									<small>250字以内写出你的一生...</small>
								</div>
							</div>

							<div class="am-form-group">
								<div class="am-u-sm-9 am-u-sm-push-3">
									<button type="button" class="am-btn am-btn-primary"
										id="user_update_btn">保存修改</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<footer class="admin-content-footer">
				<hr><p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed
				under MIT license.</p>
			</footer>
		</div>

	</div>



	<script type="text/javascript"
		src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript"
		src="${APP_PATH }/static/js/jquery.form.js"></script>
	<script src="${APP_PATH }/static/plugins/assets/js/jquery.min.js"></script>
	<script
		src="${APP_PATH }/static/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/static/plugins/assets/js/amazeui.min.js"></script>
	<script src="${APP_PATH }/static/plugins/assets/js/app.js"></script>
	<!---->
	<script src="${APP_PATH }/static/plugins/layui/layui.js"></script>

	<script src="${APP_PATH }/static/plugins/sketchpad/js/sketchpad.js"></script>
	<!---->
</body>


<script type="text/javascript">
		$("#uploadPic_btn").click(function(){
			$("#upLoadPic_Form").submit();
		});
		$("#userPic").change(function(){
			var avatorPath = getObjectURL(this.files[0]);
			$("#user_avator").attr("src",avatorPath);
		});
			//返回上传图片的url
		function getObjectURL(file) {
			var url = null ;
			if (window.createObjectURL!=undefined) { // basic
				url = window.createObjectURL(file) ;
			} else if (window.URL!=undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file) ;
			} else if (window.webkitURL!=undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file) ;
			}
				return url ;
		}
	</script>

<script type="text/javascript">


	$(document).ready(function(){ 
	    	//回显用户签名与个人简介和性别
		$("#profile").text("${userInfo.autograph}");
		$("#userIntro").text("${userInfo.summary}");
		if(${userInfo.sex !=null})
			$("#sex").val( [${userInfo.sex}] ).attr("selected",true);
		//回显用户的头像信息
		if("${UserInfo.avator != null}"){
			$("#user_avator").attr("src","${APP_PATH }/readUserAvator");
		}
	 }); 
	
		$("#user_update_btn").click(function(){
			var userId = $("#userId").val();
			if(!validate_info()){
				return false;
			}
				$.ajax({
					url:"${APP_PATH}/userInfo",
					//序列化表单数据，用于ajax传输
					data:$("#updateUserInfoForm").serialize(),
					type:"PUT",
					success:function(data){
						var profileMsg = $("#profile").val();
						console.log(data.msg);
						alert(data.msg);
						//用户信息修改成功后，根据用户的签名自动更行标题栏签名
						 if($("#profile").val() == null || $("#profile").val() == ""){
							 $("#autograph").text("你可以到个人资料中设置自己的签名.");
						}else{
							$("#autograph").text(profileMsg);
						} 
						
					},
				});
		});
		
		function validate_info(){
			//1.校验用户年龄
			var age = $("#age").val();
			if(age<=0 || age>=120){
				show_validate_msg("#age","error","年龄必须大于0岁且小于120岁！");
				return false;
			}else{
				show_validate_msg("#age","success","");
			}
			//2、校验邮箱信息
			var email = $("#userEmail").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				show_validate_msg("#userEmail", "error", "邮箱格式不正确！");
				return false;
			}else{
				show_validate_msg("#userEmail", "success", "");
			}
			//3.验证手机号码
			var phoneNum = $("#userPhone").val();
			var regPhoneNum = /^1(3|4|5|7|8)\d{9}$/;
			if(!regPhoneNum.test(phoneNum)){
				show_validate_msg("#userPhone", "error", "手机号码输入错误！");
				return false;
			}else{
				show_validate_msg("#userPhone", "success", "");
			}
			return true;
		}
		
		function show_validate_msg(ele,status,msg){
			//1.移除错误与正确在父节点添加的样式
			$(ele).parent().removeClass("has-success has-error");
			//2.信息正确后将表单框显示成绿色
			$(ele).css({'border-color':''});
			$(ele).next("span").text("");
			if(status == "success"){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			}else{
				$(ele).parent().addClass("has-error");
				$(ele).css({'border-color':'#a94442'});
				$(ele).next("span").text(msg);
			}
		}
	</script>
</html>
