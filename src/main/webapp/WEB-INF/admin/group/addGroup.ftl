<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
	<head>
    	<title>轮播管理-添加轮播数据</title>
    	<meta charset="UTF-8">
     	<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
	    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-responsive.css" />
	    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
	    <link rel="stylesheet" type="text/css" href="${ctx}/css/admin-right.css" />
	    <script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
		
    	<script type="text/javascript">
    	 	$(function(){
		    	/** 为返回按钮绑定点击事件 */
				$('#backid').click(function(){
					window.location.href = "${ctx}/admin/group/toGroupList";
				});
    	 	});
    	 	
    	 	//前台非空验证
 		   $(function(){
 			   $('#addGroupForm').submit(function(){
 				   	var groupId = $('#groupId');
 				   	var groupName = $('#groupName');
 				   	var type = $('#type');
 				   	var pttSilentTime = $('#pttSilentTime');
 				   	var callTime = $('#callTime');
 				   	var pttOnTime = $('#pttOnTime');
 					var i="";
 					if($.trim(groupId.val())==""){
 						i = "请输入组Id";
 						groupId.focus();
 					}else if($.trim(groupName.val())==""){
 						i = "请输入组名";
 						groupName.focus();
 					}else if($.trim(type.val())==""){
 						i = "请输入组类型";
 						type.focus();
 					}else if($.trim(pttSilentTime.val())==""){
 						i = "请输入PTT静默时长";
 						pttSilentTime.focus();
 					}else if($.trim(callTime.val())==""){
 						i = "请输入通话时间限定";
 						callTime.focus();
 					}else if($.trim(pttOnTime.val())==""){
 						i = "请输入单次PTT授权最长时间";
 						pttOnTime.focus();
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
		<table class="table table-bordered table-hover definewidth m10">
		    <form id="addGroupForm" action="${ctx}/admin/group/addGroup" method="POST" class="definewidth m20" enctype="multipart/form-data">
		        <tr>
		            <td width="30%" class="tableleft">组id号</td>
		            <td><input type="text" name="groupId" id="groupId"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">组名</td>
		            <td><input type="text" name="groupName" id="groupName"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">类型</td>
		            <td><input type="text" name="type" id="type"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">PTT静默时长</td>
		            <td><input type="text" name="pttSilentTime" id="pttSilentTime"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">一次呼叫总得通话时间限定</td>
		            <td><input type="text" name="callTime" id="callTime"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">单次PTT授权最长时间</td>
		            <td><input type="text" name="pttOnTime" id="pttOnTime"/></td>
		        </tr>
		         <tr>
		        	<td>
		            	
		            </td>
		            <td>
		            &nbsp;&nbsp;<button type="submit" class="btn btn-primary">保存</button>
		                &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回</button>
		            </td>
	        	</tr>
		    </form>
		</table>
	</body>
</html>