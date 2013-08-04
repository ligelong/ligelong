<%@ page contentType="text/html; charset=utf-8" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<link href="/style/admin.css" type="text/css" rel="stylesheet"/>
		<script language="javascript">
			$(document).ready(function(){
				var severMillis = <%=System.currentTimeMillis()%>;
				var thisMillis = new Date().getTime();
				var gapMillis = 60*60*1000;
				if((thisMillis-severMillis)>gapMillis || (severMillis-thisMillis)>gapMillis) {
					alert("您的系统时间和实际时间相差过多，请调整之后重新登录");
					window.location.href='user/logout.do';
				}
			});
		</script>
		<title>操作区</title>
	</head>
	<body>
	</body>
</html>
