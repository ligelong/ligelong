<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.do"/>
<form action="add.do" method="post">
<input type="hidden" name="action" value="on"/>
<textarea name="content" ></textarea>
<input type="submit" value="提交"/>
</form>
<jsp:include page="/tail.do"/>
