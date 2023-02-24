<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
<base href="<%=basePath%>">
<title>Hadoop云盘系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/statics/js/my.css" rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/statics/js/bootstrap-3.2.0-dist/css/bootstrap.css" rel="stylesheet" media="screen">
</head>

<body>
      <!-- Static navbar -->
      <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Hadoop模拟网盘功能</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">云存储 <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="file/queryFiles">我的文件</a></li>
                  <li><a href="upload.jsp">上传文件</a></li>
                </ul>
              </li>
              <li><a href="search.jsp">搜索</a></li>
              <li><a href="#">Link</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Action <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Action1</a></li>
                  <li><a href="#">Action2</a></li>
                  <li><a href="#">Action3</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-header">Action4</li>
                  <li><a href="#">Action5</a></li>
                  <li><a href="#">Action6</a></li>
                </ul>
              </li>
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
            
              <div class="navbar-collapse collapse">
              <c:if test="${empty uname}">
          		<form class="navbar-form navbar-right" role="form" action="user/login" method="post">
            		<div class="form-group">
             			 <input name="name" required type="text" placeholder="用户名" class="form-control">
            		</div>
            		<div class="form-group">
             			 <input name="pwd" required type="password" placeholder="密码" class="form-control">
            		</div>
            		<button type="submit" class="btn btn-success">登录</button> ${msg}
            		<button formaction="user/register" type="submit" class="btn btn-success">注册</button>
          		</form>
              </c:if>
                  <c:if test="${not empty uname}">
					<form class="navbar-form navbar-right" role="form" action="user/logout" method="get">
						<div class="form-group">
             				 <label>你好，${uname}</label>
            			</div>
          		 		<button type="submit" class="btn btn-success">注销</button>
          		 	</form>
                  </c:if>
        		</div>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <script src="<%=request.getContextPath()%>/statics/js/jquery-2.1.1.js"></script>
    <script src="<%=request.getContextPath()%>/statics/js/bootstrap-3.2.0-dist/js/bootstrap.js"></script>
  </body>
</html>