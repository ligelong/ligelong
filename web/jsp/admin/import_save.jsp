<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<LINK href="/style/admin.css" type="text/css" rel="stylesheet"></LINK>
		<script language="javascript" src="/scripts/jquery.js"></script>
		<script language="javascript">
			$(document).ready(function(){
				$("input[type='submit']").click(function(){
					$("#hintdiv").show();
				});
			});

		</script>
		<title>导入帖子</title>
	</head>
	<body>
		<div align="center">
		<table id="table1" class="tablestyle">
			<tr height="30">
				<th class="tabletitle" height="20">
					[数据导入]
				</th>
			</tr>
			<tr>
				<td align="left" class="itemnormal">本次导入新添加<span><c:out value="${file}"/></span>条记录</td>
			</tr>
		</table>
	</body>
</html>
