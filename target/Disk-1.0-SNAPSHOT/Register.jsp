<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册-电猫安全网盘</title>
    <link href="<%=request.getContextPath()%>/css/Register.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
    <link rel="shortcut icon" href="">
</head>

<body>
<div class="all">
    <div class="dingbudaohang">
        <div class="dingbudaohangfb">
            <div class="dingbu1">
                <a href=""><img width=150 height=80 src="<%=request.getContextPath()%>/images/logo-hadoop.png"></a>
            </div>
            <!--<div class="dingbu2">
                <div class="dingbu21">
                    <a href="">注册帐号</a>
                </div>
            </div>
            <div class="dingbu3">
                <a href="index2.jsp">登录</a>
            </div>-->
        </div>
    </div>

    <!--注册-->
    <div class="zhuce_all">
        <div class="zhuce1">
            <div class="zhuce11">
                <a style="font-size: 18px; color: #333333">注册Hadoop网盘账号</a>
            </div>
        </div>
        <div class="zhuce_yonghu">
            <div
                    style="height: 30px; position: absolute; left: 662px; top: 250px; width: 200px; margin-left: -99px; margin-top: 10px"
                    id="result5"></div>
            <div class="zhuce_yonghu1">
						<span> <label class="yonghutubiao"></label>
						</span>

                <div class="shuru">

                    <input type="text" id="userName" name="userName"
                           placeholder="请输入要注册的用户名" required=""/>
                    <div
                            style="height: 30px; width: 200px; margin-left: 5px; margin-top: 10px"
                            id="result1"></div>

                </div>
            </div>

            <div class="zhuce_yonghu2">
						<span> <label class="yonghutubiao2"></label>
						</span>
                <div class="shuru">
                    <input type="password" name="password" id="password"
                           placeholder="请输入要注册的密码" required=""/><br/>
                    <div
                            style="height: 30px; width: 200px; margin-left: 5px; margin-top: 6px"
                            id="result2"></div>
                </div>
            </div>
            <div class="zhuce_yonghu3">
						<span> <label class="yonghutubiao2"></label>
						</span>
                <div class="shuru">
                    <input type="password" name="rePassword" id="rePassword"
                           placeholder="请第二次输入密码" required=""/> <br/>
                    <div
                            style="height: 30px; width: 200px; margin-left: 5px; margin-top: 6px"
                            id="result3"></div>
                </div>
            </div>
            <%--<div class="zhuce_yonghu4">--%>
						<%--<span> <label class="yonghutubiao3"></label>--%>
						<%--</span>--%>
                <%--<div class="shuru">--%>
                    <%--<input type="text" name="phone" maxlength="11"--%>
                           <%--placeholder="请输入要注册的手机号" id="phone" required=""/><br/>--%>
                    <%--<div--%>
                            <%--style="height: 30px; width: 200px; margin-left: 5px; margin-top: 6px"--%>
                            <%--id="result4"></div>--%>
                    <%--<div--%>
                            <%--style="height: 30px; width: 200px; margin-left: 5px; margin-top: 6px"--%>
                            <%--id="result6"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <input type="hidden" value="-1" id="userType"/>
            <div class="zhuce24">
                <input type="hidden" name="userType" value="-1"/>
                <button id="add" name="add" class="xiayibu" onclick="res()">提交</button>
            </div>
            <div class="zhuce25">
                <div class="zhuce251">
                    <input type="checkbox" name="" checked="checked">
                </div>
                <div class="zhuce252">
                    <p style="color: #959393">
                        阅读并同意 <a href="" style="color: #FA6924">“电猫网盘用户协议”</a>和 <a href=""
                                                                                   style="color: #FA6924">“电猫网盘用户隐私政策”</a>
                    </p>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath }/js/Resgister.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath }/js/ui.js"></script>
</html>