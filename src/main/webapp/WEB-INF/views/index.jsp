<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>networkchat</title>

<meta name="description" content="">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<jsp:include page="/WEB-INF/views/css.jsp"></jsp:include>
</head>
<body>
	<header class="am-topbar am-topbar-inverse admin-header">
		<div class="am-topbar-brand">
			<!-- <strong>B/S在线交流</strong> -->
		</div>

		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

			<ul
				class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
				<li class="am-dropdown" data-am-dropdown><a
					class="am-dropdown-toggle" data-am-dropdown-toggle
					href="javascript:void(0)"> <span
						class="glyphicon glyphicon-user"></span> ${userInfo.username }
				</a></li>

			</ul>
		</div>
	</header>

	<!-------------------------------------------链接展示栏 ----------------------------------------------------->
	<div class="am-cf admin-main">
		<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
			<div class="am-offcanvas-bar admin-offcanvas-bar">
				<ul class="am-list admin-sidebar-list">
					<li><a href="${APP_PATH }/index"><span
							class="am-icon-weixin"></span> 聊天</a></li>
					<li><a href="javascript:void(0)" class="am-cf"
						id="userInfo_href"> <span class="am-icon-book"></span> 信息 <span
							class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
					</a></li>

					<!-- 	<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav'}"><span -->
					<li class="admin-parent"><a class="am-cf"><span
							class="am-icon-cogs"></span> 设置 <!-- <span
							class="am-icon-angle-right am-fr am-margin-right"></span> --></a>
						<ul class="am-list am-collapse admin-sidebar-sub am-in"
							id="collapse-nav">
							<li><a href="javascript:void(0)" class="am-cf"
								id="userInfoSetting"> <span
									class="glyphicon glyphicon-pencil">个人资料</span> <span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span>
							</a></li>
							<li><a href="javascript:void(0)" id="desktop-notification"
								class="am-cf"><span class="glyphicon glyphicon-bell"></span>
									桌面通知<span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>

						</ul></li>
					<li><a href="javascript:void(0);" class="help"><span
							class="glyphicon glyphicon-home"></span> 帮助</a></li>
					<li><a href="javascript:void(0);" class="aboutUsInfo"><span
							class="glyphicon glyphicon-link"></span> 关于</a></li>
					<li><a href="${APP_PATH }/logout" class="logout"><span  
							class="glyphicon glyphicon-off"></span> 注销</a></li>
				</ul>

				<div class="am-panel am-panel-default admin-sidebar-panel">
					<div class="am-panel-bd">
						<p>
							<span class="glyphicon glyphicon-tags"></span> 签名
						</p>
						<p class="user-autograph list-msg">
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
							<span class="glyphicon glyphicon-bookmark"></span> 公告
						</p>
						<p class="list-msg">${anno}</p>
					</div>
				</div>

			</div>
		</div>

		<!-------------------------------------------信息交流展示 ------------------------------------------------------>
		<div class="admin-content">
			<div class="admin-content-body">
				<div style="border: 1px solid #ccc;" class="am-cf am-padding">
					<div class="am-fl am-cf" style="float: left; width: 100%;">
						<div style="float: left">
							<strong class="am-text-primary am-text-lg">B/S</strong>  在线交流平台
						</div>  
						<div style="float: right; position: relative; width: 200px;"
							class="input-group">

							<input id="searchFriend" type="text" name="friendName"
								class="form-control" placeholder="添加好友">
							<div class="input-group-addon" id="add_friend_div"
								style="cursor: pointer;">搜索</div>
						</div>
					</div>
				</div>

				<div class="container-fluid">
					<div class="row">
						<div class="col-md-2">
		<!---------------------------------------------------- 列表区 ---------------------------------->
							<div class="am-panel"
								style="float: right; width: 106%;">
								<div class="am-panel-hd">
									<h3 class="am-panel-title">[ 好友列表 ]</h3>
								</div>
								<ul id="userList" class="am-list am-list-static am-list-striped">
								</ul>

							</div>
						</div>

		<!---------------------------------- 聊天区 --------------------------------------------------->
						<div class="col-md-6 chat-list">
							<div class="friend-title"
								style="display: none; margin-top: 7px; margin-bottom: 3px; text-align: center;">
								<%-- <img id="addFriendChat" src="${APP_PATH }/static/pic/add-friend.png"> --%>
								<span id="friendName"></span>
							</div>

							<div id="all_chat_div">

								<div id="0" class="am-scrollable-vertical chatBox_demo"
									style="height: 512px; border-left: 1px solid rgb(204, 204, 204);
									border-bottom: 1px solid rgb(204, 204, 204); border-top:0px;">
									<ul id="chatInfo"
										class="am-comments-list am-comments-list-flip">
										<li class="warn-chat-noone">未选择聊天</li>
									</ul>

									<%--用户个人信息的div --%>
									<div class="userInfo" style="display: none;">
										<span>详细信息</span>
									</div>
									<!-- <hr> -->
			<!--------------------------------------用户个人信息 --------------------------------------->
									<div class="am-tabs am-margin" data-am-tabs id="userInfo"
										style="display: none;">

										<div class="am-tab-panel am-fade am-in am-active">
											<div class="am-g">
												<div class="am-u-md-6">
													<b>用户名:</b>
												</div>
												<div class="am-u-md-6">${userInfo.username}</div>

												<div class="am-u-md-6">
													<b>性别:</b>
												</div>
												<div class="am-u-md-6 userGender_detail">
													<c:if test="${userInfo.sex == 1}">男</c:if>
													<c:if test="${userInfo.sex == 0}">女</c:if>
													<c:if test="${userInfo.sex == null}">保密</c:if>
												</div>
												<div class="am-u-md-6">
													<b>年龄:</b>
												</div>
												<div class="am-u-md-6 userAge_detail">
													<c:if test="${userInfo.age != null && userInfo.age != ''}">${userInfo.age}</c:if>
													<c:if test="${userInfo.age == null}">保密</c:if>
												</div>
												<div class="am-u-md-6">
													<b>签名:</b>
												</div>
												<div class="am-u-md-6 userAuto_detail" >
													<c:if
														test="${userInfo.autograph == null || userInfo.autograph == ''}">
							                               	这个人很懒,什么都没有留下!
							                            </c:if>
													<c:if
														test="${userInfo.autograph != null && userInfo.autograph != ''}">
							                               	${userInfo.autograph}
							                            </c:if>
												</div>
			<!----------------------------------------------展示用户头像信息 -------------------------------------------------->
												<div class="am-panel-bd">
													<div>
														<div class="am-u-md-4"
															style="margin-top: 6%; padding: 0px;">
															<img id="user_avator" />
														</div>
														<div class="am-u-md-8" style="margin-top: 6%;">
															<p>你可以从本地上传头像</p>
															<form class="am-form" id="upLoadPic_Form"
																enctype="multipart/form-data" method="POST">
																<div class="am-form-group">
																	<input type="file" id="userPic" name="avator">
																	<p class="am-form-help">当图片改变时即完成上传</p>
																	<!-- <button type="button" id="uploadPic_btn"
																			class="am-btn am-btn-primary am-btn-xs">保存</button> -->
																</div>
															</form>
														</div>
													</div>
												</div>
											</div>
										</div>

									</div>

			<!-----------------------------------------用户好友信息的div --------------------------------------------------->
									<div class="am-tabs am-margin" data-am-tabs id="friendInfo"
										style="display: none; text-align: center;">
										<div class="info-margin-1">
											<img id="friendAvatar" src="" />
										</div>
										<div class="info-margin-2">
											<span id="friendName_detail"></span>
											<!-- <i class="i_gender"></i> -->
											<img class="img-gender" src="" />
										</div>
										<div class="info-margin-2">
											<span id="friendAuto"> </span>
										</div>
										<div class="info-margin-1">
											<button class="sendMsg_btn">发消息</button>
										</div>
									</div>
								</div>
							</div>

		<!-------------------------------------------表情与发送信息 ----------------------------------------------->
							<div id="msg_div" style="display: none;">
								<div id="emoticon_div" class="emotion">
									<img src="${APP_PATH}/static/pic/smile.png"
										style="width: 25px; height: 25px;"></img>
								</div>
								<div>
									<textarea class="layui-textarea send-text" id="send_text"></textarea>
								</div>
								<div>
									<button id="send_text_btn" class="btn btn-default">发送</button>
								</div>
							</div>

							<!-- </div> -->
						</div>
		<!-- ------------------------------------ 白板区 ----------------------------------------------->
						<div style="margin-left: 64.8%;">
							<div id='canvas'>
								<canvas id="c1" width="580%" height="512">  
								</canvas>
							</div>

							<div id="boardList">
								<div id="colorList"></div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="helpModal.html"></jsp:include>
	<jsp:include page="infoSettingModal.html"></jsp:include>
