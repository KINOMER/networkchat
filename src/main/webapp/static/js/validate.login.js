/**
 * 
 */
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
	
	$("#button").click(function(){
		login();
	});
	

	function login(){
		var username = $("#username").val();
		var password = $("#password").val();
		var valistr = $("#verify").val();
		
		//alert(username+"   "+password+"   "+valistr+"   "+rememberMe	)
		
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
				error: function () {
					window.location.href = "index";
                },
			});
		}
	}
	//提示错误信息的方法
	function warnMsg(msg){
		$("#warnMsg").html("").css({});
		$("#warnMsg").html(msg).css({
			"color":"red",
		});
	}
	
	$('.imgcode').hover(function(){
		layer.tips("看不清？点击更换", '.verify', {
        		time: 6000,
        		tips: [2, "#3c3c3c"]
    		})
	},function(){
		layer.closeAll('tips');
	}).click(function(){
		$(this).attr('src','ValiImg?id=' + Math.random());
	});
	$("#remember-me").click(function(){
		var n = document.getElementById("remember-me").checked;
		if(n){
			$(".zt").show();
		}else{
			$(".zt").hide();
		}
	});
	
	