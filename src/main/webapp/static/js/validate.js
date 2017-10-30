/**
 * 在用户提交修改信息表单，与用户注册时校验用户信息
 */

//用户登录进行数据校验


//用户修改信息时的表单校验与提交

	

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
					alert(data.msg);
					//用户信息修改成功后，根据用户的签名自动更行标题栏签名与用户性别，年龄，签名
					 if($("#profile").val() == null || $("#profile").val() == ""){
						 $(".user-autograph").text("你可以到个人资料中设置自己的签名.");
					}else{
						$(".user-autograph").text(profileMsg);
					} 
					if(sex == 0){
						$(".userGender_detail").text('女');
					}else{
						$(".userGender_detail").text('男');
					}
					$(".userAge_detail").text(age);
					
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
	
	/* <script type="text/javascript">

	 //用户修改信息时的表单校验与提交
	$(document).ready(function(){ 
	    	//回显用户签名与个人简介和性别
		$("#profile").text("${userInfo.autograph}");
		$("#userIntro").text("${userInfo.summary}");
		if(${userInfo.sex != null})
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
					var age = $("#age").val();
					var sex = $("#sex").val();
					alert(data.msg);
					//用户信息修改成功后，根据用户的签名自动更行标题栏签名与用户性别，年龄，签名
					 if($("#profile").val() == null || $("#profile").val() == ""){
						 $(".user-autograph").text("你可以到个人资料中设置自己的签名.");
					}else{
						$(".user-autograph").text(profileMsg);
					} 
					if(sex == 0){
						$(".userGender_detail").text('女');
					}else{
						$(".userGender_detail").text('男');
					}
					$(".userAge_detail").text(age);
					
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
</script> */