</body>
<jsp:include page="/WEB-INF/views/js.jsp"></jsp:include>

<!-- -----------------------------------加载页面请求用户到好友列表  ----------------------------- -->
<script type="text/javascript">
	//设置一个map用户全局存放好友信息
	var friends_map = new Map([ [ "key", "value" ] ]);
	//页面加载时发送ajax请求，请求到用户的好友信息
	$(document).ready(function() {
		$.ajax({
				/*url : "${APP_PATH}/getFriendsList",*/
				url : "getFriendsList",
				type : 'post',
				success : function(data) {
				//页面加载请求到好友列表，遍历显示添加在好友列表里
				$.each(
					data.map.friends,
					function(friendName,friend) {
						friends_map.set(friendName,friend);
						//var imgEle = $("<img></img>").attr("src","data:image/jpeg;base64,"+friend.avator).appendTo($("#userList"));
						var liEle = $("<li> </li>")
									.attr(
									"name","liList")
									.attr(	"friend_name",friendName)
									.attr(	"friend_id",friend.userId)
									.attr("status","0")
									.addClass("friend")
									.css({
										"cursor":"pointer",
										"height":"46px",
										'background-color':'#f5f5f5'
									})
									.appendTo($("#userList"));
							
							if (friend.autograph == null) {
								liEle.attr("friend_autograph","该用户很懒什么也没有留下！");
							} else {
									liEle.attr("friend_autograph",friend.autograph);
							}
	
							/* .attr("friend_autograph",friend.autograph) */
	
							/* var div1Ele = $("<div><div>").attr("name",
							"div1").appendTo(liEle); */
							var avatarEle = $("<div><div>");
							$(avatarEle).css({"float" : "left"});
							var imgEle = $("<img></img>")
											.attr("src","data:image/jpeg;base64,"+ friend.avator);
							$(imgEle).css({
											"width" : "35px",
											"height" : "35px"
										});
							avatarEle.append(imgEle);
							avatarEle.appendTo(liEle);
							var nemeEle = $("<div><div>").append(friendName)
										.insertAfter(avatarEle);
	
							//添加有好友对应的聊天框
							var chat_box_ele = $("<div> </div>")
												.addClass("am-scrollable-vertical chatBox")
												.attr("id",friend.userId)
												.attr("friend_name_chatBox",friendName)
												.css({
													'border': '1px solid rgb(204, 204, 204)',
												});
	
							var ul_ele = $("<ul></ul>")
										.addClass("chat_content")
										.addClass("am-comments-list am-comments-list-flip");
							ul_ele.appendTo(chat_box_ele);
							chat_box_ele.appendTo($("#all_chat_div"));
							$(".chatBox").hide();
						});
						//console.log(friendsList)
						//当一个信息用户上线时，如果该用户有上线用户的好友，好友列表显示在线
						$.each( friendsList, function(index,item){ 
							var mapUser = friends_map.get(item);
							if(mapUser != undefined){
								$("li[friend_name = '"+ mapUser.username+"']").css('background-color','white');
								$("li[friend_name = '"+ mapUser.username+"']").attr('status','1');
							} 
						    
						});  
					},
	
			});
		});
	
