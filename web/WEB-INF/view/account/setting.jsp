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
    <link href="${cssBasePath}/pickdate/pikaday.css" type="text/css" rel="stylesheet">
    <link href="${cssBasePath}/pickdate/site.css" type="text/css" rel="stylesheet">
    <script src="${jsBasePath}/jquery-3.3.1.min.js"></script>
    <script src="${jsBasePath}/moment.min.js"></script>
    <script src="${jsBasePath}/pikaday.js"></script>

</head>
<body>
<jsp:include page="${basePath}/public/topMenu.htm"></jsp:include>
<div class="personal_main">
    <jsp:include page="${basePath}/account/main_left.htm"></jsp:include>
    <div class="personal_setting">
        <h3 class="personal_setting_h3">个人信息</h3>
        <form id="theForm" action="${basePath}/account/save_setting.htm">
            <input type="hidden" name="id" value="${user.id}">
            <ul>
                <li>
                    <label>名称</label>
                    <input type="text" name="name" value="${user.name}">
                </li>

                <li>
                    <label>性别</label>
                    <input type="radio" name="sex" class="sexBtn" value="1"> 男
                    <input type="radio" name="sex" class="sexBtn" value="2"> 女
                    <input type="radio" name="sex" class="sexBtn" value="3"> 保密
                </li>

                <li>
                    <label>邮箱</label>
                    <input type="text" name="mail" value="${user.mail}">
                </li>

                <li>
                    <label>手机号</label>
                    <input type="text" name="telenumber" value="${user.telenumber}">
                </li>

                <li>
                    <label>生日</label>
                    <input type="text" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月DD日" />" id="datepicker">
                </li>
                <li>
                    <a onclick="submit_form()"
                       class="button button-action button-rounded" style="background-color:#e59501;">
                       保存
                    </a>
                </li>

            </ul>
        </form>
    </div>

</div>
</body>
</html>
<script>
    $(document).ready(function () {
        var sex = "${user.sex}";
        $(".sexBtn").each(function () {
            var btnVal = $(this).val();
            if (sex == btnVal) {
                $(this).click();
            }
        })
        //时间格式处理


        initPickaJs();
    })
    var i18n = { // 本地化
        previousMonth : '上个月',
        nextMonth   : '下个月',
        months      : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
        weekdays    : ['周日','周一','周二','周三','周四','周五','周六'],
        weekdaysShort : ['日','一','二','三','四','五','六']
    }
    function initPickaJs() {
        var i18n = { // 本地化
            previousMonth : '上个月',
            nextMonth   : '下个月',
            months      : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            weekdays    : ['周日','周一','周二','周三','周四','周五','周六'],
            weekdaysShort : ['日','一','二','三','四','五','六']
        }
        var picker = new Pikaday(
            {
                field: document.getElementById('datepicker'),
                firstDay: 1,
                i18n:i18n,
                minDate: new Date('2000-01-01'),
                maxDate: new Date('2020-12-31'),
                yearRange: [2000, 2050],
                format: 'YYYY年MM月DD日',//这里面是改变日期显示的格式的，YYYY年MM月DD日 ,想要什么格式的就在这里面改
            });
    }
    function submit_form() {
        $("#theForm").submit();
    }
</script>