<%@page contentType="text/html; charset=utf-8" %><html>
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

			function testfile(fileName) { 
				var extName = fileName.substr(fileName.lastIndexOf(".")+1).toLowerCase(); 
				if(extName != 'xlsx') { 
					alert("目前只支持excel 2007格式文件");
				}
			} 
		</script>
		<title>导入帖子</title>
	</head>
	<body>
		<div align="center">
			<form action="import_save.do" enctype="multipart/form-data" method="post">
				<table id="table1" class="tablestyle">
					<tr height="30"><th class="tabletitle" height="20">[导入帖子]</th></tr>
					<tr>
						<td align="left" class="itemnormal">
							<b>本地文件(目前只支持excel 2007格式，扩展名为xlsx的文件)：</b>
							<input style="font-size:9pt" type="file" name="localUrl" size="60" onchange="testfile(this.value)">
						</td>
					</tr>
					<tr><td align="left" class="itemnormal"><input type="submit" value="下一步"></td></tr>					
<c:if test="${!empty error}">
					<tr><td align="left" class="itemnormal"><div class="warning"><c:out value="${error}"/></div></td></tr>
</c:if>
				</table>
			</form>
		</div>
		<div id="hintdiv" style="display:none" align="center">正在上传，请稍等</div>
	</body>
</html>
