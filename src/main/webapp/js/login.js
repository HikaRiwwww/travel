$(function () {
    $(".submit_btn").click(function () {
        $.post("user/userLogin", $("#loginForm").serialize(), function (data) {
            if (!data.flag) {
                $(function () {
                    $("#codeImg > img").click();
                });
                $("#errorMsg").html(data.errorMsg);
            }else {
                location.href="index.html";
            }
        })
    })
});