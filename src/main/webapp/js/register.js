$(function () {
    $("#registerForm").submit(function () {
        if(checkUsername() && checkPassword() && checkEmail() && checkName() && checkPhoneNumber() && checkBirth() && checkVerifyCode()){
            $.post("user/userRegister", $(this).serialize(), function (data) {
                // data {flag: true, errorMsg:"}
                console.log(data);
                if(data.flag){
                    // 注册成功
                    location.href = "register_ok.html";
                }
            })
        }
        return false;
    });
    $("#username").blur(function () {
        checkUsername()
    });
    $("#password").blur(function () {
        checkPassword()
    });
    $("#email").blur(function () {
        checkEmail()
    });
    $("#name").blur(function () {
        checkName()
    });
    $("#telephone").blur(function () {
        checkPhoneNumber()
    });
    $("#birthday").blur(function () {
        checkBirth()
    });
    $("#check").blur(function () {
        checkVerifyCode()
    })
});


function checkUsername() {
    flag = false;
    var startProhib = "1234567890_";
    var username = $("#username");
    var len = username.val().length;
    if (len >= 6 && len <= 20) {
        flag = startProhib.indexOf(username[0]) === -1;
    }
    if (!flag) {
        username.css("border", "2px solid red");
    } else {
        username.css("border", "");

    }
    return flag
}


function checkPassword() {
    flag = false;
    var password = $("#password");
    var passwordText = password.val();
    var len = passwordText.length;
    var capital = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var capitalFlag = false;
    if (len >= 6 && len <= 20) {
        for (i = 0; i < capital.length; i++) {
            if (passwordText.indexOf(capital[i]) !== -1) {
                capitalFlag = true;
            }
        }
        flag = capitalFlag;
    }
    if (!flag) {
        password.css("border", "2px solid red");
    } else {
        password.css("border", "");
    }
    return flag
}


function checkEmail() {
    var flag = false;
    var email = $("#email");
    var emailText = email.val();
    if (!emailText.startsWith("@")) {
        if (emailText.endsWith(".com") || emailText.endsWith(".cn")) {
            var count = 0;
            for (i = 0; i < emailText.length; i++) {
                if (emailText[i] === "@") {
                    count += 1
                }
            }
            flag = (count === 1);
        }
    }
    if (!flag) {
        email.css("border", "2px solid red");
    } else {
        email.css("border", "");
    }
    return flag
}

function checkName() {
    var name = $("#name");
    var nameText = name.val();
    var flag = (nameText.length !== 0);
    if (!flag) {
        name.css("border", "2px solid red");
    } else {
        name.css("border", "");
    }
    return flag

}

function checkPhoneNumber() {
    var flag = false;
    var phoneNum = $("#telephone");
    var phoneNumText = phoneNum.val();
    var nums = "1234567890";
    if (phoneNumText.length === 11) {
        for (var num of phoneNumText) {
            if (nums.indexOf(num) !== -1) {
                flag = true
            } else {
                flag = false;
                break
            }
        }
    }

    if (!flag) {
        phoneNum.css("border", "2px solid red");
    } else {
        phoneNum.css("border", "");
    }
    return flag
}


function checkBirth() {
    var birth = $("#birthday");
    var birthText = birth.val();
    var flag = (birthText.length !== 0);
    if (!flag) {
        birth.css("border", "2px solid red");
    } else {
        birth.css("border", "");
    }
    return flag
}

function checkVerifyCode() {
    var code = $("#check");
    var codeText = code.val();
    var flag = (codeText.length !== 0);
    if (!flag) {
        code.css("border", "2px solid red");
    } else {
        code.css("border", "");
    }
    return flag
}