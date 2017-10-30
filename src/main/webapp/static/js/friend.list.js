/**
 * 
 */
//设置一个map用户全局存放好友信息
	var friends_map = new Map([ [ "key", "value" ] ]);
	//页面加载时发送ajax请求，请求到用户的好友信息
	$(document).ready(function() {
		$.ajax({
				/*url : "${APP_PATH}/getFriendsList",*/
				url : "getFriendsList",
				type : 'post',
				success : function(data) {
				console.log(data)
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
									.addClass("friend")
									.css("cursor",	"pointer")
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
					},

			});
		});