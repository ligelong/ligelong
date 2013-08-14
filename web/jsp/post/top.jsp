<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.do"/>
<%
	Object pagenumber = request.getAttribute("pagenumber");
	java.util.List<com.ligelong.hibernate.entity.PostEntity> postList = (java.util.List<com.ligelong.hibernate.entity.PostEntity>)request.getAttribute("list");
	for(com.ligelong.hibernate.entity.PostEntity post : postList) {
		String c = post.gainTextFromContent();
		if(c.length()>100) {
			out.print("<div>" + c.substring(0, 100) + "</div>");
			out.print("<div><a href='post/post.do?id=" + post.getId() + "'>显示更多</a><div>");
		} else {
			out.print("<div>" + c + "</div>");
		}
		out.print("<a href='index.do?up=" + post.getId() + "&amp;page=" + pagenumber + "'>↑</a>" + post.getUpcount() + "|<a href='index.do?down=" + post.getId() + "&amp;page=" + pagenumber + "'>↓</a>" + post.getDowncount() + "|<a href='post/post.do?id=" + post.getId() + "'>" + post.getCommentcount() + "条评论</a>|" + "<a href='post/share.do?id=" + post.getId() + "'>转发</a>");
	}
%>	
	<div>
		<c:if test="${!empty nextpage}">
			<a href="index.do?page=<c:out value="${nextpage}"/>">下一页</a>&nbsp;
		</c:if>
		<c:if test="${!empty prevpage}">
			<a href="index.do?page=<c:out value="${prevpage}"/>">上一页</a>&nbsp;
		</c:if>
		<c:if test="${pagenumber>2}">
			<a href="index.do?page=1">1</a>&nbsp;
		</c:if>
		<c:if test="${pagenumber>3}"> ...
		</c:if>
		<c:if test="${!empty prevpage}">
			<a href="index.do?page=<c:out value="${prevpage}"/>"><c:out value="${prevpage}"/></a>&nbsp;
		</c:if>
		<c:out value="${pagenumber}"/>
		<c:if test="${!empty nextpage}">
			<a href="index.do?page=<c:out value="${nextpage}"/>"><c:out value="${nextpage}"/></a>&nbsp;
		</c:if>
		<c:if test="${(!empty nextpage) && totalpage>nextpage+1}"> ...
		</c:if>
		<c:if test="${totalpage>nextpage}">
			<a href="index.do?page=<c:out value="${totalpage}"/>"><c:out value="${totalpage}"/></a>&nbsp;
		</c:if>
	</div>
<jsp:include page="/tail.do"/>
