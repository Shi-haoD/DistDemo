<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Hadoop云盘系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

<div class="container">

    <%@ include file="topbar.jsp" %>

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <form role="form" action="file/upload" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="file" name="uploads" multiple="true"><span
                    style="color:red;font-size:14px">按住Ctrl可批量上传</span>
            </div>
            <button type="submit" class="btn btn-default">提交</button>
        </form>

    </div>

</div>


<!-- /container -->


</body>
</html>
