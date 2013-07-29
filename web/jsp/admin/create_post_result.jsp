<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<LINK href="/style/admin.css" type="text/css" rel="stylesheet"></LINK>
		<title>笑话后台管理后台</title>
	</head>
	<body>
		<table border="0" width="100%" id="table1" class=tablestyle height="28">
			<tr><td class="itemtitle" colspan="2">创建帖子</td></tr>
			<tr><td class="itemnormal" colspan="2"><b>《<c:out value="${title}"/>》</b>创建成功</td></tr>
			<tr><td class="itemnormal" colspan="2"><a href="create_post.do">继续创建</a></tr>
		</table>
	</body>
</html>
