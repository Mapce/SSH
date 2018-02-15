<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/13 0013
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tag/global.jsp" %>

<html>
<link href="${cssBasePath}/public.css" type="text/css" rel="stylesheet"></link>
<script src="${jsBasePath}/jquery-3.3.1.min.js" ></script>
<body>
<div class="top_menu_bg">
<div class="top_menu">
    <div>
        <ul class="top_menu_ul">
            <li class="this" >
                <a href="${basePath}/welcome.htm" >首页</a>
            </li>
            <li>
                <a href="https://www.taptap.com/top/download">排行榜</a>
            </li>
            <li>
                <a href="https://www.taptap.com/review/new">安利墙</a>
            </li>
            <li>
                <a href="https://www.taptap.com/categories">发现</a>
            </li>
            <li>
                <a href="https://www.taptap.com/forum">动态</a>
            </li>
          <c:choose>
              <c:when test="${user==null}">
            <li style="float: right;">
                <a href="${basePath}/login.htm">登录</a>
            </li>
              </c:when>
              <c:when test="${user!=null}">
                  <li style="float: right;">
                      <a id="top_userLabel">${user.name}</a>
                      <ul id="top_drop_menu" style="display: none">
                          <a href="${basePath}/account/personalCenter.htm"><li>个人中心</li></a>
                      </ul>
                  </li>

              </c:when>
          </c:choose>
        </ul>
    </div>

    <div class="navbar-mask" data-taptap-slide="nav" data-target="#navbarSlide"></div>
</div>
</div>
</body>
</html>
<script>
    $(document).ready(function(){
        $("#top_userLabel").click(function () {
            toggleUserDrop_menu();
        })
    });
    function toggleUserDrop_menu() {
        $("#top_drop_menu").toggle();
    }
</script>
