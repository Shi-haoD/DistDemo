$(function() {

	$('#go_phone').click(function() {
		$('#menu').hide();
		$('#menu2').show();
	})
	$('#go_userName').click(function() {

		$('#menu2').hide();
		$('#menu').show();
	})

})

function setMassage() {
	var type = -2;
	phone = $.trim($("#phone").val());
	if (phone == null) {
		mizhu.alert('手机号出错', '手机号不能为空','alert_green');
	} else {
		$.post("/Cloud_Disk/LoginTwo_Servlet", {
			"type" : type,
			"phone" : phone
		}, function(result) {
			if (result != null) {
				$("#massage").val(result)
			} else {

			}
		}, "json")
	}

}

// 短信验证码计时
var sb;
var seconds = 60; // 设定计时时间60
var timestarter; // 定时器，后续停止计时要用到

function getNowTime() {
	var sta = new Date();
	sb = sta.getTime();
	timestarter = setInterval("timer()", 1000);
}
var tim = 0;
function timer() {
	var sta = new Date();
	var wy = sta.getTime(); // 获取当前时间

	var time = seconds - parseInt((wy - sb) / 1000);
	if (time <= 0) {
		tim = 0;
		document.getElementById("seconds").innerHTML = '发送短信验证码';
		clearInterval(timestarter);
	} else if (time < 10) {
		document.getElementById("seconds").innerHTML = '0' + time + 's后可重新发送';
	} else {
		document.getElementById("seconds").innerHTML = +time + 's后可重新发送';
	}
}
$(function() {
	$('#send').click(function() {
		var phone = $.trim($("#phone").val());
		if (phone == null || phone == "") {
			mizhu.alert('发送验证失败', '手机号不能为空','alert_green');
		} else {
			var user = {
				keyWord : $("#phone").val()
			};
			$.ajax({
				url : "/Cloud_Disk/UniquePhone_Servlet",
				data : user,
				async : true,
				type : "POST",
				dataType : "html",
				success : function(result) {
					if (result != 0) {
						if (tim == 0) {
							tim = 1;
							getNowTime();
							setMassage();
						}
					} else {
						mizhu.alert('手机号出错', '手机号未注册','alert_green');
					}
				}

			});

		}

	})

})