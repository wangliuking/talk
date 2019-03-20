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
        <th>源ID</th>
        <th>目标ID</th>
        <th>类型</th>
        <th>内容</th>
        <th>发送时间</th>
        <th>短信会议ID</th>
        <th>发送状态</th>
        <th colspan="2" style="text-align:center">操作</th>
    </tr>
    </thead>
    	<#if smsList??>
    	<#list smsList as sms>
	     <tr>
            <td>${sms.srcId}</td>
            <td>${sms.tarId}</td>
            <td>${sms.type}</td>
            <td>${sms.content}</td>    
            <td>${sms.sendTime}</td>
            <td>${sms.callId}</td>
            <td>
            <#if sms.status==0>
            <font color="green">发送成功</font>
            <#elseif sms.status==1>
            <font color="red">发送失败</font>
            <#elseif sms.status==2>
            <font color="yellow">发送中</font>
            <#elseif sms.status==3>
            <font color="blue">发送超时</font>
            <#elseif sms.status==4>
            <font color="black">存储转发</font>
            </#if>
            </td>
            <#if person=="admin">
            <td><a href="${ctx}/admin/sms/delete?id=${sms.id}">删除</a></td>
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
		toURL="${ctx}/admin/sms/smsList"/></div>
	</#if>

  </body>
</html>