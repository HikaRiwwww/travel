$(function () {
    var rid = getParameter("rid");
    $.get("detail/getRouteDetail", {"rid": rid}, function (route) {
        var rname = route.rname;
        var price = route.price;
        var intro = route.routeIntroduce;
        var imgList = route.routeImgList;
        $("#title_href").html(rname);
        $(".pros_title").html(rname);
        $(".hot").html(intro);
        $(".price").html("<strong>¥"+price+"</strong><span>起</span>")

        /*
        *<a title="" class="little_img" data-bigpic="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size4/201703/m40920d0669855e745d97f9ad1df966ebb.jpg">
          <img src="http://www.jinmalvyou.com/Public/uploads/goods_img/img_size2/201703/m20920d0669855e745d97f9ad1df966ebb.jpg"></a>
        * */
        img_html = '<a class=\"up_img\"></a>'
        for (var i = 0; i < imgList.length ; i++) {
            little_src = imgList[i].smallPic
            big_src = imgList[i].bigPic
            if (i >= 4){
                img_html += '<a title="" class="little_img" data-bigpic=\"travel/'+big_src+'\" style="display:none;">\n' +
                    '<img src=\"'+ little_src + '\">\n' +
                    '</a>\n'
            }else {
                img_html += '<a title="" class="little_img" data-bigpic=\"travel/'+big_src+'\">\n' +
                    '<img src=\"'+ little_src + '\">\n' +
                    '</a>\n'

            }
        }
        img_html+='<a class="down_img" style="margin-bottom: 0;"></a>'
        $('.prosum_left dd').html(img_html)
        goImg()
    });
});


function auto_play() {
    var cur_index = $('.prosum_left dd').find('a.cur_img').index();
    cur_index = cur_index - 1;
    var num = $('.little_img').length;
    var max_index = 3;
    if ((num - 1) < 3) {
        max_index = num - 1;
    }
    if (cur_index < max_index) {
        var next_index = cur_index + 1;
        var big_pic = $('.little_img:eq(' + next_index + ')').data('bigpic');
        $('.little_img').removeClass('cur_img');
        $('.little_img:eq(' + next_index + ')').addClass('cur_img');
        $('.big_img').attr('src', big_pic);
    } else {
        var big_pic = $('.little_img:eq(0)').data('bigpic');
        $('.little_img').removeClass('cur_img');
        $('.little_img:eq(0)').addClass('cur_img');
        $('.big_img').attr('src', big_pic);
    }
}

function goImg() {
    $(document).ready(function() {
        //焦点图效果
        //点击图片切换图片
        $('.little_img').on('mousemove', function() {
            $('.little_img').removeClass('cur_img');
            var big_pic = $(this).data('bigpic');
            $('.big_img').attr('src', big_pic);
            $(this).addClass('cur_img');
        });
        //上下切换
        var picindex = 0;
        var nextindex = 4;
        $('.down_img').on('click',function(){
            var num = $('.little_img').length;
            if((nextindex + 1) <= num){
                $('.little_img:eq('+picindex+')').hide();
                $('.little_img:eq('+nextindex+')').show();
                picindex = picindex + 1;
                nextindex = nextindex + 1;
            }
        });
        $('.up_img').on('click',function(){
            var num = $('.little_img').length;
            if(picindex > 0){
                $('.little_img:eq('+(nextindex-1)+')').hide();
                $('.little_img:eq('+(picindex-1)+')').show();
                picindex = picindex - 1;
                nextindex = nextindex - 1;
            }
        });
        //自动播放
        // var timer = setInterval("auto_play()", 5000);
    });

}