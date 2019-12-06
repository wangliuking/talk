<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/css/admin-right.css" />
    <script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
    
    <!-- 引入日期js控件 -->
	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

	<script type="text/javascript">
		$(function(){
			//返回列表页面操作
			$('#backid').click(function(){
				window.location.href="${ctx}/admin/user/toUserList";
			});
			
			//自己根据前面的内容补充表单验证操作
			
			
		});
		//前台非空验证
		   $(function(){
			   $('#addUserForm').submit(function(){
				   	var userId = $('#userId');
				   	var userName = $('#userName');
				   	var password = $('#password');
				   	var authenticateCode = $('#authenticateCode');
				   	var priority = $('#priority');
				   	var type = $('#type');
					var i="";
					if($.trim(userId.val())=="" || userId.val().length > 30){
						i = "用户Id非法";
						userId.focus();
					}else if($.trim(userName.val())=="" || userName.val().length > 50){
						i = "用户名非法";
						userName.focus();
					}else if($.trim(password.val())=="" || password.val().length > 30){
						i = "登录密码非法";
						password.focus();
					}else if($.trim(authenticateCode.val())=="" || authenticateCode.val().length > 30){
						i = "鉴权码非法";
						authenticateCode.focus();
					}else if($.trim(priority.val())=="" || priority.val().length > 11){
						i = "优先级非法";
						priority.focus();
					}else if($.trim(type.val())=="" || type.val().length > 11){
						i = "用户类型非法";
						type.focus();
					}
					if(i != ""){
						alert(i);
						return false;
					}else{
						return true;
					}
			   });
		   });
	</script>
</head>
<body>
<form action="${ctx}/admin/user/add" method="post" class="definewidth m20" id="addUserForm">
<table class="table table-bordered table-hover ">
    <tr>
        <td width="30%" class="tableleft">用户ID</td>
        <td><input type="text" name="userId" value="" id="userId"/></td>
    </tr>
    <tr>
        <td width="30%" class="tableleft">用户名</td>
        <td><input type="text" name="userName" value="" id="userName"/></td>
    </tr>
    <tr>
        <td width="30%" class="tableleft">登录密码</td>
        <td>
       		<input type="text" name="password" value="" id="password"/>
        </td>
    </tr>
    <tr>
        <td width="30%" class="tableleft">鉴权码</td>
        <td>
       		<input type="text" name="authenticateCode" value="" id="authenticateCode"/>
        </td>
    </tr>
    <tr>
        <td width="30%" class="tableleft">优先级</td>
        <td>
       		<input type="text" name="priority" value=""  id="priority" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="仅超级管理员可以操作"  <#if session_user.priority!="2"> disabled="disabled" </#if> />
        </td>
    </tr>
    <tr>
        <td width="30%" class="tableleft">用户类型</td>
        <td><input type="text" name="type" value="" id="type" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>