<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.do"/>
<form action="add.do" method="post">
<input type="hidden" name="action" value="on"/>
标题：<input type="text" name="title" /><br/>
内容：<textarea name="content" ></textarea><br/>
<input type="submit" value="提交"/><br/>
</form>
<jsp:include page="/tail.do"/>
