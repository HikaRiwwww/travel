$(function () {
    var cid = getParameter("cid");
    var keywords = getParameter("keywords");
    if (keywords!=null){
        keywords = window.decodeURIComponent(keywords)
    }

    load(cid, 1, keywords);

});

function load(cid, currentPage, keywords) {
    $.get("route/showRoute", {cid: cid, currentPage: currentPage, keywords:keywords}, function (pb) {
        var totalPage = pb.totalPage;
        var totalCount = pb.totalCount;
        var pageSize = pb.pageSize;
        // 设置分页按钮
        var lis = "";
        var startPage = '<li class="threeword" onclick="load(' + cid + ',' + 1 + ','+'\'' + keywords+'\''+')"><a href="#">首页</a></li>\n';
        var prePage = "";
        if (currentPage > 1) {
            prePage = '<li class="threeword" onclick="load('+cid+','+(currentPage-1)+','+'\'' + keywords+'\''+')"><a href="#">上一页</a></li>';
        } else {
            prePage = '<li class="threeword" onclick="load(' + cid + ',' + 1 + ','+'\'' + keywords+'\''+')"><a href="#">上一页</a></li>';
        }
        lis += startPage += prePage;
        start = 1;
        if ((currentPage - 4) >= start) {
            start = currentPage - 4;
        }
        var end = start + 9;
        if (end > totalPage) {
            end = totalPage;
        }

        for (var i = start; i <= end; i++) {
            var li;
            if (currentPage === i) {
                li = '<li class="curPage" onclick="load('+cid+','+i+','+'\'' + keywords+'\''+')"><a href="#">'+i+'</a></li>';
            } else {
                li = '<li onclick="load('+cid+','+i+','+'\'' + keywords+'\''+')"><a href="#">'+i+'</a></li>';
            }
            lis += li
        }
        var nextPage = "";
        if (currentPage >= totalPage) {
            nextPage = '<li class="threeword" onclick="load('+cid+','+totalPage+','+'\'' + keywords+'\''+')"><a href="#">下一页</a></li>';
        } else {
            nextPage = '<li class="threeword" onclick="load('+cid+','+(currentPage+1)+','+'\'' + keywords+'\''+')"><a href="#">下一页</a></li>';
        }
        var endPage = '<li class="threeword" onclick="load('+cid+','+totalPage+','+'\'' + keywords+'\''+')"><a href="#">末页</a></li>\n';
        lis += nextPage += endPage;
        $(".pageNum > ul").html(lis);

        // 设置页数和记录数
        $("#pageNum").html(totalPage);
        $("#routeNum").html(totalCount);

        // 设置信息列表
        var route_lis = "";
        for (var j = 0; j < pb.list.length; j++) {
            route = pb.list[j];
            route_li = ' <li>\n' +
                '            <div class="img"><img src="' + route.rimage + '" style="width: 299px"></div>\n' +
                '            <div class="text1">\n' +
                '                <p>' + route.rname + '</p>\n' +
                '                <br/>\n' +
                '                <p>' + route.routeIntroduce + '</p>\n' +
                '                </div>\n' +
                '                <div class="price">\n' +
                '                <p class="price_num">\n' +
                '                <span>&yen;</span>\n' +
                '                <span>' + route.price + '</span>\n' +
                '                <span>起</span>\n' +
                '                </p>\n' +
                '                <p><a href="route_detail.html?rid='+route.rid+'">查看详情</a></p>\n' +
                '                </div>\n' +
                '         </li>\n';
            route_lis += route_li;
        }
        $('#route_list').html(route_lis);
        window.scrollTo(0, 0);
    })

}