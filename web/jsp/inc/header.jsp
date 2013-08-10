<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div id="container">
		<div><a href="/index.do"><img src="/images/logo.png"  alt="logo" height="90px" width="320px"/></a></div>
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