</script>

<!-----------------------修改用户信息前 回显用户数据--查看用户个人信息 修改头像信息------------------------------->
<script type="text/javascript">
	//用户点击修改信息连接时显示对应的信息修改页面
	$(function(){
		getSrceenWH();
		
		//显示弹框
		$('#userInfoSetting').click(function(){
			$(".setInfo-showWarn").html("");
			//回显用户签名与个人简介和性别
			$("#profile").text("${userInfo.autograph}");
			$("#userIntro").text("${userInfo.summary}");
			if("${userInfo.sex != null}"){
				$("#sex").val( ["${userInfo.sex}"] ).attr("selected",true);
			}
			
			className = $(this).attr('class');
			$('#dialogBg').fadeIn(300);
			$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
		});
		
		//关闭弹窗
		$('.claseDialogBtn').click(function(){
			$('#dialogBg').fadeOut(300,function(){
				$('#dialog').addClass('bounceOutUp').fadeOut();
			});
		});
	});
	
	var w,h,className;
	function getSrceenWH(){
		w = $(window).width();
		h = $(window).height();
		$('#dialogBg').width(w).height(h);
	}
	
	window.onresize = function(){  
		getSrceenWH();
	}  
	$(window).resize();  
	
	//修改用户头像
	$("#userPic").change(function() {
		 var options = {  
	     	url: "uploadPic",  
	        type: "post",  
	        dataType: "json",  
	        success: function(data, status, xhr) { 
	        			alert(data.msg);
	        }  
	 	 };  
	          
		$("#upLoadPic_Form").ajaxSubmit(options);  
	});
	
	
	/**
	 * 在用户查看自己的信息时
	 */
	var avatarPath = null;
	$("#userInfo_href").click(function() {
		//回显用户的头像信息
		if ("${userInfo.avator != null}") {
			/*$("#user_avator").attr("src", "${APP_PATH }/readUserAvator");*/
			if(avatarPath == null ||avatarPath == undefined){
				$("#user_avator").attr("src", "readUserAvator");
			}
			else{
				$("#user_avator").attr("src", avatarPath);
			}
		}
		var chatBox_demo_ele = $(".chatBox_demo");
		var child = chatBox_demo_ele.children(':first');
		$(".friend-title").hide();
		$(".chatBox").hide();
		//详细信息标题
		$(".userInfo").show();
		$("#friendInfo").hide();
		$("#msg_div").hide();
		$(".chatBox_demo").show();
		$("#userInfo").show();
		$(".friend-title").hide();
		child.text("");

	});

	//在用户改变头像时更新显示的头像信息
	$("#userPic").change(function() {
		var avatorPath = getObjectURL(this.files[0]);
		avatarPath = avatorPath;
		$("#user_avator").attr("src", avatorPath);
	});
	//返回上传图片的url
	function getObjectURL(file) {
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}

