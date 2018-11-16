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
    
    <!-- 引入日期js控件 -->
	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

	<script type="text/javascript">
		$(function(){
			//返回列表页面操作
			$('#backid').click(function(){
				window.location.href="${ctx}/admin/system/systemList";
			});	
		});
		
		//前台非空验证
		   $(function(){
			   $('#updateSystem').submit(function(){			
					var i="";
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
<form action="${ctx}/admin/system/update" method="post" class="definewidth m20" id="updateSystem">
<table class="table table-bordered table-hover ">
    <tr>
        <td class="tableleft">呼叫通话时长</td>
        <td width="10%">
        <input type="text" id="callTime" name="callTime" value="${system.callTime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td width="10%" class="tableleft">PTT on 时长</td>
        <td width="10%">
        <input type="text" id="pttOnTime" name="pttOnTime" value="${system.pttOnTime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td width="35%" class="tableleft"></td>
    </tr>
    <tr>
        <td class="tableleft">ptt静默时长</td>
        <td>
        <input type="text" id="pttSilentTime" name="pttSilentTime" value="${system.pttSilentTime}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td class="tableleft">GPS上报时间间隔</td>
        <td>
        <input type="text" id="gpsReportInterval" name="gpsReportInterval" value="${system.gpsReportInterval}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td width="35%" class="tableleft"></td>
    </tr>
    <tr>
        <td class="tableleft">用户心跳时间间隔</td>
        <td>
       	<input type="text" id="appHeartInterval" name="appHeartInterval" value="${system.appHeartInterval}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td class="tableleft">建立通道后心跳时间间隔</td>
        <td>
        <input type="text" id="audioHeartInterval" name="audioHeartInterval" value="${system.audioHeartInterval}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td width="35%" class="tableleft"></td>
    </tr>
    <tr>
        <td class="tableleft">TCP监听端口</td>
        <td>
        <input type="text" id="tcpListenPort" name="tcpListenPort" value="${system.tcpListenPort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td class="tableleft">语音端口</td>
        <td>
        <input type="text" id="appVoicePort" name="appVoicePort" value="${system.appVoicePort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td width="35%" class="tableleft"></td>
    </tr>
    <tr>
        <td class="tableleft">本地局向码</td>
        <td>
        <input type="text" id="bdId" name="bdId" value="${system.bdId}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td class="tableleft">本地局监听端口</td>
        <td>
        <input type="text" id="bdListenPort" name="bdListenPort" value="${system.bdListenPort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td width="35%" class="tableleft"></td>
    </tr>
    <tr>
        <td class="tableleft">本地局语音端口</td>
        <td>
       	<input type="text" id="bdVoicePort" name="bdVoicePort" value="${system.bdVoicePort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td class="tableleft">gps服务端口</td>
        <td>
        <input type="text" id="gpsServerPort" name="gpsServerPort" value="${system.gpsServerPort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td width="35%" class="tableleft"></td>
    </tr>
    <tr>
        <td class="tableleft">数据库同步端口</td>
        <td>
        <input type="text" id="dbSynPort" name="dbSynPort" value="${system.dbSynPort}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength = 11/>
        </td>
        <td class="tableleft">g729录音目录</td>
        <td>
       	<input type="text" id="g729RecPath" name="g729RecPath" value="${system.g729RecPath}" maxlength = 256/>
        </td>
        <td width="35%" class="tableleft"></td>
    </tr>
    <tr>
        <td class="tableleft">wav录音目录</td>
        <td>
        <input type="text" id="wavRecPath" name="wavRecPath" value="${system.wavRecPath}" maxlength = 256/>
        </td>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
        <td width="35%" class="tableleft"></td>
    </tr>
</table>
</form>
</body>
</html>
