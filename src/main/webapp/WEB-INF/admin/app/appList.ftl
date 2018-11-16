<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
<head>
    <title>app</title>
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
    	$("#addApp").click(function(){
    		window.location="${ctx}/admin/app/showAdd";
    	}); 
     });
	</script>
</head>
<body>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>app名称</th>
        <th>app版本</th>
        <th>内容介绍</th>
        <th>文件信息</th>
        <th>上传时间</th>
        <th colspan="2" style="text-align:center">操作</th>    
    </tr>
    </thead>
    	<#if appList??>
    	<#list appList as app>
	     <tr>
            <td>${app.appName}</td>
            <td>${app.appVersion}</td>
            <td>${app.content}</td>
            <td><a href="${ctx}/admin/app/download?path=${app.path?substring(8)}">${app.path?substring(8)}</a></td>
            <td>${app.uploadTime?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td><a href="${ctx}/admin/app/delete?id=${app.id}&path=${app.path}">删除</a></td>
        </tr>
        </#list>
        </#if>
        </table>
        
<#if pageModel??>
	<!-- 分页标签-->
	<div id="pagerInfo"
		style="margin: 5px 0px; text-align: center; background-color: white; color: blue">
		共${pageModel.recordCount}条,
		分${((pageModel.recordCount+pageModel.pageSize
		-1)/pageModel.pageSize)?int}页</div>
	<div id="pager"><#import "../common/pager.ftl" as page>
		<@page.pager pageIndex=pageModel.pageIndex pageSize=pageModel.pageSize
		recordCount=pageModel.recordCount
		toURL="${ctx}/admin/user/userList"/></div>
	</#if>
	<button type="button" class="btn btn-success" id="addApp" style="margin-left:27px;">添加新的app</button>
  </body>
</html>