var TimeFn = null;
//点击添加好友
$("#add_friend_div").click(function() {
    var searchFriendName = $("#searchFriendName").val();
    if (searchFriendName == "" || searchFriendName == null) {
        alert("请输入要添加的好友！");
    } else {
        $.ajax({
            url : "addFriend",
            data : "friendName=" + searchFriendName,
            type : 'post',
            success : function(data) {
                alert(data.msg);
                if (data.msg == "添加好友成功!") {
                    ws.close();
                    window.location.href = "index";
                }
            },
        });
        $("#searchFriendName").val("");
    }
});
