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
    <script src="${ctx}/js/artDialog-6.0.2/dist/dialog-min.js"></script>
	<link rel="stylesheet" href="${ctx}/js/artDialog-6.0.2/css/ui-dialog.css">
	<script type="text/javascript">
    	 	$(function(){
    	 		/** 为保存操作绑定点击事件*/
		    	$('#save').click(function(){
		    		var temp='';
		    		var powerTemp='';
	    	 		$('.power option:selected').each(function(index, element){
	    	 			powerTemp=powerTemp+$(this).val();
	    	 		});
	    	 		var params = {
	    	 		userId:$('#userId').val(),
	    	 		power:powerTemp
	    	 		};
					var url = "${ctx}/admin/user/powerUpdate";
					$.post(url,params,function(data,status){
						if(status == "success"){
							if(data.status == 0){
								window.location = "${ctx}/admin/user/userList"
							}else if(data.status == 1){
								alert(data.tip);
							}
						}else{
							alert("数据加载失败!");	
						}
					},"json");
		    	});			
				
		    	/** 为返回按钮绑定点击事件 */
				$('#backid').click(function(){
					window.location.href = "${ctx}/admin/user/userList";
				});
		    	
    	 	});
		</script>
</head>
<body>

<table class="table table-bordered table-hover definewidth m10" >
<form id="addForm" action="" method="POST" class="definewidth m20" enctype="multipart/form-data">
<input type="hidden" id="userId" name="userId" value="${user.userId}"/>
    <thead>
    <tr>
        <th>用户Id</th>
        <th>用户名称</th>
        <th>功能模块</th>
        <th>访问限制</th>
    </tr>
    </thead>
    <#if powerMap??>
    <#list powerMap?keys as key>
	<tr>
		<td>${user.userId}<#if user.level==2>（超级管理员）<#else>（管理员）</#if></td>
		<td>${user.userName}</td>
        <td>${key}</td>
        <td>
        <select class="power">
        <option name="" value="0" <#if powerMap[key]=="0">selected="selected"</#if>>不可读</option>
        <option name="" value="1" <#if powerMap[key]=="1">selected="selected"</#if>>可读不可写</option>
        <option name="" value="2" <#if powerMap[key]=="2">selected="selected"</#if>>读写均可</option>
        </select> 
        </td>     
   	</tr>
   	</#list>
   	</#if>
   	<tr>
<td>
&nbsp;&nbsp;<button type="button" class="btn btn-primary" name="save" id="save">保存</button>
&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回</button>
</td>
</tr>
   	</form>
        </table>
        </body>
</html>
