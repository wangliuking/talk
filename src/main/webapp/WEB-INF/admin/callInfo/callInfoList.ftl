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
        <th>呼叫会议ID</th>
        <th>主叫ID</th>
        <th>被叫ID</th>
        <th>PTT ID</th>
        <th>呼叫状态</th>
        <th>呼叫类型</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>结束原因</th>
        <th>播放录音</th>
        <th>下载</th>
        <th colspan="2" style="text-align:center">操作</th>
    </tr>
    </thead>
    	<#if callInfoList??>
    	<#list callInfoList as callInfo>
	     <tr>
            <td>${callInfo.callId}</td>
            <td>${callInfo.callingId}</td>
            <td>${callInfo.calledId}</td>
            <td>${callInfo.pttId}</td>
            <td>${callInfo.callStatus}</td>
            <td>${callInfo.callType}</td>
            <td>${callInfo.startTime?string("yyyy-MM-dd HH:mm:ss")}</td>
        	<td>${callInfo.endTime?string("yyyy-MM-dd HH:mm:ss")}</td>
        	<td>${callInfo.endReason}</td>
        	<#if session_user.browserType=="IE">
        	<td><a href="javascript:void(0)" onclick="aClick('${callInfo.filePath?substring(43)}')">打开</a></td>
        	<#elseif session_user.browserType=="360">
        	<td><a href="javascript:void(0)" onclick="aClick('${callInfo.filePath?substring(43)}')">打开</a></td>
            <#elseif callInfo.callType==2>
        	<td><a href="javascript:void(0)" onclick="aClick('${callInfo.filePath?substring(43)}')">视频</a></td>
        	<#else>
        	<td><audio src="${ctx}/${callInfo.filePath?substring(43)}" controls>您的浏览器不支持此播放功能</audio></td>
        	</#if>
        	<td><a href="${ctx}/download?downloadId=${callInfo.filePath?substring(43)}">下载</a></td>
        	<#if person=="admin">
            <td><a href="${ctx}/admin/callInfo/delete?id=${callInfo.id}">删除</a></td>
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
		toURL="${ctx}/admin/callInfo/callInfoList"/></div>
	</#if>

  </body>
</html>
<script type="text/javascript">
	function aClick(path){
		window.open ("${ctx}/admin/callInfo/sound?filePath="+path, "newwindow", "height=300,width=400,toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	}
</script>
