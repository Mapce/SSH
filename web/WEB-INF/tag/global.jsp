<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/13 0013
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}/static" />
<c:set var="staticPath" value="${pageContext.request.contextPath}/static" />
<c:set var="cssBasePath" value="${contextPath}/css" />
<c:set var="jsBasePath" value="${contextPath}/js" />
<c:set var="imgBasePath" value="${contextPath}/image" />



<link href="${cssBasePath}/buttons.css" type="text/css" rel="stylesheet"></link>