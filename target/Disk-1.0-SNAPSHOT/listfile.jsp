<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hadoop云盘系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/statics/js/bootstrap-3.2.0-dist/css/bootstrap.css" rel="stylesheet"
          media="screen">
</head>
<body>
<div class="container">
    <%@ include file="topbar.jsp" %>

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <form action="file/batchDelete" onsubmit="return del()">
            <tr>
                <td><input type="submit" value="批量删除"></td>
                <td>
                    <meter min="0" max="${maxSize}" low="3" high="8" value="${filesSize}"/>
                    "></meter>(${filesSize}/1G)
                </td>
            </tr>
            <table class="table table-hover">
                <tr>
                    <th><input type="checkbox" onclick="allchoose()" id="allcho">全选</th>
                    <th>文件名</th>
                    <th>操作</th>
                    <th>大小(字节)</th>
                    <th>上传时间</th>
                    <th>副本数</th>
                    <th>所属组</th>
                    <th>数据块大小</th>
                    <th>权限</th>
                </tr>
                <!--开始循环-->
                <c:forEach var="file" items="${infos}" varStatus="status">
                    <tr>
                        <td><input type="checkbox" name="fnames" value="${file.fname}"></td>
                        <td id="td${status.count}">${file.fname}</td>
                        <td>
                            <a href="file/download?fname=${file.fname}"><span class="glyphicon glyphicon-download-alt"
                                                                              title="下载"></span></a>
                            <span class="icon-bar"></span>
                            <a href="file/delete?fname=${file.fname}"><span class="glyphicon glyphicon-remove"
                                                                            title="删除"></span></a>
                            <a href="javascript:rename('${status.count}')"><span class="glyphicon glyphicon-edit"
                                                                                 title="重命名"></span></a>

                        </td>
                        <td>
                                ${file.size}
                        </td>
                        <td>${file.dt}</td>
                        <td>${file.replication}</td>
                        <td>${file.group}</td>
                        <td>${file.blockSize}</td>
                        <td>${file.permission}</td>

                    </tr>
                </c:forEach>
                <!--end 循环-->
            </table>
        </form>
    </div>

</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/jquery-2.1.1.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/statics/js/bootstrap-3.2.0-dist/js/bootstrap.js"></script>
<script type="text/javascript">
    function rename(num) {
        var td = document.getElementById("td" + num);
        var filename = td.innerHTML;
        var newname = prompt("重命名", filename);
        if (newname == null || newname.length == 0) {
            return;
        } else {
            location = "file/rename?oldname=" + filename + "&newname=" + newname;
        }
    }

    function allchoose() {
        var all = document.getElementById("allcho");
        if (all.checked == true) {
            var che = document.getElementsByName("bean.edit");
            for (var i = 0; i < che.length; i++) {
                che[i].checked = true;
            }
        } else {
            var che = document.getElementsByName("bean.edit");
            for (var i = 0; i < che.length; i++) {
                che[i].checked = false;
            }
        }
    }

    function del() {
        if (confirm("确认删除？")) {
            return true;
        }
        return false;

    }
</script>
</body>
</html>