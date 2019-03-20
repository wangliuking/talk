<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
<head>
<meta charset="UTF-8">
<title>播放</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
</head>
<body>
<object id="player" height=280 width=400 classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6" title="双击播放器屏幕可以全屏">
<embed src="${ctx}/${filePathSound}" autoold=true loop=1 hidden=false type="audio/x-wav" height=240 width=340 controls=smallconsole align="middle">
</embed>
<param name="AutoStart" value="1">
<!-- 是否自动播放 -->
<param name="Balance" value="0">
<!-- 调整左右声道平衡 -->
<param name="enabled" value="1">
<!-- 播放器是否可以人为控制 -->
<param name="EnableContextMenu" value="1">
<!-- 是否启用上下文菜单 -->
<param name="url" value="${ctx}/music/demo.wav">
<!-- 播放的文件地址 -->
<param name="PlayCount" value="1">
<!-- 播放次数控制，为整数 -->
<param name="rate" value="1">
<!-- 播放速率控制，1为正常，允许小数，1.0,2.0 -->
<param name="currentPosition" value="0">
<!-- 控件设置：当前位置 -->
<param name="currentMarker" value="0">
<!-- 控件设置，当前标记 -->
<param name="defaultFrame" value="1">
<!-- 显示默认框架 -->
<param name="invokeURLs" value="0">
<!-- 脚本命令设置：是否调用URL -->
<param name="baseURL" value="">
<!-- 脚本命令设置：被调用的URL -->
<param name="stretchToFit" value="1">
<!-- 是否按比例伸展 -->
<param name="volume" value="50">
<!-- 默认声音大小0%-100%，50则为50% -->
<param name="mute" value="0">
<!-- 是否静音 -->
<param name="uiMode" value="Full">
<!-- 播放器显示模式：Full显示全部；mini最简化；None不显示播放控制，只显示视频窗口；invisible全部不显示 -->
<param name="windowlessVideo" value="0">
<!-- 如果是0可以允许全屏，否则只能在窗口中查看 -->
<param name="fullScreen" value="0">
<!-- 开始播放是否自动全屏 -->
<param name="enableErrorDialogs" value="-1">
<!-- 是否启用错误提示报告 -->
<param name="SAMIStyle" value="1">
<!-- SAMI样式 -->
<param name="SAMILang" value="1">
<!-- SAMI语言 -->
<param name="SAMIFilename" value="1">
<!-- 字幕ID -->
<param name="ShowStatusBar" value="1">
</object>
</body>
</html>