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
	<style>#addnew{margin-left:20px;}</style>
	<script type="text/javascript">
     function deleteGroup2Bd(bdId,groupId){
    		var d = dialog({
				title: '温馨提示',
			    content: '<font size=6 color=red>您确定要删除对应的数据吗?</font>',
			    okValue:'确定',
			    ok:function(){
			    	this.title("请稍等...");
			    	window.location="${ctx}/admin/group2bd/delete?bdId="+bdId+"&groupId="+groupId;
			    	return false;
			    },
			    cancelValue:'取消',
			    cancel:function(){}
			});
			d.showModal();
    	}
     //修改关联数据
     function updateGroup2Bd(bdId,groupId){
    	 window.location="${ctx}/admin/group2bd/showUpdate?bdId="+bdId+"&groupId="+groupId;
     }
     
   //添加用户
     $(function(){
    	$("#addBureaudirection").click(function(){
    		window.location="${ctx}/admin/group2bd/toAddGroup2Bd";
    	}); 
     });
     </script>
</head>
<body>
<form class="form-inline definewidth m20" action="${ctx}/admin/group2bd/group2bdList" method="post">  
    组id：
    <input type="text" name="group.groupId" id="group.groupId" class="abc input-default" placeholder="请输入需要查询的组id" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>组ID</th>
        <th>组名称</th> 
        <th>局向码</th>
        <th>局向名</th>   
        <th colspan="2" style="text-align:center">操作</th>
    </tr>
    </thead>
    	<#if group2bdList??>
    	<#list group2bdList as group2bd>
	     <tr>
            <td>${group2bd.group.groupId}</td>
            <td>${group2bd.group.groupName}</td>
            <td>${group2bd.bureaudirection.bdId}</td>
            <td>${group2bd.bureaudirection.bdName}</td>
            
            <td>
            	<a href="javascript:;" onclick="updateGroup2Bd('${group2bd.bureaudirection.bdId}','${group2bd.group.groupId}');">修改</a>
            </td>

             <td>         	
            	<a href="javascript:;" onclick="deleteGroup2Bd('${group2bd.bureaudirection.bdId}','${group2bd.group.groupId}');">删除</a>
            </td>

        </tr>
        </#list>
        </#if>
        </table>
        <#if pageModel??>
	<!-- 分页标签-->
	<div id="pagerInfo"
		style="margin: 5px 0px; text-align: center; background-color: #FFFF00; color: blue">
		共${pageModel.recordCount}条,
		分${((pageModel.recordCount+pageModel.pageSize
		-1)/pageModel.pageSize)?int}页</div>
	<div id="pager"><#import "../common/pager.ftl" as page>
		<@page.pager pageIndex=pageModel.pageIndex pageSize=pageModel.pageSize
		recordCount=pageModel.recordCount
		toURL="${ctx}/admin/group2bd/group2bdList"/></div>
	</#if>
	<button type="button" class="btn btn-success" id="addBureaudirection" style="margin-left:27px;">添加新的局向和组关联</button>
        </body>
</html>