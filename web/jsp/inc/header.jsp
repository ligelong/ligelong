<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><?xml version="1.0" encoding="utf-8"?><!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width"/>
	<meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0" /> 
	<link rel="stylesheet" type="text/css"　media="screenand (min-width: 320px) and (max-device-width: 640px)" href="/style/css.css"/>
	<title>就是搞笑</title>
</head>
<body>
	<div id="container">
		<div><a href="/index.do"><img src="/images/logo.png"  alt="logo" style="height:90px;width:320px"/></a></div>
		<div style="text-align:right"><a href="post/add.do">发表</a>&nbsp;<%
			com.ligelong.hibernate.entity.UserEntity user = (com.ligelong.hibernate.entity.UserEntity)request.getAttribute("user");
			if(user!=null) {
				out.print(user.getUsername());
			} else {
		%><a href="user/login.do">登录</a><%
			}
		%></div><hr/>
		<div><a href="post/today.do">今日</a>|<a href="post/top.do?type=1">最搞</a>|<a href="post/top.do?type=2">最衰</a>|<a href="post/top.do?type=3">最新</a>|<a href="post/top.do?type=4">热评</a>|<a href="post/share.do">分享</a></div><hr/>
		<c:forEach var="link" items="${ad1}">
		<div>
			<a href="<c:out value="${link.link}"/>"><c:out value="${link.title}"/></a><br/>
		</div>
		</c:forEach>
		<hr/>