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
				window.location.href="${ctx}/admin/gpsuser/gpsuserList";
			});	
		});
		
		//前台非空验证
		   $(function(){
			   $('#updateGpsUser').submit(function(){	
			   		var gpsUserId = $('#gpsUserId');
 				   	var gpsUserPassword = $('#gpsUserPassword');		
					var i="";
					if($.trim(gpsUserId.val())==""){
 						i = "请输入gps用户id";
 						gpsUserId.focus();
 					}else if($.trim(gpsUserPassword.val())==""){
 						i = "请输入gps密码";
 						gpsUserPassword.focus();
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
<form action="${ctx}/admin/gpsuser/update?gpsId=${gpsuser.gpsUserId}" method="post" class="definewidth m20" id="updateGpsUser">
<table class="table table-bordered table-hover ">
    <tr>
        <td width="30%" class="tableleft">gps用户id</td>
        <td>
        <input type="text" id="gpsUserId" name="gpsUserId" value="${gpsuser.gpsUserId}" />
        </td>
    </tr>
    <tr>
        <td width="30%" class="tableleft">gps密码</td>
        <td>
        <input type="text" id="gpsUserPassword" name="gpsUserPassword" value="${gpsuser.gpsUserPassword}" />
        </td>
    </tr>
    <tr>
        <td width="30%" class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
