<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Frameset//EN"><%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
	<title>笑话后台管理后台</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
</head>
<frameset border="0" name="totalframe" id="totalframe" framespacing="0" rows="*,30">
	<frameset border="0" name="allframe" id="allframe" framespacing="0" cols="240,*">
		<frame id="frmSub" name="sub" marginWidth="5" marginHeight="10" src="sub.do" scrolling="auto"/>
		<frameset rows="40,*">
			<frame id="frmTitle" name="title" marginWidth="1" marginHeight="3" src="title.do" scrolling="auto"/>
			<frame id="frmMain" name="main" src="main.do" scrolling="auto" marginwidth="10" marginheight="5"/>
		</frameset>
	</frameset>
	<frame id="frmStatus" name="status" src="status.do" marginheight="0" scrolling="no"/>
</frameset>
<body topmargin="0">
</html>