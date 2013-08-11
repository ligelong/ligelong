<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%!
	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy年MM月dd日 HH:mm");
%>
		<hr/>
		<c:forEach var="link" items="${ad2}">
		<div>
			<a href="<c:out value="${link.link}"/>"><c:out value="${link.title}"/></a><br/>
		</div>
		</c:forEach>
	</div>
	<hr/>
	<div><a href="#top">顶部</a>|<a href="f/advice.do">建议</a>|<a href="/f/setting.do">设置</a>|<a href="f/nav.do">导航</a>|<a href="f/corp.do">合作</a></div><hr/>
	<div>郭德纲老师教导我们！再不搞笑就太搞笑了！</div>
	<div>本站QQ群：1234567890987654321</div>
	<div><%=df.format(new java.util.Date())%></div>
</body>
</html>