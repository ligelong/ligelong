<%@page contentType="text/html; charset=utf-8"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<link href="/style/admin.css" type="text/css" rel="stylesheet"></link>
		<script language="javascript">
			function initTd() {
				if(getCookie("posttdshow")=='hide') {
					document.getElementById("posttd").style.display="none";
				}
				if(getCookie("adtdshow")=='hide') {
					document.getElementById("adtd").style.display="none";
				}
				if(getCookie("usertdshow")=='hide') {
					document.getElementById("usertd").style.display="none";
				}
				if(getCookie("statisticstdshow")=='hide') {
					document.getElementById("statisticstd").style.display="none";
				}
			}
  
			function turnit(trid,imgid) {
				var trstyle = document.getElementById(trid).style;
				var imgsrc = document.getElementById(imgid);
				if (trstyle.display=="none"){
					trstyle.display="inline";
					imgsrc.src="/images/minusbottom.gif";
					setCookie(trid+"show", "show");
				} else {
					trstyle.display="none";
					imgsrc.src="/images/plusbottom.gif";
					setCookie(trid+"show", "hide");
				}
			}
			
			function setCookie(cname,cvalue) {
				document.cookie = cname + "=" + cvalue + ";expires=Fri, 31 Dec 3000 23:59:59 GMT;" ;
			}
			
			function getCookie(cname) {
				var cookies;
				var cookiesstring = new String(document.cookie);
				cookies = cookiesstring.split(";");
				for(var i=0 ; i<cookies.length ; i++) {
					if(cookies[i]&&cookies[i].length > 0) {
						if(cookies[i].indexOf(cname + "=")!=-1) {
							var begin = cookies[i].indexOf(cname);
							return cookies[i].substring(begin + (cname + "=").length);
						}
					}
				}
				return "Can not Found !";
			}
</script>
<title>功能区</title>
</head>
<body onload="initTd()">
  <table class="tablestyle" cellSpacing="1" cellPadding="2" width="100%" border="0">
    <tr>
      <th class="tabletitle">欢迎您，<c:out value="${user.username}"/><br/>[功能区]</th>
    </tr>
    <tr>
      <td>
		<table cellspacing="0" cellpadding="2" border="0" width="100%">
			<tr id="sub_post_manage">
				<td class="itemtitle" onmouseup="turnit('posttd','postimg');" style="text-align:left;cursor:hand">
					<img id="postimg" height="16" src="/images/minusbottom.gif" width="18" align="absmiddle">
					<b>帖子管理</b>
				</td>
			</tr>
			<tr id="itemlist_commentmanage">
				<td id="posttd" class="itemnormal" style="text-align:left;display:inline">
					<table cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td style="cursor:hand" colspan="3">
								<img height="16" src="/images/join.gif" width="18" align="absmiddle" border="0">
								<a target="main" href="manage_post.do">管理帖子</a>
							</td>
						</tr>
						<tr>
							<td style="cursor:hand" colspan=3>
								<img height="16" src="/images/join.gif" width="18" align="absmiddle" border="0">
								<a target="main" href="create_post.do">新建帖子</a>
							</td>
						</tr>
						<tr>
							<td style="cursor:hand" colspan="3">
								<img height="16" src="/images/join.gif" width="18" align="absmiddle" border="0">
								<a target="main" href="import_post.do">导入帖子</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      </td>
    </tr>
    <tr>
      <td>
		<table cellspacing="0" cellpadding="2" border="0" width="100%">
			<tr id="sub_ad_manage">
				<td class="itemtitle" onmouseup="turnit('adtd','adimg');" style="text-align:left;cursor:hand">
					<img id="adimg" height="16" src="/images/minusbottom.gif" width="18" align="absmiddle">
					<b>链接管理</b>
				</td>
			</tr>
			<tr id="itemlist_commentmanage">
				<td id="adtd" class="itemnormal" style="text-align:left;display:inline">
					<table cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td style="cursor:hand" colspan="3">
								<img height="16" src="/images/join.gif" width="18" align="absmiddle" border="0">
								<a target="main" href="manage_ad.do">管理链接</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      </td>
    </tr>
    <tr>
      <td>
		<table cellspacing="0" cellpadding="2" border="0" width="100%">
			<tr id="sub_user_manage">
				<td class="itemtitle" onmouseup="turnit('usertd','userimg');" style="text-align:left;cursor:hand">
					<img id="userimg" height="16" src="/images/minusbottom.gif" width="18" align="absmiddle">
					<b>用户管理</b>
				</td>
			</tr>
			<tr id="itemlist_commentmanage">
				<td id="usertd" class="itemnormal" style="text-align:left;display:inline">
					<table cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td style="cursor:hand" colspan="3">
								<img height="16" src="/images/join.gif" width="18" align="absmiddle" border="0">
								<a target="main" href="manage_user.do">管理用户</a>
							</td>
						</tr>
						<tr>
							<td style="cursor:hand" colspan="3">
								<img height="16" src="/images/join.gif" width="18" align="absmiddle" border="0">
								<a target="main" href="acl_user.do">用户权限</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      </td>
    </tr>
    <tr>
      <td>
		<table cellspacing="0" cellpadding="2" border="0" width="100%">
			<tr id="sub_statistics_manage">
				<td class="itemtitle" onmouseup="turnit('statisticstd','statisticsimg');" style="text-align:left;cursor:hand">
					<img id="statisticsimg" height="16" src="/images/minusbottom.gif" width="18" align="absmiddle">
					<b>统计查询</b>
				</td>
			</tr>
			<tr id="itemlist_commentmanage">
				<td id="statisticstd" class="itemnormal" style="text-align:left;display:inline">
					<table cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td style="cursor:hand" colspan="3">
								<img height="16" src="/images/join.gif" width="18" align="absmiddle" border="0">
								<a target="main" href="statistics.do">统计查看</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      </td>
    </tr>
    <tr>
      <td>
		<table cellspacing="0" cellpadding="2" border="0" width="100%">
			<tr id="sub_statistics_manage">
				<td class="itemtitle" onmouseup="turnit('statisticstd','statisticsimg');" style="text-align:left;cursor:hand">
					<img id="statisticsimg" height="16" src="/images/minusbottom.gif" width="18" align="absmiddle">
					<b>系统相关</b>
				</td>
			</tr>
			<tr id="itemlist_commentmanage">
				<td id="statisticstd" class="itemnormal" style="text-align:left;display:inline">
					<table cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td style="cursor:hand" colspan="3">
								<img height="16" src="/images/join.gif" width="18" align="absmiddle" border="0">
								<a href="../index.do" target=_blank>进入网站</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      </td>
    </tr>
  </table>
</body>
</html>
