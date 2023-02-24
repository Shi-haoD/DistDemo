<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hadoop云盘系统</title>
</head>
<body>
	<div class="container">
		<%@ include file="topbar.jsp"%>
		
		<div class="jumbotron">
		    <form action="search" method="post">
				<input type="text" name="search" />&nbsp;&nbsp;&nbsp;<input type="submit" value="搜索"> 
			</form>
		</div>

	</div>
</body>
</html>