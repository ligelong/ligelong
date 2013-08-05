<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><?xml version="1.0" encoding="utf-8"?><!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width"/>
	<meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0" /> 
	<link rel="stylesheet" type="text/css"　media="screenand (min-width: 320px) and (max-device-width: 640px)" href="/style/css.css"/>
</head>
<body>
<%@include file="../inc/header.jsp"%>
<c:if test="${!empty info}">
<c:out value="${info}"/><br/>
</c:if>
		登录成功，正在<a href="<c:out value="${url}"/>">跳转</a>
	</div>
	<hr/>
<%@include file="../inc/tail.jsp"%>
