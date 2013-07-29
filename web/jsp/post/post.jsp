<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><?xml version="1.0" encoding="utf-8"?><!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width"/>
	<meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0" /> 
	<link rel="stylesheet" type="text/css"　media="screenand (min-width: 320px) and (max-device-width: 640px)" href="/style/css.css"/>
	<title>就是搞笑</title>
	<script language="javascript">
		function submitform() {
			document.getElementById('commentform').submit();
		}
	</script>
</head>
<body>
<%@include file="../inc/header.jsp"%>
<%
	Object pagenumber = request.getAttribute("pagenumber");
	com.ligelong.hibernate.entity.PostEntity post = (com.ligelong.hibernate.entity.PostEntity)request.getAttribute("post");
	if(post==null) {
		out.print("<div>这条笑话不见了</div>");
	} else {
		String c = post.gainTextFromContent();
		out.print("<div>" + c + "</div>");
		out.print("<a href='post.do?up=" + post.getId() + "'>↑</a>" + post.getUpcount() + "|<a href='post.do?down=" + post.getId() + "'>↓</a>" + post.getDowncount() + "|" + post.getCommentcount() + "条评论|" + "<a href=''>转发</a>");
	}
%>	
<form action="post.do" method="post" id="commentform">
<input type="hidden" name="id" value="<c:out value="${post.id}"/>"/>
<input type="hidden" name="action" value="on"/>
<input type="text" name="content" />&nbsp;<a href="#" onclick="submitform()">提交评论</a>
</form>
<c:forEach var="comment" items="${comments}">
<div><c:out value="${comment.content}"/>&nbsp;&nbsp;&nbsp;<c:out value="${comment.createtime}"/></div>
</c:forEach>
<%@include file="../inc/tail.jsp"%>