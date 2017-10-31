
/**
 *用户点击发消息按钮时显示与好友对应的聊天框
 */
$(".sendMsg_btn").click(function(){
	$(".chatBox_demo").hide(friendName);
	var sendMsg_btn_ele = $(".sendMsg_btn");
	var friendName = sendMsg_btn_ele.attr("to");
	//根据好友名找到对应的好友id
	var friendId = friends_map.get(friendName).userId;

	$("#friendName").text(friendName);
	$(".friend-title").show();
	//根据好友id找到对应的好友聊天框，显示出来
	$("#"+friendId).show();
	$("#msg_div").show();


});

/**
 *用户点击查看帮助时，弹出帮助窗口
 */
var m1 = new MyModal.modal(function() {});
$('.help').on("click", function() {
	$(".aboutUs").hide();
	$(".m-middle").show();
	m1.show();
	$(".warnUser").html("帮助");
});

/*
 *用户点击关于我们的按钮弹出关于我们信息的弹出框
 */
$(".aboutUsInfo").click(function(){

	$(".m-middle").hide();
	$(".warnUser").html("关于我们");
	//向后台请求关于我们的信息
	$.ajax({
		/*url: '${APP_PATH}/aboutUs',*/
		url: 'aboutUs',
		type: "POST",
		success: function (data) {
			$(".team").html(data.map.teamInfo.team);
			$(".backMail").html(data.map.teamInfo.email);
			$(".advice").html(data.map.teamInfo.qq);
		}
	});

	$(".aboutUs").show();
	m1.show();

});

//当用户点击开发团队与使用反馈时弹出对应的消息框
$(".team_href").click(function(){
	$("#tab2").hide();
	$("#tab1").show();
});
//当用户点击开发团队与使用反馈时弹出对应的消息框
$(".back_href").click(function(){
	$("#tab1").hide();
	$("#tab2").show();
});



