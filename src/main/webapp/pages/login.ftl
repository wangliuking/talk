<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网管登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/styles.css" tppabs="${ctx}/css/styles.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
<script src="${ctx}/js/Particleground.js" tppabs="${ctx}/js/Particleground.js"></script>
<script type="text/javascript">
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
});


<!--获取当前浏览器类型 -->
function myBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return "IE";
    }; //判断是否IE浏览器
    if((window.navigator.mimeTypes[40] || !window.navigator.mimeTypes.length)){
      	return("360");
	}
    if (isOpera) {
        return "Opera"
    }; //判断是否Opera浏览器
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    } //判断是否Firefox浏览器
    if (userAgent.indexOf("Chrome") > -1){
  		return "chrome";
 	}
    if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    } //判断是否Safari浏览器
}
//以下是调用上面的函数
var mb = myBrowser();

$(function(){
	$("#loginBtn").click(function(){
		var name = $('#username');
		var pass = $('#userpass');
		var i="";
		if($.trim(name.val())==""){
			i = "登录名不能为空";
			name.focus();
		}else if($.trim(pass.val()) == ""){
			i = "密码不能为空";
			pass.focus();
		}

		if(i != ""){
			alert(i);
			return false;
		}else{
			var params = $("#loginForm").serialize();
			var url = "${ctx}/customer/loginAjax?browType="+mb;
			$.post(url,params,function(data,status){
				if(status == "success"){
					if(data.status == 0){
						window.location = "${ctx}/admin/important"
					}else if(data.status == 1){
						window.location = "${ctx}/admin/normal"
					}else{
						alert(data.tip);
					}
				}else{
					alert("数据加载失败!");	
				}
			},"json");
		}
	});

}); 

</script>

</head>
<body>

<form action="user/login.do" method="post" id="loginForm">
<dl class="admin_login">
 <dt>
  <strong>公网对讲系统</strong>
  <em>Management System</em>
 </dt>
 <dd class="user_icon">
  <input type="text" id="username" placeholder="账号" name="userName" class="login_txtbx" value=""/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" id="userpass" placeholder="密码" name="password" class="login_txtbx" value=""/>
 </dd>
 
 <dd>
  <input type="button" id="loginBtn" value="立即登陆" class="submit_btn" />
 </dd>

 </form>

</dl>

</body>
</html>
<script   language="javascript">    
      if (top != window)    
      top.location.href = window.location.href;    
</script> 