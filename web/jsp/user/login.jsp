<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.do"/>
<c:if test="${!empty info}">
<c:out value="${info}"/><br/>
</c:if>
	<script language="javascript">
		function submitform() {
			document.getElementById('loginform').submit();
		}
	</script>
		<form id="loginform" action="login.do" method="post">
		<input type="hidden" name="action" value="on"/>
		<div>用户名：<input type="text" name="username" /></div>
		<div>密码：<input type="password" name="pwd" /></div>
		<hr/>
		<div><a href="#" onclick="submitform()">登录</a>&nbsp;<a href="../user/reg.do">注册</a></div>
		</form>
		<c:forEach var="link" items="${ad2}">
		<div>
			<a href="<c:out value="${link.link}"/>"><c:out value="${link.title}"/></a><br/>
		</div>
		</c:forEach>
	</div>
	<hr/>
<jsp:include page="/tail.do"/>
