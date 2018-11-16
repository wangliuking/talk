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
		
    	<script type="text/javascript">
    	 	$(function(){
		    	/** 为返回按钮绑定点击事件 */
				$('#backid').click(function(){
					window.location.href = "${ctx}/admin/bureaudirection/toBureaudirectionList";
				});
    	 	});
    	 	
    	 	$(function(){
  			   $('#updateBureaudirectionForm').submit(function(){
  					var bdId = $('#bdId');
 				   	var address = $('#address');
 				   	var csPort = $('#csPort');
 				   	var voicePort = $('#voicePort');
 				   	var bdName = $('#bdName');
 					var i="";
 					if($.trim(bdId.val())==""){
 						i = "请输入局向码";
 						bdId.focus();
 					}else if($.trim(address.val())==""){
 						i = "请输入局向ID地址";
 						address.focus();
 					}else if($.trim(csPort.val())==""){
 						i = "请输入局向信令端口";
 						csPort.focus();
 					}else if($.trim(voicePort.val())==""){
 						i = "请输入局向语音端口";
 						voicePort.focus();
 					}else if($.trim(bdName.val())==""){
 						i = "请输入局向名";
 						bdName.focus();
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
		    <form id="updateBureaudirectionForm" action="${ctx}/admin/bureaudirection/update" method="POST" class="definewidth m20" enctype="multipart/form-data">
		        <input type="hidden" name="bdId" value="${bureaudirection.bdId}">
		        <tr>
		            <td width="30%" class="tableleft">局向码</td>
		            <td><input type="text" name="bdId" id="bdId" value="${bureaudirection.bdId}"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">局向IP地址</td>
		            <td><input type="text" name="address" id="address" value="${bureaudirection.address}"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">局向信令端口</td>
		            <td><input type="text" name="csPort" id="csPort" value="${bureaudirection.csPort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">局向语音端口</td>
		            <td><input type="text" name="voicePort" id="voicePort" value="${bureaudirection.voicePort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">局向名</td>
		        	<#if bureaudirection.bdName??>
		            <td><input type="text" name="bdName" id="bdName" value="${bureaudirection.bdName}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
		       		<#else>
		       		<td><input type="text" name="bdName" id="bdName" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
		       		</#if>
		        </tr>
		         <tr>
		        	<td width="30%">	            	
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