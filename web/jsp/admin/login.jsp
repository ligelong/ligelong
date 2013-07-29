<%@page contentType="text/html;charset=utf-8"%><%@include file="inc/Header.jsp"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Frameset//EN">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>笑话后台管理后台</title>
  <link href="style/login.css" type="text/css" rel="stylesheet"></link>
  <script type="text/javascript" src="scripts/jquery.js"></script>
  <script language="javascript">
  $(document).ready(function() {
  	if(self!=top){
  		parent.location.href='login.do'
  	}
  	
  	// 设置各div的样式
  	$('#un').addClass("txt");
  	$('#pwd').addClass("txt");
    $('div span').addClass("errtxt");
    $('#description').addClass('description');
    // 错误div默认隐藏
    $('#wrongdiv').addClass("errdiv").hide();
    // 输入框的样式按事件变化
  	$('input').addClass("input_off").focus(function(){
  		$(this).removeClass().addClass("input_on");
  	}).mousemove(function(){
  		$(this).removeClass().addClass("input_on");
  	}).blur(function(){
  		$(this).removeClass().addClass("input_off");
  	}).mouseout(function(){
  		$(this).removeClass().addClass("input_off");
  	});
    // 点击登录的动作
    $("#loginbutton").click(function() {
      if($("#username").val()=='') {
        alert("请输入用户名！")
        $("#username")[0].focus();
        return false;
      }
      if($("#userpwd").val()=="") {
        alert("请输入密码！")
        $("#userpwd")[0].focus();
        return false;
      }
      // 先判断用户名和密码是否为空，如果都不为空，则使用ajax去验证
      $.post("/user/loginaction.do", {"username":$("#username").val(),"password":$("#userpwd").val()},
        function(data, textStatus){
          if(data=='0') {
            window.location.href='/';
          } else if(data=='1') {
          	$('div img').attr("src","images/nouser.png");
          	$('div span').html("没有这个用户名");
            $('#wrongdiv').show();
          } else if(data=='2'){
          	$('div img').attr("src","images/wrongpwd.png");
          	$('div span').html("密码错误");
            $('#wrongdiv').show();
          } else if(data=='3'){
          	$('div img').attr("src","images/wrongpwd.png");
          	$('div span').html("该用户已被注销");
            $('#wrongdiv').show();
          } else if(data=='4'){
          	$('div img').attr("src","images/wrongpwd.png");
          	$('div span').html("非法的IP地址");
            $('#wrongdiv').show();
          }
        },
      "text")
      .error(function(){ // 这里是ajax出错的情况
      	$('div img').attr("src","images/wrongpwd.png");
      	$('div span').html("系统错误");
        $('#wrongdiv').show();
      });
    })
    .mouseover(function(){ // 设置按钮鼠标样式
      $(this).css("cursor","pointer");
    });
    // 如果用户名密码错误要重新输入时，将错误提示隐藏
    $("input[type='text']").focus(function() {
    	//if($("#wrongdiv").is(":visible")&&$('div span').html()=='没有这个用户名') {
      	$('#wrongdiv').hide();
      //}
    });
    $("input[type='password']").focus(function() {
    	//if($("#wrongdiv").is(":visible")&&$('div span').html()=='密码错误') {
        $('#wrongdiv').hide();
      //}
    });
  });
  // 监听回车键
  $(document).keypress(function(e) {
		if (e.which == 13) {
			$("#loginbutton").trigger("click");
		}
	})
  </script>
</head>
<body>
  <div id="main">
    <div id="logo"></div>
    <div id="wrongdiv"><img src="" alt=""><span></span></div>
    <div id="un">用户名</div>
    <div id="uninput"><input type="text" id="username"/></div>
    <div id="pwd">密　码</div>
    <div id="pwdinput"><input type="password" id="userpwd"/></div>
    <div id="loginbutton"></div>
    <div id="description">笑话后台管理后台 v1.0</div>
  </div>
</body>
</html>