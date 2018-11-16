<!DOCTYPE HTML>
<#assign ctx=request.contextPath>
<html>
<head>
    <title>网管系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/assets/css/main-min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/common/main-min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/bui-min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/config-min.js"></script>
    
    <script src="${ctx}/js/artDialog-6.0.2/dist/dialog-min.js"></script>
	<link rel="stylesheet" href="${ctx}/js/artDialog-6.0.2/css/ui-dialog.css">
	
    <script>
    BUI.use('common/main',function(){
    	/** 定义导航菜单配置信息数组 */
        var config = [
                      {id:'1',// 唯一标识符id
                    	  homePage:'11',// 当前显示的菜单id
                    	  menu:[{text:'系统管理',// 主菜单
                    	  items:[
                    	         {id:'11',text:'呼叫信息管理',href:'${ctx}/admin/callInfo/callInfoList'}
                    	         ]}]}];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
    
    $(function(){
    	$(".dl-log-quit").click(function(){
    		var d = dialog({
				title: '温馨提示',
			    content: '<font size=6 color=red>您确定要退出系统吗?</font>',
			    okValue:'确定',
			    ok:function(){
			    	this.title("请稍等...");
			    	window.location="${ctx}/admin/logout";
			    	return false;
			    },
			    cancelValue:'取消',
			    cancel:function(){}
			});
			d.showModal();
    	});
    });
</script>
</head>
<body>

<div class="header">

    <div class="dl-title">
    
    </div>

    <div class="dl-log"><span class="dl-log-user"><#if session_user??>欢迎您，${session_user.userName}<#else><a href="${ctx}/">请重新登录</a></#if></span>
    <a href="javascript:;" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected">
            <div class="nav-item-inner nav-home">系统管理</div>
            </li>
            <li class="nav-item dl-selected">
            <div class="nav-item-inner nav-order">业务管理</div>
            </li>

        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
</div>
<div style="text-align:center;">
<p></p>
</div>
</body>
</html>