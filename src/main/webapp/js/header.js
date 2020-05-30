$(function () {
    $.get("user/currentUser", {}, function (data) {
        if (data == null) {
            $(".login").hide()
        } else {
            $(".login_out").hide();
            var greeting = "欢迎回来，" + data.username + "!";
            $(".login > span").html(greeting)
        }
    });

    $.get("category/getAllCategories", {}, function (data) {
        var nav = $(".nav");
        var lis = '<li class="nav-active"><a href="index.html">首页</a></li>\n';
        for (var i = 0; i < data.length; i++) {
            lis += ('<li><a href=route_list.html?cid=' + data[i].cid + '>' + data[i].cname + '</a></li>\n')
        }
        lis += ('<li><a href="favoriterank.html">收藏排行榜</a></li>\n');
        nav.html(lis);

    })

});


function searchRoute() {
    var searchStr = $(".search_input").val();
    if(searchStr!=null && searchStr.length>0){
        searchStr = window.decodeURIComponent(searchStr);
        location.href = "route_list.html?keywords=" + searchStr;
    }
}
function enterToSearch(event) {
    if(event.keyCode===13){
        $(".search-button").click();
    }
}
