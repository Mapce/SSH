<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/14 0014
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tag/global.jsp" %>
<html>
<head>
    <title>个人中心</title>
</head>
<body>
<jsp:include page="${basePath}/public/topMenu.htm"></jsp:include>
<div class="personal_main">

    <jsp:include page="${basePath}/account/main_left.htm"></jsp:include>
    <div class="personal_setting">
        <form>

            <ul></ul>
        </form>
    </div>
</div>
</body>
</html>
