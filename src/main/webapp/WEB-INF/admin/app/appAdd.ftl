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
		<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.js"></script>
	    <script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script> 	
    	<script type="text/javascript">
    	function ajaxFileUpload() {
            $.ajaxFileUpload
            (
                {
                    url: '${ctx}/admin/app/upload', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'pathName', //文件上传域的ID
                    dataType: 'HTML', //返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                 		$('#path').attr("value", data);
                 		$('#listener').attr("value", "1");
                 		alert("上传成功！");	
                        if (typeof (data.error) != 'undefined') {
                            if (data.error != '') {
                                alert(data.error);
                            } else {
                                alert(data.msg);
                            }
                        }
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                        alert(e);
                    }
                }
            );    	
    	}
    	 	$(function(){
		    	/** 为返回按钮绑定点击事件 */
				$('#backid').click(function(){
					window.location.href = "${ctx}/admin/app/appList";
				});
    	 	});
    	 	
    	 	//前台非空验证
 		   $(function(){
 			   $('#addAppForm').submit(function(){
 				   	var appName = $('#appName');
 				   	var appVersion = $('#appVersion');
 				   	var content = $('#content');
 				   	var listener = $('#listener');
 					var i="";
 					if($.trim(appName.val())==""){
 						i = "请输入app名称";
 						appName.focus();
 					}else if($.trim(appVersion.val())==""){
 						i = "请输入app版本";
 						appVersion.focus();
 					}else if($.trim(content.val())==""){
 						i = "请输入内容";
 						content.focus();
 					}else if($.trim(listener.val())==""){
 						i = "请等待上传完毕！";
 					}
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
		<table class="table table-bordered table-hover definewidth m10">
		    <form id="addAppForm" action="${ctx}/admin/app/add" method="POST" class="definewidth m20" enctype="multipart/form-data">
		    <input type="hidden" id="listener" name="listener" value=""/>
		        <tr>
		            <td width="30%" class="tableleft">app名称</td>
		            <td><input type="text" name="appName" id="appName"/></td>
		        </tr>
		        <tr>
		        	<td width="30%" class="tableleft">app版本</td>
		            <td><input type="text" name="appVersion" id="appVersion"/></td>
		        </tr>
		         <tr>
		        	<td width="30%" class="tableleft">内容介绍</td>
		            <td><input type="text" name="content" id="content"/></td>
		        </tr>
		         <tr>
		        	<td width="30%" class="tableleft">上传文件</td>
		            <td><input type="file" name="pathName" id="pathName" onchange="ajaxFileUpload()" value=""/></td>
		            <input type="hidden" id="path" name="path" value=""/>
		        </tr>
		         <tr>
		         <td width="30%" class="tableleft">选择操作</td>
		            <td>
		            &nbsp;&nbsp;<button type="submit" class="btn btn-primary">保存</button>
		                &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回</button>
		            </td>
	        	</tr>
		    </form>
		</table>
	</body>
</html>