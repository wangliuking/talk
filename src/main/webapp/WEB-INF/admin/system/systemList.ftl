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
        <th width="12%">呼叫通话时长</th>
        <th width="12%">PTT on 时长</th>
        <th width="12%">ptt静默时长</th>
        <th width="12%">GPS上报时间间隔</th>
        <th width="12%">APP用户心跳时间间隔</th>
        <th width="12%">心跳时间间隔</th>
        <th width="12%">TCP监听端口</th>
        <th width="12%">语音端口</th>  
    </tr>
    </thead>
    	<#if systemList??>
    	<#list systemList as system>
	     <tr>
	     	<#if system.callTime??>
            <td>${system.callTime}</td>
            <#else>
            <td>无</td>
            </#if>
            
            <#if system.pttOnTime??>
            <td>${system.pttOnTime}</td>
            <#else>
            <td>无</td>
            </#if>
            
            <#if system.pttSilentTime??>
            <td>${system.pttSilentTime}</td>
            <#else>
            <td>无</td>
            </#if>
            
            <#if system.gpsReportInterval??>
            <td>${system.gpsReportInterval}</td>
            <#else>
            <td>无</td>
            </#if>
            
            <#if system.appHeartInterval??>
            <td>${system.appHeartInterval}</td>
            <#else>
            <td>无</td>
            </#if>
            
            <#if system.audioHeartInterval??>
            <td>${system.audioHeartInterval}</td>
            <#else>
            <td>无</td>
            </#if>
            
            <#if system.tcpListenPort??>
            <td>${system.tcpListenPort}</td>
            <#else>
            <td>无</td>
            </#if>
            
            <#if system.appVoicePort??>
            <td>${system.appVoicePort}</td>
            <#else>
            <td>无</td>
            </#if>

        </tr>
        </#list>
        </#if>
        </table>
        
        <table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th width="11%">本地局向码</th>
        <th width="11%">本地局监听端口</th>
        <th width="11%">本地局语音端口</th>
        <th width="11%">gps服务端口</th>
        <th width="11%">数据库同步端口</th>
        <th width="11%">g729录音目录</th>
        <th width="11%">wav录音目录</th>
        <th width="11%">录音前缀</th>
        <th width="11%">操作</th>
    </tr>
    </thead>
    	<#if systemList??>
    	<#list systemList as system>
	     <tr>
            <td>${system.bdId}</td>
            <td>${system.bdListenPort}</td>
            <td>${system.bdVoicePort}</td>
            <td>${system.gpsServerPort}</td>
            <td>${system.dbSynPort}</td>    
            <td>${system.videoRecPath}</td>
            <td>${system.wavRecPath}</td>
             <td>${system.videoUrlPrefix}</td>
            <#if str2=="2">
            <td><a href="${ctx}/admin/system/showUpdate">修改</a></td>
            <#elseif str2=="1">
            <td>无法修改</td>
            </#if>
        </tr>
        </#list>
        </#if>
        </table>

  </body>
</html>