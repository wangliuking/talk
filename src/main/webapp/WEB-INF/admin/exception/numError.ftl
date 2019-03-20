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
				window.location.href="${ctx}/admin/user/toInsertBatch";
			});
			
			//自己根据前面的内容补充表单验证操作
			
			
		});
	</script>
</head>
<body>
<form action="" method="post" class="definewidth m20">
<table class="table table-bordered table-hover ">
    <tr>
        <td class="tableleft"></td>
        <td>
            	<font color="red">批量开户数量过大，请重新操作！</font>
            <button type="button" class="btn btn-success" name="backid" id="backid">返回</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>