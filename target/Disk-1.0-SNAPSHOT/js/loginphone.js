//登录
var name;
var pwd;

$(function () {
    $("#name").blur(function () {
        name = $("#name").val();
        if (name == "") {
            $("#result").html('').css("color", "red").append("用户名不能为空");
            return false;
        } else
            $("#result").html('');
    })
    $("#pwd").blur(function () {
        pwd = $("#pwd").val();
        if (pwd == "") {
            $("#result").html('').css("color", "red").append("密码不能为空");
            return false;
        }
        if (pwd.length < 6) {
            $("#result").html('').css("color", "red").append("密码长度要不小于6位");
            return false;
        } else
            $("#result").html('');
    })
})

function login() {
    name = $.trim($("#name").val());
    pwd = $.trim($("#pwd").val());
    $.post("/user/login", {
        "name": name,
        "pwd": pwd
    }, function (result) {
        if (result == null) {
            alert('null')
            $("#result").html('').css("color", "red").append("用户名或密码错误");
        } else {
            window.location.href = "index.jsp";
        }
    }, "json")
}
// $(function () {
//     $("[name='phone']")
//         .blur(
//             function () {
//                 var userPhone = $.trim($("[name='phone']").val());
//                 if (userPhone == "") {
//                     $("#result2").css("color", "red").html("请输入手机号！");
//                     return false;
//                 }
//                 if ($(this).is('#phone')) {
//                     phone = $("#phone").val();
//                     if (this.value == ""
//                         || (this.value != "" && !(/^1[3|4|5|8][0-9]\d{4,8}$/)
//                             .test(this.value))) {
//                         $('#result2').css('color', 'red').html(
//                             '请输入正确手机号');
//                     } else {
//
//                         var user = {
//                             keyWord: $("#phone").val()
//                         };
//                         $.ajax({
//                             url: "/user/login",
//                             data: user,
//                             async: true,
//                             type: "POST",
//                             dataType: "html",
//                             success: function (result) {
//                                 if (result == 1) {
//                                     $("#result2").css('color', 'green')
//                                         .html('该手机号可以登陆');
//                                 } else {
//                                     $("#result2").css('color', 'red')
//                                         .html('未注册');
//                                 }
//                             }
//
//                         });
//                     }
//                 }
//
//             })
// })

// function logintwo() {
//     phone = $.trim($("#phone").val());
//     var massage = $.trim($("#massage").val());
//     var userType1 = 1;
//     var type = 1;
//     $.post("/user/login", {
//         "phone": phone,
//         "massage": massage,
//         "type": type,
//         "userType": userType1
//     }, function (result) {
//         if (result == null) {
//             $("#result2").html('').css("color", "red").append("手机号或验证码错误");
//             mizhu.alert('信息错误', '验证码或手机号输入错误,请手机号验证通过后请求发送短信,之后请刷新页面',
//                 'alert_red');
//             $("#phone").val('')
//         } else {
//             window.location.href = "index.jsp";
//         }
//     }, "json")
// }
