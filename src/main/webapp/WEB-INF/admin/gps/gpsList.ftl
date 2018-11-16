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
</head>
<body>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>用户ID</th>
        <th>经度</th>
        <th>纬度</th>
        <th>上报类型</th>
        <th>GPS接受时间</th>
        <th colspan="2" style="text-align:center">操作</th>
    </tr>
    </thead>
    	<#if gpsList??>
    	<#list gpsList as gps>
	     <tr>
            <td>${gps.userId}</td>
            <td>${gps.longitude}</td>
            <td>${gps.latitude}</td>
            <td>${gps.type}</td>    
            <td>${gps.recvTime?string("yyyy-MM-dd HH:mm:ss")}</td>
            <#if person=="admin">
            <td><a href="${ctx}/admin/gps/delete?id=${gps.id}">删除</a></td>
            <#elseif person!="admin">
            <td>无法删除</td>
            </#if>
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
		toURL="${ctx}/admin/gps/gpsList"/></div>
	</#if>

  </body>
</html>