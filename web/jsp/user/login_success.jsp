<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.do"/>
<c:if test="${!empty info}">
<c:out value="${info}"/><br/>
</c:if>
		登录成功，正在<a href="<c:out value="${url}"/>">跳转</a>
	</div>
	<hr/>
<jsp:include page="/tail.do"/>
