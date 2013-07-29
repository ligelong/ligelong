<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<LINK href="/style/admin.css" type="text/css" rel="stylesheet"></LINK>
		<script language="javascript" src="/scripts/jquery.js"></script>
		<script language="javascript">
			$(document).ready(function(){
				$("#tag_href").click(function(){
					alert("该功能稍后开启");
					return false;
				});
				$("#submit_btn").click(function() {
					if($("#content").val()=='') {
						alert("内容不能为空");
						$("#content")[0].focus();
						return false;
					}
					if($("#title").val()=='') {
						$("#title").val($("#content").val().trim().substring(0, 5));
					}
				});
			});
		</script>
		<title>笑话后台管理后台</title>
	</head>
	<body>
		<form action="create_post.do" method="post">
			<input type="hidden" name="action" value="on">
			<table border="0" width="100%" id="table1" class=tablestyle height="28">
				<tr><td class="itemtitle" colspan="2">创建帖子</td></tr>
				<tr><td class="itemnormal">标题</td><td class="itemnormal" style="text-align:left"><input type="text" name="title" id="title" /></td></tr>
				<tr><td class="itemnormal">分类</td><td class="itemnormal" style="text-align:left"><select name="tag" id="tag">
					<c:forEach var="tag" items="${list}">
					<option value="<c:out value="${tag.id}"/>"><c:out value="${tag.name}"/></option>
					</c:forEach>
				</select>&nbsp;<a href="javascript:void(0)" id="tag_href">分类管理</a></td></tr>
				<tr><td class="itemnormal">内容</td><td class="itemnormal" style="text-align:left"><textarea rows="10" cols="100" name="content" id="content"></textarea></td></tr>
				<tr><td class="itemnormal" colspan="2"><input type="submit" value="提交" id="submit_btn"/></td></tr>
			</table>
		</form>
	</body>
</html>
