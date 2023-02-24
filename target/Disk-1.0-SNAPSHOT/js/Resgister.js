var userName = null;
var password = null;
var rePassword = null;
var phone = null;
var userType = null;
$(function() {
	userName = $.trim($("#userName").val());
	password = $.trim($("#password").val());
	rePassword = $.trim($("#rePassword").val());
	phone = $.trim($("#phone").val());
	userType = $.trim($("#userType").val());
	$(':input')
			.blur(
					function() {
						// if ($(this).is('#userName')) {
						// 	if (this.value == "" || this.value.length < 6
						// 			|| this.value.length > 18) {
						// 		var errorMsg = '请输入6-18位的用户名.';
						// 		$('#result1').css('color', 'red').html(
						// 				'请输入6-18位的用户名');
						// 	} else {
						// 		var okMsg = '输入正确.';
						// 		$('#result1').css('color', 'green')
						// 				.html('输入正确');
                        //
						// 		var user = {
						// 			userName : $("#userName").val()
						// 		};
						// 		$.ajax({
						// 			url : "/user/login",
						// 			data : user,
						// 			async : true,
						// 			type : "POST",
						// 			dataType : "html",
						// 			success : function(result) {
						// 				if (result == 1) {
						// 					$("#result5").css('color', 'red')
						// 							.html('该用户名已经被注册');
						// 				} else {
						// 					$("#result5").css('color', 'green')
						// 							.html('未注册');
						// 				}
                        //
						// 			}
                        //
						// 		});
                        //
						// 	}
                        //
						// }
						if ($(this).is('#password')) {
							if (this.value == ""
									|| (this.value != "" && !(/^\d{5,10}$/)
											.test(this.value))) {
								$('#result2').css('color', 'red').html(
										'密码必须是5-10位数字');
							} else {
								$('#result2').css('color', 'green')
										.html('验证通过');
							}
						}
						// if ($(this).is('#rePassword')) {
						// 	if (password != rePassword) {
						// 		$('#result3').css('color', 'red').html(
						// 				'两次输入的密码不一致');
						// 	} else {
						// 		$('#result3').css('color', 'green')
						// 				.html('两次一致');
						// 	}
						// }
						// if ($(this).is('#phone')) {
						// 	phone = $("#phone").val();
						// 	if (this.value == ""
						// 			|| (this.value != "" && !(/^1[3|4|5|8][0-9]\d{4,8}$/)
						// 					.test(this.value))) {
						// 		$('#result4').css('color', 'red').html(
						// 				'请输入正确手机号');
						// 	} else {
						// 		$('#result4').css('color', 'green').html('正确');
						// 		var user = {
						// 			keyWord : $("#phone").val()
						// 		};
						// 		$.ajax({
						// 			url : "UserController/user/login",
						// 			data : user,
						// 			async : true,
						// 			type : "POST",
						// 			dataType : "html",
						// 			success : function(result) {
						// 				if (result == 1) {
						// 					$("#result6").css('color', 'red')
						// 							.html('该手机号已经被注册');
						// 				} else {
						// 					$("#result6").css('color', 'green')
						// 							.html('未注册');
						// 				}
						// 			}
                        //
						// 		});
						// 	}
						// }

					})
})
function res() {
    userName = $.trim($("#userName").val());
    password = $.trim($("#password").val());
    // mizhu.confirm('', '是否要注册用户？', function(result) {
    //     if(result) {
    //         mizhu.alert('温馨提醒', '注册成功');
    //     }
    // });
    $.post("/user/register", {
        "userName" : userName,
        "password" : password
    }, function(result) {
        if (result == null) {
			alert('1')
        }else {
            window.location.href = "index2.jsp";
		}
    }, "json")


}
