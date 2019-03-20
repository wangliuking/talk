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
        <th>gps用户id</th>
        <th>gps密码</th>
        <th colspan="2" style="text-align:center">操作</th>    
    </tr>
    </thead>
    	<#if gpsuserList??>
    	<#list gpsuserList as gpsuser>
	     <tr>
            <td>${gpsuser.gpsUserId}</td>
            <td>${gpsuser.gpsUserPassword}</td>
            <td><a href="${ctx}/admin/gpsuser/showUpdate?gpsUserId=${gpsuser.gpsUserId}">修改</a></td>
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
		toURL="${ctx}/admin/gpsuser/gpsuserList"/></div>
	</#if>

  </body>
</html>