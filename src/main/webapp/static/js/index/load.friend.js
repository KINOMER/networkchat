//设置一个map用户全局存放好友信息
var friends_map = new Map([ [ "key", "value" ] ]);
//页面加载时发送ajax请求，请求到用户的好友信息
$(document).ready(function() {
    $.ajax({
        //url : "${APP_PATH}/getFriendsList",*/
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
                    if(friend.avator != null){
                        var imgEle = $("<img></img>")
                            .attr("src","data:image/jpeg;base64,"+ friend.avator).addClass("grey");
                    }else{
                        var imgEle = $("<img></img>")
                            .attr("src","/static/pic/default.jpg").addClass("grey");
                    }

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
                        .addClass("chat-box-border");
                    /*css({
                        'border': '1px solid rgb(204, 204, 204)',
                    });*/

                    var ul_ele = $("<ul></ul>")
                        .addClass("chat_content")
                        .addClass("am-comments-list am-comments-list-flip");
                    ul_ele.appendTo(chat_box_ele);
                    chat_box_ele.appendTo($("#all_chat_div"));
                    $(".chatBox").hide();
                });
            //console.log(friendsList)
            //当一个用户上线时，如果该用户有上线用户的好友，好友列表显示在线
            $.each( friendsList, function(index,item){
                var mapUser = friends_map.get(item);
                if(mapUser != undefined){
                    $("li[friend_name = '"+ mapUser.username+"']").css('background-color','#f6f6f6\'');
                    $("li[friend_name = '"+ mapUser.username+"']").children().eq(0).children().eq(1).removeClass("grey");
                    $("li[friend_name = '"+ mapUser.username+"']").attr('status','1');
                }

            });
        },

    });
});