</script>

<!----------------------------------- 修改用户信息 ------------------------------------------------------->
<script type="text/javascript">

	$("#user_update_btn").click(function(){

		var userId = $("#userId").val();
		if(!validate_info()){
			return false;
		}
			$.ajax({
				/*url:"${APP_PATH}/userInfo",*/
				url:"userInfo",
				//序列化表单数据，用于ajax传输
				data:$("#updateUserInfoForm").serialize(),
				type:"PUT",
				success:function(data){
					var profileMsg = $("#profile").val();
					var age = $("#age").val();
					var sex = $("#sex").val();
					//alert(data.msg);
					//用户信息修改成功后，根据用户的签名自动更行标题栏签名与用户性别，年龄，签名
					 if($("#profile").val() == null || $("#profile").val() == ""){
						 $(".user-autograph").text("你可以到个人资料中设置自己的签名.");
					}else{
						$(".user-autograph").text(profileMsg);
						$(".userAuto_detail").text(profileMsg);
					} 
					if(sex == 0){
						$(".userGender_detail").text('女');
					}else{
						$(".userGender_detail").text('男');
					}
					$(".userAge_detail").text(age);
					$(".setInfo-showWarn").html("修改信息成功").css({'color':'green',});
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

<!---------------------------- 用户添加好友信息时 ------------------------------------------------------>
<script type="text/javascript">

	var TimeFn = null;

	//这里需要修改！！！！！！！！！！！！！！！！换成form表单提交数据
	//点击添加好友
	$("#add_friend_div").click(function() {
		var friendName = $("#searchFriend").val();
		if (friendName == "" || friendName == null) {
			alert("请输入要添加的好友！");
		} else {
			$.ajax({
				url : "${APP_PATH}/addFriend",
				data : "friendName=" + friendName,
				type : 'post',
				success : function(data) {
					alert(data.msg);
					<%--ajax信息返回后刷新页面，将添加的好友显示出来 --%>
					if (data.msg == "添加好友成功!") {
						window.location.href = "${APP_PATH}/index";
					}
				},
			});
			$("#searchFriend").val("");
		}
	});
	
</script>

<!-----------------------------------------WebSocket连接  ---------------------------------------->
<script type="text/javascript">
	var ws;
	window.onload = connect;
	var username = "${userInfo.username}";
	var friendsList = null;
	var flag = true;  //页面通知的标记
	function connect() {
	
		//将用户信息隐藏，再点击查看信息时再显示
		/* $("#userInfo").hide();
		//隐藏表情框，消息发送框
		$("#msg_div").hide(); */
		$(".dialogIco").attr("src","${APP_PATH }/static/pic/ico.png");
		if ('WebSocket' in window) {
			ws = new WebSocket(
					"ws://localhost:8080/networkchat/chatSocket?username="
							+ "${userInfo.username}");
		} else if ('MozWebSocket' in window) {
			ws = new MozWebSocket(url);
		} else {
			alert('当前浏览器不支持HTML5服务！');
			return;
		}
	
		if (!("Notification" in window)) {
		       alert("当前浏览器不支持 notification");
		   }
		
		function getFriendsMap(){
			
		}
		//单击好友获得好友信息
		$(document).on("click", ".friend", function() {
			var friend_autograph = $(this).attr("friend_autograph");
			var friend_name = $(this).attr("friend_name");
			var friend_avatar = friends_map.get(friend_name).avator;
			var friend_id = $(this).attr("friend_id");
			var friendInfo_ele = $("#friendInfo");
			var friendInfo_ele_childrens = friendInfo_ele.children();
			var friend_gender = friends_map.get(friend_name).sex;
			
			//画图模式还原
			rebackDrawBoard();
			// 取消上次延时未执行的方法
			clearTimeout(TimeFn);
			//执行延时
			TimeFn = setTimeout(function() {
				
				$(".friend-title").hide();
				$("#msg_div").hide();
				$("#userInfo").hide();
				$(".chatBox").hide();
				$("#chatInfo").hide();
				//详细信息标题
				$(".userInfo").show();
				$(".chatBox_demo").show();
				$("#friendInfo").show();
				$("#friendAvatar").attr("src", "data:image/jpeg;base64,"
						+ friend_avatar);
				$("#friendName_detail").text(friend_name);
				if(friend_gender == 1){
					$(".img-gender").attr("src","${APP_PATH}/static/pic/male.gif");
				}else if(friend_gender == 0){
					$(".img-gender").attr("src","${APP_PATH}/static/pic/female.gif");
				}
				
				$("#friendAuto").text(friend_autograph);
				$(".sendMsg_btn").attr("to",friend_name);
			}, 300);
		});

		
		
		//双击某个好友显示对应的聊天框
		$(document).on("dblclick", ".friend", function() {
			
			//如果查看了好友信息隐藏好友信息
			$("#friendInfo").hide();
			//将表情，文本写入框，发送按钮div显示出来
			$("#msg_div").show();
			var to_friend = null;
			var to_friend_id_demo = null;
			var friend_id = $(this).attr("friend_id");
			var friend_name = $(this).attr("friend_name");
			clearTimeout(TimeFn);
			var chat_div_nodes = $("#all_chat_div").children();
			to_friend = $(this).attr("friend_name");
			to_friend_id_demo = $(this).attr("friend_id");
			//生成对话框的div
			$("#friendName").text(to_friend);
			$(".friend-title").show();
			$(".chatBox_demo").hide();
			$(".chatBox").hide();

			//根据id找到对应的聊天框显示出来
			$("#" + friend_id).show();
			//画图模式还原
			rebackDrawBoard();

		});
		
		function rebackDrawBoard(){
			var oC = document.getElementById('c1');
			var oGC = oC.getContext('2d');
			oGC.clearRect(0, 0, $("#c1").css("width").split("px")[0], $("#c1").css("height").split("px")[0]);
		}
		
		//处理表情
		$(function() {
			$('.emotion').qqFace({
				id : 'facebox',
				assign : 'send_text',
				path : '${APP_PATH }/static/pic/facelist/', //表情存放的路径
			});
		});
		function replace_em(str) {
			str = str.replace(/\</g, '&lt;');
			str = str.replace(/\>/g, '&gt;');
			str = str.replace(/\n/g, '<br/>');
			str = str.replace(/\[em_([0-9]*)\]/g,
							'<img src="${APP_PATH}/static/pic/facelist/$1.gif" border="0" />');
			return str;

		}

		var sendMsg = null;
		//点击发送消息按钮发送数据
		$("#send_text_btn").click(function() {
			sendContent();
		});
		//发送消息绑定回车事件
		$('#send_text').on('keypress',function(event){ 
	        if(event.keyCode == 13){  
	        	event.preventDefault();  //回车不换行
	        	sendContent();
	        }else if(event.keyCode != 13) 
	        	return;
    	});
		function sendContent(){
			var to_friend = $("#friendName").text();
			var to_friend_id_demo = friends_map.get(to_friend).userId;
			var friendLiEle = $("[friend_name='"+to_friend+"']");
			var friendStatus = friendLiEle.attr("status");  //获取用户在线状态，如果不在线将消息存放在数据库中
			sendMsg = $("#send_text").val();       
			
			if(sendMsg != null && sendMsg != ''){        
				
				if(friendStatus ==1){    //用户在线时构建Json数据，将消息同步给好友
					var obj = {
							to : to_friend,
							msg : sendMsg,
							type : 1
					}
					//将发送的json数据转成字符串
					var str = JSON.stringify(obj);
					ws.send(str);
				}else{  
					
					var friend_avatar = friends_map.get(to_friend).avator;
					//桌面通知提醒
					notice(to_friend , to_friend+"不在线，请稍后联系！" , "data:image/jpeg;base64,"
								+ friend_avatar);//用户不在线时，进行桌面通知提醒
				}
				
					//将发送的信息添加在对应的聊天框内
					//根据对应的好友id找到没有隐藏的聊天框
					var chatContent_ele = $("#" + to_friend_id_demo);
					//找到没有隐藏的聊天框下的第一个ul孩子节点
					var ul_content_ele = chatContent_ele.children(":first");
					//	alert(chatContent_ele+"..."+ul_content_ele);
					var li_ele = $("<li></li>");
					li_ele.addClass("comm_li_2");

					var div_all_ele = $("<div></div>");

					var div_content_ele = $("<div></div>");
					var span_content_ele = $("<span class='bubble rightBubble'></span>");
					var span_horn1_ele =  $("<span class='bottomLevel'></span>");
					var span_horn2_ele =  $("<span class='topLevel'></span>");
					//将信息筛选，需要转化为表情的转化为表情符号
					span_content_ele.html(replace_em(sendMsg));

					var div_avatar_ele = $("<div></div>");
					var img_avatar_ele = $("<img></img>");
					var br_ele = $("</br>");
					//根据session中的信息请求当前用户头像
					img_avatar_ele.attr("src", "${APP_PATH }/readUserAvator");
					img_avatar_ele.css({
						'width' : '40px',
						'height' : '40px',
					});

					div_content_ele.css({
						'float' : 'right',
					});
					div_avatar_ele.css({
						'float' : 'right',
						'margin-right' : '8px',
					});

					img_avatar_ele.appendTo(div_avatar_ele);
					div_avatar_ele.appendTo(div_all_ele);
					span_content_ele.appendTo(div_content_ele);
					span_horn1_ele.appendTo(span_content_ele);
					span_horn2_ele.appendTo(span_content_ele);
					div_content_ele.appendTo(div_all_ele);
					div_all_ele.appendTo(li_ele);

					li_ele.appendTo(ul_content_ele);
					br_ele.appendTo(ul_content_ele);
					
					$("#send_text").val('');
					//让消息随着滚动条滚动一致固定在底部，筛选没有隐藏的聊天框
					$(".chatBox").scrollTop($(".chatBox :visible")[0].scrollHeight);
			}
			
		}
		
		//处理发送回来的消息
		//获得发送消息好友的名称
		var from_friend = null;
		var chat_msg = null;
		$("#desktop-notification").click(function(){
			if(flag){
				flag = false;
				alert("桌面通知已关闭！");
			}else{
				flag = true;
				alert("桌面通知已开启！");
			}
		});
		//这里要完成好友消息的提醒，以及将好友发来的聊天信息展示在聊天框内,/获得好友发送过来的消息
		ws.onmessage = function(event) {
			
			eval("var result=" + event.data);
			from_friend = result.from;
			chat_msg = result.content;
			type = result.type;
			
			if(chat_msg != null || chat_msg != undefined)
				chat_msg = replace_em(chat_msg);
			
			if(type == 101){
				$("#userList > li").css("background-color","#f5f5f5");
				$("#userList > li").attr("status","0");
				friendsList = result.list;
				//当一个信息用户上线时，如果该用户有上线用户的好友，好友列表显示在线(防止用户已经在线不能及时刷新)
				$.each(result.list, function(index,item){ 
					var mapUser = friends_map.get(item);
					if(mapUser != undefined){
						$("li[friend_name = '"+ mapUser.username+"']").css('background-color','white');
						$("li[friend_name = '"+ mapUser.username+"']").attr("status","1");
					} 
				    
				});  
			}
			if(type == 1){
				//获得好友发来的信息后展示在聊天框内,首先找到对应的聊天框div节点，再获得子孩子ul标签
				var from_friend_name_ele = $(".chatBox[friend_name_chatbox='"
						+ from_friend + "']");
				var ul_content_ele = from_friend_name_ele.children(":first");

				var li_ele = $("<li></li>");
				li_ele.addClass("comm_li");
				var div_all_ele = $("<div></div>");
				var div_content_ele = $("<div></div>");
				var div_avatar_ele = $("<div></div>");
				var img_avatar_ele = $("<img></img>");
				var br_ele = $("</br>");

				div_avatar_ele.css({
					'float' : 'left',
					'margin-left' : '8px',
				});
				div_content_ele.css({
					'float' : 'left',
				});
				var span_content_ele = $("<span class='bubble leftBubble'></span>").append(chat_msg);
				var span_horn1_ele =  $("<span class='bottomLevel'></span>");
				var span_horn2_ele =  $("<span class='topLevel'></span>");

				//根据map获取对应好友的头像
				var friend_avatar = friends_map.get(from_friend).avator;
				//根据双击好友列表获得好友的头像信息，将发送消息好友的头像信息显示出来
				img_avatar_ele.attr("src", "data:image/jpeg;base64,"
						+ friend_avatar);
				img_avatar_ele.css({
					'width' : '40px',
					'height' : '40px',
				});
				img_avatar_ele.appendTo(div_avatar_ele);
				div_avatar_ele.appendTo(div_all_ele);
				span_content_ele.appendTo(div_content_ele);
				span_horn1_ele.appendTo(span_content_ele);
				span_horn2_ele.appendTo(span_content_ele);
				div_content_ele.appendTo(div_all_ele);
				div_all_ele.appendTo(li_ele);

				li_ele.appendTo(ul_content_ele);
				br_ele.appendTo(ul_content_ele);
				//根据好友的姓名找到对应的li标签
				var liNode = $("[friend_name='" + from_friend + "']");
				var warn_msg = $("<div></div>");

				//如果聊天框是隐藏的，追加提醒
				var to_friend_id = friends_map.get(from_friend).userId;
				if ($("#" + to_friend_id).is(':hidden')) {
					warn_msg.addClass("notice");
				}
				warn_msg.appendTo(liNode);
				//在有消息提示时，点击好友取消提示
				$(liNode).click(function() {
					warn_msg.removeClass("notice");
				});
				//将收到的信息显示在滚动条底部
				//在没有聊天框显示的时候，不滚动
				if($(".chatBox :visible")[0] == undefined){
					$(".chatBox").scrollTop($(".chatBox")[0].scrollHeight);
				}else{
					//当聊天框没有隐藏的时候，让发送来的消息一直处在滚动条底部
					$(".chatBox").scrollTop($(".chatBox :visible")[0].scrollHeight);
				}
				//桌面通知提醒
				notice(from_friend , result.content , "data:image/jpeg;base64,"
						+ friend_avatar);
			}
			
			//白板演示,展示给好友
			if(type == 0){
				boardFlag = false;
				
				var oC = document.getElementById('c1');
				var oGC = oC.getContext('2d');
				var color = result.lineColor;
				var lineWidth = result.lineWidth;
					
				var coordArry = chat_msg.split("_");
				var x = coordArry[0];
				var y = coordArry[1];
				
			    oGC.strokeStyle = color;
			    oGC.lineWidth = lineWidth;
				oGC.lineTo(x,y);
				oGC.stroke();
			}
			if(type == 100){
				var oC = document.getElementById('c1');
				var oGC = oC.getContext('2d');
				oGC.beginPath();
			}
			
		};
	}
	
	function notice(title , body , icon) {
		//判断页面通知标记，为真时通知
		if(flag){
			var notification = new Notification(title,{
			 	   body:body,
			       icon:icon,
			       tag:"1",
			});
		   //设置桌面通知的时间量，两秒后关闭通知
			notification.onshow = function () {
		    	setTimeout(function () {
				        notification.close();
				    }, 2000);
			}
			notification.onclick = function(){
				notification.close();
			}
		}
		
	}
	
</script>

<!-------------------------------------白板 -------------------------------------->
<script type="text/javascript">
	initCanvas();
	var lineColor = null;
	var lineWidth = null;
	var boardFlag = true;
	
	function initCanvas() {
		var oC = document.getElementById('c1');
		var oGC = oC.getContext('2d');
	//	var from = null;
		var to = null;
		var type = null;
		var msg = null;
		
		oC.onmousedown = function(ev) {
			
			var ev = ev || window.event;
			oGC.beginPath();
			oGC.moveTo(ev.clientX - oC.offsetLeft, ev.clientY - oC.offsetTop);
			document.onmousemove = function(ev) {
				var ev = ev || window.event;  
				var x = ev.clientX-oC.offsetLeft;
		        var y = ev.clientY-oC.offsetTop;
		        
		        if($(".friend-title").is(":visible") && lineColor != null  && boardFlag == true){
			    	var obj = {
				    	to : $("#friendName").text(),
				    	msg : x + '_' + y,
				    	type : 0,
				    	lineColor:lineColor,
				    	lineWidth:lineWidth
			   		 }
			    	
			    	oGC.strokeStyle = lineColor;
			    	oGC.lineWidth = lineWidth;
				    oGC.lineTo(x,y);
				    oGC.stroke();
				    //将发送的json数据转成字符串
					var str = JSON.stringify(obj);
				    ws.send(str);
		   		 }	
			};
			document.onmouseup = function() {
				boardFlag = true;
				var obj = {
				    to : $("#friendName").text(),
				    msg : "",
				    type : 100,
				    
			    }
				oGC.beginPath();
				oGC.strokeStyle = null;
				var str = JSON.stringify(obj);
				//当画图点移开时向后台发送数据，告知画图点已经改变，重新划线，使线段不连续
				ws.send(str);
				document.onmousemove = null;
				document.onmouseup = null;
			};
		};
	}
	//白板划线颜色选择
	var availableColors = [];
	colorContainers = new Array(3 * 3 * 3);
    for (var i = 0; i < colorContainers.length; i++) {
        var colorContainer = colorContainers[i] =
        document.createElement("div");
        var color = availableColors[i] =
            [
                Math.floor((i % 3) * 255 / 2),
                Math.floor((Math.floor(i / 3) % 3) * 255 / 2),
                Math.floor((Math.floor(i / (3 * 3)) % 3) * 255 / 2),
               1.0
            ];
        colorContainer.setAttribute("style","background-color: "+rgb(color));
        colorContainer.setAttribute("class","color-box");
       
        colorContainer.addEventListener("mousedown", (function(ix) {
            return function() {
            	//console.log(ix);
                setColor(ix);
                lineColor = $(this).css("background-color");
            };
        })(i), false);
		var colorList = $("#colorList");
		colorList.append(colorContainer);
      }
    
      function rgb(color) {
          return "rgba(" + color[0] + "," + color[1] + ","
                  + color[2] + "," + color[3] + ")";
      }
      function setColor(colorIndex) {
          if (typeof currentColorIndex !== "undefined")
              colorContainers[currentColorIndex]
                  .style.borderColor = "#000";
          currentColorIndex = colorIndex;
          colorContainers[currentColorIndex]
              .style.borderColor = "#d08";
      }
      
      //白板划线浓度选择
      var availableThicknesses = [1,2, 3, 5, 8,10, 16, 22, 28 ,40, 50];
      
      var thicknessContainersBox = document.createElement("div");
      $("#boardList").append(thicknessContainersBox);
      
      thicknessContainers = new Array(availableThicknesses.length);
      for (var i = 0; i < thicknessContainers.length; i++) {
          var thicknessContainer = thicknessContainers[i] =
              document.createElement("div");
          thicknessContainer.setAttribute("class","width-box");
          thicknessContainer.appendChild(document.createTextNode(
                  String(availableThicknesses[i])));
          thicknessContainer.addEventListener("mousedown", (function(ix) {
              return function() {
            	  lineWidth = $(this).text();
                  setThickness(ix);
              };
          })(i), false);

          thicknessContainersBox.appendChild(thicknessContainer);
      }
      
      function setThickness(thicknessIndex) {
          if (typeof currentThicknessIndex !== "undefined")
              thicknessContainers[currentThicknessIndex]
                  .style.borderColor = "#000";
          currentThicknessIndex = thicknessIndex;
          thicknessContainers[currentThicknessIndex]
              .style.borderColor = "#d08";
      }
</script>

<script type="text/javascript">
	 window.addEventListener('beforeunload', function(event) {
		ws.close();           //页面离开前关闭socket连接
	});
</script>


</html>
