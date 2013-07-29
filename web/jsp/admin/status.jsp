<%@page contentType="text/html; charset=utf-8" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<link href="/style/admin.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="/scripts/jquery.js"></script>
	<script language="javascript">
		var timerID = null;
		var timerRunning = false;

		function stopclock(){
			if(timerRunning)
				clearTimeout(timerID);
			timerRunning = false;
		}
		
		function startclock () {
			stopclock();
			showtimestatus();
		}
		
		function showtimestatus () {
			var now = new Date();
			var months = now.getMonth();
			var days = now.getDate();
			var hours = now.getHours();
			var minutes = now.getMinutes();
			var seconds = now.getSeconds();
			var timeValue = now.getFullYear()+"年";
			timeValue += ((months < 9) ? "0" : "") + (months+1) + "月";
			timeValue += ((days < 10) ? "0" : "") + days +"日&nbsp;";
			timeValue += ((hours < 10) ? "0" : "") + hours + "时";
			timeValue += ((minutes < 10) ? "0" : "") + minutes + "分";
			timeValue += ((seconds < 10) ? "0" : "") + seconds + "秒";
			$('#time').html(timeValue);
			timerID = setTimeout("showtimestatus()",1000);
			timerRunning = true;
		}

		$(document).ready(function(){
			startclock();
		});
	</script>
<title>笑话后台管理后台</title>
</head>
<body>
<div align="center" valign="top" style="height:20px">
	<table border="0" width="100%" height="100%">
		<tr>
			<th>当前系统时间:<span id="time"></span></th>
		</tr>
	</table>
</div>
</body>
</html>
