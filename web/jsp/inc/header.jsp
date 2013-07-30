<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div id="container">
		<div><a href="/index.do"><img src="/images/logo.jpg"  alt="logo" height="30px" width="50px"/></a></div>
		<div style="text-align:right"><a href="post/post.do">发表</a>&nbsp;<a href="user/login.do">登录</a></div><hr/>
		<div><a href="post/today.do">今日</a>|<a href="post/top.do?type=1">最搞</a>|<a href="post/top.do?type=2">最衰</a>|<a href="post/top.do?type=3">最新</a>|<a href="post/top.do?type=4">热评</a>|<a href="post/share.do">分享</a></div><hr/>
		<c:forEach var="link" items="${ad1}">
		<div>
			<a href="<c:out value="${link.link}"/>"><c:out value="${link.title}"/></a><br/>
		</div>
		</c:forEach>
		<hr/>