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
					window.location.href = "${ctx}/admin/user/userList";
				});
    	 	});
    	 	
    	 	//前台非空验证
 		   $(function(){
 			   $('#Form').submit(function(){
 				   	var num1 = $('#num1');  
 				   	var num2 = $('#num2');	
 					var i="";
 					if($.trim(num1.val())==""){
 						i = "请输入起始id号";
 						num1.focus();
 					}else if($.trim(num2.val())==""){
 						i = "请输入结束id号";
 						num2.focus();
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
		    <form id="Form" action="${ctx}/admin/user/insertBatch" method="POST" class="definewidth m20" enctype="multipart/form-data">
		        <tr>
		            <td width="30%" class="tableleft">起始id号</td>
		            <td><input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="num1" name="num1" maxlength = 10/></td>
		        </tr>
		        <tr>
		            <td width="30%" class="tableleft">结束id号</td>
		            <td><input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="num2" name="num2" maxlength = 10/></td>
		        </tr>
		        <tr>
		            <td width="30%" class="tableleft">开户类型</td>
		            <td><input type="radio" class="magic-checkbox" name="typeInsert" value="0" checked/>用户
		            	<input type="radio" class="magic-checkbox" name="typeInsert" value="1" />组
		            </td>
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