<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><jsp:include page="/header.do"/>
<%
	Object pagenumber = request.getAttribute("pagenumber");
	com.ligelong.hibernate.entity.PostEntity post = (com.ligelong.hibernate.entity.PostEntity)request.getAttribute("post");
	if(post==null) {
		out.print("<div>这条笑话不见了</div>");
	} else {
		String c = post.gainTextFromContent();
		out.print("<div>" + c + "</div>");
		out.print("<a href='post.do?up=" + post.getId() + "'>↑</a>" + post.getUpcount() + "|<a href='post.do?down=" + post.getId() + "'>↓</a>" + post.getDowncount() + "|" + post.getCommentcount() + "条评论|" + "<a href=''>转发</a>");
	}
%>

	<script language="javascript">
		function submitform() {
			document.getElementById('commentform').submit();
		}
	</script>
<form action="post.do" method="post" id="commentform">
<input type="hidden" name="id" value="<c:out value="${post.id}"/>"/>
<input type="hidden" name="action" value="on"/>
<input type="text" name="content" />&nbsp;<a href="#" onclick="submitform()">提交评论</a>
</form>
<c:forEach var="comment" items="${comments}">
<div><c:out value="${comment.content}"/>&nbsp;&nbsp;&nbsp;<c:out value="${comment.createtime}"/></div>
</c:forEach>
<jsp:include page="/tail.do"/>