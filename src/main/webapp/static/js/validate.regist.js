/**
 * 
 */

	//用户注册时校验数据
	function txtFocus(ele){
		$("."+ele+"").css({
			"border":"2px solid #0f88eb",
		});
	}
	function txtBlur(ele){
		$("."+ele+"").css({
			"border":"1px solid white",
		});
	}
	
	$("#username").change(function(){
		var username = $("#username").val();
		$.ajax({
			/*url:"${APP_PATH}/checkUsername/username-"+username,*/
			url:"checkUsername/username-"+username,
			type:"POST",
			success:function(data){
				//console.log(data);
				var msg = data.msg;
				if(data.code == 1){
					$("#button").removeAttr("disabled");
					$("#warnMsg").html("").css({});
					$("#warnMsg").html(msg).css({
						"color":"green",
					});
				}else{
					warnMsg(msg);
					$("#button").attr("disabled","disabled"); 
				}
			}
		});
	});
	
	$("#button").click(function(){
		var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		var regPassWord = /^(\w){6,16}$/;
		var username = $("#username").val();
		var password = $("#password").val();
		var rePassword = $("#password1").val();
		
		if(username ==  undefined || "" == username){
			warnMsg("请输入用户名！");
			return false;
		}else if(!regName.test(username)){
			warnMsg("用户名必须是2-5位中文或者6-16位英文和数字的组合!");
			return false;
		}else if(password == null || password ==''){
			warnMsg("请输入密码！");
			return false;
		}else if(rePassword == null || rePassword ==''){
			warnMsg("请重新输入密码！");
			return false;
		}else if(!regPassWord.test(password)){
			warnMsg("输入密码不合法！");
			return false;
		}else if(password != rePassword){
			warnMsg("两次密码不一致！");
			return false;
		}else{
			//$("#regist_form").submit(); 
			$.ajax({
				/*url:"${APP_PATH}/regist",*/
				url:"regist",
				type:"POST",
				data:$("#regist_form").serialize(),
				success:function(data){
					var msg = data.msg;
					var code = data.code;
					if(code == 1){
						$("#warnMsg").html("").css({});
						$("#warnMsg").html(msg).css({
							"color":"green",
						});
					}else{
						warnMsg(msg);
					}
					console.log(data);
					
				}
			});
		}
	});
	
	//提示错误信息的方法
	function warnMsg(msg){
		$("#warnMsg").html("").css({});
		$("#warnMsg").html(msg).css({
			"color":"red",
		});
	}
	
	
	
	
	