<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<LINK href="/style/admin.css" type="text/css" rel="stylesheet"></LINK>
		<script language="javascript" src="/scripts/jquery.js"></script>
		<script language="javascript">
			$(document).ready(function(){
			});
			
			function showPost(id) {
				closeDiv("contentdiv");
				var ret = '';
				$.ajaxSettings.async = false;
				$.post("get_post_content.do", {id:id},function(data, textStatus){
                    ret = data;
                }, "text").error(function(XMLHttpRequest, textStatus, errorThrown){
                    alert("系统异常，请稍后再试");
                });
				var top1 = event.pageY?event.pageY:window.event.clientY;
				var left1 = event.pageX?event.pageX:window.event.clientX;
				$('body').append('<div id="contentdiv"/>');
				$("#contentdiv").append("<div id='uldiv' style='text-align:center;padding:0 20px;'>");
				$("#uldiv").html(ret);
				$("#contentdiv").css({"position":"absolute","margin":"0","padding":"0","top":top1+10,"left":left1+10,"background-color":"#ffffff"});  		
				$("#contentdiv").append("<div style='text-align:center'><input type='button' id='closebtn' value='关闭' onclick='closeDiv(\"contentdiv\")'/></div>");
				$("#contentdiv").show();
			}
			
			function closeDiv(divid) {
				$("#" + divid).remove();
			}
			
			function hidePost(id) {
				if(confirm("确定要屏蔽吗？")) {
					$.ajaxSettings.async = false;
					$.post("prohibit_post.do", {id:id},function(data, textStatus){
						if(data=='0') {
							alert("屏蔽成功");
							location.reload();
						}
					}, "text").error(function(XMLHttpRequest, textStatus, errorThrown){
						alert("系统异常，请稍后再试");
					});
				}
			}
			
			
			function deletePost(id) {
				if(confirm("确定要删除吗？")) {
					$.ajaxSettings.async = false;
					$.post("delete_post.do", {id:id},function(data, textStatus){
						if(data=='0') {
							alert("删除成功");
							location.reload();
						}
					}, "text").error(function(XMLHttpRequest, textStatus, errorThrown){
						alert("系统异常，请稍后再试");
					});
				}
			}
		</script>
		<title>笑话后台管理后台</title>
	</head>
	<body>
		<div align="center">
			<table border="0" width="100%" id="table1" class=tablestyle height="28">
				<tr><td class="itemtitle">&nbsp;</td><td class="itemtitle">标题</td><td class="itemtitle">状态</td><td class="itemtitle">操作</td></tr>
				<c:forEach var="post" items="${list}" varStatus="status">
				<tr>
					<td class="itemnormal"><c:out value="${status.count}"/></td>
					<td class="itemnormal"><a href="javascript:void(0)" onclick="showPost(<c:out value="${post.id}"/>)"><c:out value="${post.title}"/></a></td>
					<td class="itemnormal"><c:choose><c:when test="${post.status==0}">正常</c:when><c:otherwise>屏蔽</c:otherwise></c:choose></td>
					<td class="itemnormal"><a href="javascript:void(0)" onclick="hidePost(<c:out value="${post.id}"/>)">屏蔽</a>&nbsp;<a href="javascript:void(0)" onclick="deletePost(<c:out value="${post.id}"/>)">彻底删除</a></td></tr>
				</c:forEach>
				<tr><td class="itemnormal" colspan="3">
				
		<c:if test="${!empty nextpage}">
			<a href="manage_post.do?page=<c:out value="${nextpage}"/>">下一页</a>&nbsp;
		</c:if>
		<c:if test="${!empty prevpage}">
			<a href="manage_post.do?page=<c:out value="${prevpage}"/>">上一页</a>&nbsp;
		</c:if>
		<c:if test="${pagenumber>2}">
			<a href="manage_post.do?page=1">1</a>&nbsp;
		</c:if>
		<c:if test="${pagenumber>3}"> ...
		</c:if>
		<c:if test="${!empty prevpage}">
			<a href="manage_post.do?page=<c:out value="${prevpage}"/>"><c:out value="${prevpage}"/></a>&nbsp;
		</c:if>
		<c:out value="${pagenumber}"/>
		<c:if test="${!empty nextpage}">
			<a href="manage_post.do?page=<c:out value="${nextpage}"/>"><c:out value="${nextpage}"/></a>&nbsp;
		</c:if>
		<c:if test="${(!empty nextpage) && totalpage>nextpage+1}"> ...
		</c:if>
		<c:if test="${totalpage>nextpage}">
			<a href="manage_post.do?page=<c:out value="${totalpage}"/>"><c:out value="${totalpage}"/></a>&nbsp;
		</c:if>
				</td></tr>
			</table>
		</div>
	</body>
</html>
