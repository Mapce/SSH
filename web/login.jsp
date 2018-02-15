<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/13 0013
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tag/global.jsp"%>
<html>
<link href="/static/css/public.css" type="text/css" rel="stylesheet">
<head>
    <title>登陆</title>
</head>
<body>
<jsp:include page="/public/topMenu.htm"></jsp:include>
<div class="login_bg">
        <form id="login_form" name="create_account" action="${basePath}/login.htm" method="POST">
            <div id="login_box">
                <div class="login_main">
                    <div class="login_row">
                        <div class="form_area">
                            <label class="label">用户名或邮箱</label>
                            <input class="username" placeholder="请输入用户名或邮箱" type="text" maxlength="64" name="username" id="username" >
                        </div>
                    </div>
                    <div class="login_row">
                        <div class="form_area">
                            <label class="label">请输入密码</label>
                            <input type="password" maxlength="64" name="password" id="password" onkeyup="CheckPasswordStrength()" autocomplete="off">
                        </div>
                        <div class="form_notes">
                            <div id="password_tag" class="password_tag">&nbsp;</div>
                        </div>
                        <div style="clear: left;"></div>
                    </div>
                </div>
            </div>
            <input type="submit" class="button button-highlight button-large" value="登陆"></input>
            <a href="${basePath}/register.htm" class="button button-highlight button-large">注册</a>
        </form>

</div>
</body>
</html>
