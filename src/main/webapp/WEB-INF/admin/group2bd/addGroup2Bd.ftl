<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
	<head>
    	<title>添加组和局向关联</title>
    	<meta charset="UTF-8">
     	<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
	    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap-responsive.css" />
	    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
	    <link rel="stylesheet" type="text/css" href="${ctx}/css/admin-right.css" />
	    <script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
		
    	<script type="text/javascript">
    	 	$(function(){
    	 		/** 为保存操作绑定点击事件*/
		    	$('#save').click(function(){
		    		/**序列化表单参数*/
	    	 		
	    	 		var params = {
	    	 			bdId:$('#selectBdId option:selected').val(),
	    	 			groupId:$('#selectGroupId option:selected').val()
	    	 		};
					var url = "${ctx}/admin/group2bd/addGroup2Bd";
					$.post(url,params,function(data,status){
						if(status == "success"){
							if(data.status == 1){
								window.location = "${ctx}/admin/group2bd/group2bdList"
							}else if(data.status == 0){
								window.location = "${ctx}/admin/group2bd/group2bdList"
							}else if(data.status == 2){
								alert(data.tip);
							}
						}else{
							alert("数据加载失败!");	
						}
					},"json");
		    	});			
				
		    	/** 为返回按钮绑定点击事件 */
				$('#backid').click(function(){
					window.location.href = "${ctx}/admin/group2bd/group2bdList";
				});
		    	
    	 	});
		</script>
	</head>
	<body>
		<table class="table table-bordered table-hover definewidth m10">
		    <form id="addForm" action="" method="POST" class="definewidth m20" enctype="multipart/form-data">
		    	<tr>
		        	<td width="30%" class="tableleft">组名</td>
		        	<td><select id="selectGroupId">
		            <#if groupList??>
		            <#list groupList as group>
		            <option name="${group.groupId}" value="${group.groupId}">
		            ${group.groupId}
		            </option>
		            </#list>
		            </#if>
		            </select></td>
		        	
		            <!--<td>
		            <#if groupList??>
		            <#list groupList as group>				
		            <label><input type="checkbox" name="${group.groupId}" value="${group.groupId}" />${group.groupId}</label>
				  	<#if (group_index+1)%4==0><br/></#if>
					
		            </#list>
		            </#if>
					</td>-->
		        </tr>
		        <tr>
		            <td width="30%" class="tableleft">局向码</td>
		            <td><select id="selectBdId">
		            <#if bureaudirectionList??>
		            <#list bureaudirectionList as bureaudirection>
		            <option name="${bureaudirection.bdId}" value="${bureaudirection.bdId}">
		            ${bureaudirection.bdId}
		            </option>
		            </#list>
		            </#if>
		            </select></td>
		        </tr>
		         <tr>
		        	<td>
		            	
		            </td>
		            <td>
		            &nbsp;&nbsp;<button type="button" class="btn btn-primary" name="save" id="save">保存</button>
		                &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回</button>
		            </td>
	        	</tr>
		    </form>
		</table>
	</body>
</html>