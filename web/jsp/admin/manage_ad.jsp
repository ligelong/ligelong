<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<LINK href="/style/admin.css" type="text/css" rel="stylesheet"></LINK>
		<script language="javascript" src="/scripts/jquery.js"></script>
		<script language="javascript">
			$(document).ready(function(){
				$("#addposhref").click(function() {
					showAddPosition();
				});
				$("#addadhref").click(function() {
					showAddAd();
				});
				
			});
			
			function showAddPosition() {
				var top1 = event.pageY?event.pageY:window.event.clientY;
				var left1 = event.pageX?event.pageX:window.event.clientX;
				$('body').append('<div id="positiondiv"/>');
				$("#positiondiv").append("<div id='uldiv' style='text-align:center;padding:0 20px;'>");
				$("#uldiv").append("填写标题:<input type='text' id='title' /><br/>");
				$("#positiondiv").css({"position":"absolute","margin":"0","padding":"0","top":top1+10,"left":left1+10,"background-color":"#ffffff"});  		
				$("#positiondiv").append("<div style='text-align:center'><input type='button' id='closebtn' value='确定' onclick='addPosition()'/>&nbsp;<input type='button' id='closebtn' value='关闭' onclick='closeDiv(\"positiondiv\")'/></div>");
				$("#positiondiv").show();
			}
			
			function addPosition() {
				var ret = '';
				$.ajaxSettings.async = false;
				$.post("add_ad_position.do", {id:$("#id").val(), title:$("#title").val()},function(data, textStatus){
                    ret = data;
                }, "text").error(function(XMLHttpRequest, textStatus, errorThrown){
                    alert("系统异常，请稍后再试");
                });
				location.reload();
			}
			
			function showAddAd() {
				var top1 = event.pageY?event.pageY:window.event.clientY;
				var left1 = event.pageX?event.pageX:window.event.clientX;
				$('body').append('<div id="positiondiv"/>');
				$("#positiondiv").append("<div id='uldiv' style='text-align:center;padding:0 20px;'>");
				$("#uldiv").append("填写标题:<input type='text' id='title' /><br/>");
				$("#uldiv").append("填写链接:<input type='text' id='link' /><br/>");
				$("#positiondiv").css({"position":"absolute","margin":"0","padding":"0","top":top1+10,"left":left1+10,"background-color":"#ffffff"});  		
				$("#positiondiv").append("<div style='text-align:center'><input type='button' id='closebtn' value='确定' onclick='addAd()'/>&nbsp;<input type='button' id='closebtn' value='关闭' onclick='closeDiv(\"positiondiv\")'/></div>");
				$("#positiondiv").show();
			}
			
			function addAd() {
				var ret = '';
				$.ajaxSettings.async = false;
				$.post("add_ad.do", {id:$("#id").val(), title:$("#title").val(), link:$("#link").val()},function(data, textStatus){
                    ret = data;
                }, "text").error(function(XMLHttpRequest, textStatus, errorThrown){
                    alert("系统异常，请稍后再试");
                });
				location.reload();
			}
			
			function closeDiv(divid) {
				$("#" + divid).remove();
			}
			
			function deletePost(id) {
				if(confirm("确定要删除吗？")) {
					alert("暂时无法删除");
				}
				return;
			}
			
			function showAd(id) {
				var ret = '';
				$.ajaxSettings.async = false;
				$.post("get_ad_content.do", {id:id},function(data, textStatus){
                    ret = data;
                }, "text").error(function(XMLHttpRequest, textStatus, errorThrown){
                    alert("系统异常，请稍后再试");
                });
				var top1 = event.pageY?event.pageY:window.event.clientY;
				var left1 = event.pageX?event.pageX:window.event.clientX;
				$('body').append('<div id="adcontentdiv"/>');
				$("#adcontentdiv").append("<div id='uldiv' style='text-align:center;padding:0 20px;'>");
				$("#uldiv").html(ret);
				$("#adcontentdiv").css({"position":"absolute","margin":"0","padding":"0","top":top1+10,"left":left1+10,"background-color":"#ffffff"});  		
				$("#adcontentdiv").append("<div style='text-align:center'><input type='button' id='closebtn' value='关闭' onclick='closeDiv(\"adcontentdiv\")'/></div>");
				$("#adcontentdiv").show();
			}
		</script>
		<title>笑话后台管理后台</title>
	</head>
	<body>
		<input type="hidden" id="id" value="<c:out value="${id}"/>"/>
		<div align="center">
			<table border="0" width="100%" id="table1" class=tablestyle height="28">
				<tr><td class="itemtitle">&nbsp;</td><td class="itemtitle">标题</td><td class="itemtitle">类型</td><td class="itemtitle">操作</td></tr>
				<c:forEach var="link" items="${list}" varStatus="status">
				<tr>
					<td class="itemnormal"><c:out value="${status.count}"/></td>
					<td class="itemnormal"><c:choose><c:when test="${link.type==0}"><a href="manage_ad.do?id=<c:out value="${link.id}"/>"><c:out value="${link.title}"/></a></c:when>
						<c:otherwise><a href="javascript:void(0)" onclick="showAd(<c:out value="${link.id}"/>)"><c:out value="${link.title}"/></a></c:otherwise></c:choose></td>
					<td class="itemnormal"><c:choose><c:when test="${link.type==0}">位置</c:when><c:otherwise>链接</c:otherwise></c:choose></td>
					<td class="itemnormal"><a href="javascript:void(0)" onclick="deletePost(<c:out value="${link.id}"/>)">删除</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div align="center">
			<table border="0" width="100%" id="table1" class=tablestyle>
				<tr><td class="itemnormal"><a href="javascript:void(0)" id="addposhref">在此处添加一个广告位</a></td></tr>
				<c:if test="${id!=-1}">
				<tr><td class="itemnormal"><a href="javascript:void(0)" id="addadhref">在此处添加一个广告链接</a></td></tr>
				</c:if>
			</table>
		</div>
	</body>
</html>
