<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>闪电存储的云盘-电猫安全云盘</title>

<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css" />
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<link rel="shortcut icon" href="">
</head>
<body>
	<!-- 顶部导航栏 -->
	<div class="dingbudaohang">
		<div class="dingbudaohangfb">
			<div class="dingbu1" style="display: inline">
				<a href=""><img width=150 height=80 src="<%=request.getContextPath()%>/images/logo-hadoop.png"></a>
			</div>
			<ul class="dingbu2">
				<li class="d1"><a href="" id="d11">首页</a></li>
				<li class="d2"><a href="">会员</a></li>
				<li class="d3"><a href="">帮助</a></li>
			</ul>
			<!--<div class="dingbu3">
				<a href="">登录</a><em class="d31">|</em><a
					href="<%=request.getContextPath()%>/Register.jsp">注册</a>
			</div>-->
		</div>
	</div>

	<!-- 登录广告 -->
	<div class="denglu">
		<div class="denglufb">
			<div id="menu">
				<div class="zhubody">
					<div class="deng1">
						<h2>欢迎使用云盘</h2>
					</div>
					<div class="deng2">
						<a style="color: #3B8CFF">hadoop</a>
					</div>
					<form  method="post">
						<div class="deng3">
							<input type="text" id="name" placeholder="请输入您的用户名/邮箱" value="${uname}">
						</div>
						<div class="deng4">
							<input type="password" name="password" id="pwd"
								placeholder="请输入您的密码">
							<div style="height: 30px; width: 500px; margin-left: 20px"
								id="result"></div>
						</div>
						<div class="deng5">
							<div class="deng51">
								<%--<div class="deng511">--%>
									<%--<input type="checkbox">--%>
								<%--</div>--%>
								<%--<div class="deng512">--%>
									<%--<a style="font-size: 12px; color: #999999">下次自动登录</a>--%>
								<%--</div>--%>
							</div>
							
						</div>

						<div class="deng6">
							<input type="button" onclick="login()" value="登录"
								style="cursor: pointer"/>
						</div>
					</form>
					<input type="hidden" value="-1" id="userType" />

					<div class="deng7">
						<a href="${pageContext.request.contextPath }/Register.jsp">注册</a>
					</div>
					<%--<div class="deng8" id="go_phone">--%>
						<%--<a href="#">使用其他方式登录</a>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<div id="menu2">--%>
				<%--<div class="zhubody">--%>
					<%--<div class="deng21">--%>
						<%--<h2>欢迎使用电猫安全云盘</h2>--%>
					<%--</div>--%>
					<%--<div class="deng22">--%>
						<%--<a style="color: orange">手机号用户可通过验证码登录</a>--%>
					<%--</div>--%>
					<%--<form action="LoginTwo_Servlet" method="post">--%>
						<%--<div class="deng23">--%>
							<%--<input type="text" id="phone" name="phone" placeholder="请输入您的手机号"--%>
								<%--required>--%>

						<%--</div>--%>
						<%--<div style="height: 15px; width: 500px; margin-left: 40px"--%>
							<%--id="result2"></div>--%>
						<%--<div class="deng24">--%>
							<%--<input type="text" class="dx" name="massage" id="massage"--%>
								<%--placeholder="请输入短信验证码" required>--%>
							<%--<div id="send">--%>
								<%--<div id="seconds" class="dx1" name="yanzheng"--%>
									<%--style="cursor: pointer">发送验证码</div>--%>
							<%--</div>--%>
						<%--</div>--%>
						<%--<div style="margin-left: 25px" id="second"></div>--%>

						<%--<div class="deng26">--%>
							<%--<input type="button" onclick="logintwo()" value="登录"--%>
								<%--style="cursor: pointer"> <input type="hidden" value="1"--%>
								<%--id="userType1" name="userType1" />--%>
						<%--</div>--%>
					<%--</form>--%>
					<%--<div class="deng27">--%>
						<%--<a class="dsf">第三方帐号登录</a> <a href=""></a><div class="deng271">--%>
								<%--<img src="<%=request.getContextPath()%>/images/qq.png">--%>
							<%--</div> </span><a href="test.jsp" class="qq" id="qqLoginBtn"--%>
							<%--onclick="qqLoginBtn2()">QQ登录</a> <a href=""><div class="deng272">--%>
									<%--<img src="<%=request.getContextPath()%>/images/wx.png">--%>
								<%--</div></a><a class="wx">微信登录</a>--%>
					<%--</div>--%>
					<%--<div class="deng28" id="go_userName">--%>
						<%--<a href="#">使用用户名登录</a>--%>
					<%--</div>--%>
				</div>
			</div>
		</div>
	</div>







</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>

<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/loginphone.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui.js"></script>

<script type="text/javascript"  charset="UTF-8" src="<%=request.getContextPath()%>/js/loginphone.js"></script>
<script type="text/javascript"  charset="UTF-8" src="<%=request.getContextPath()%>/js/login.js"></script>
<script type="text/javascript"  charset="UTF-8" src="<%=request.getContextPath()%>/js/ui.js"></script>

</html>