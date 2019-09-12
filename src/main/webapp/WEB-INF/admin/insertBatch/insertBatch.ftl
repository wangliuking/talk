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
        <script type="text/javascript" src="${ctx}/js/angular.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/paging.js"></script>
    	<script type="text/javascript">
    	 	$(function(){
                $('#save').click(function(){
                    /**序列化表单参数*/
                    var num1 = $("#num1").val();
                    var num2 = $("#num2").val();
                    if((parseInt(num2)-parseInt(num1))>1000){
                        alert("批量开户数过多，请重新填写");
                        return false;
					}
                    var typeInsert = $("input[type='radio']:checked").val();
                    var tempGroupIds = [];
                    $('input[name="groupIds"]').each(function(){
                        //此处如果用attr，会出现第三次失效的情况
                        if($(this).is(':checked')){
                            tempGroupIds.push($(this).val());
                        }
                    });
                    if(tempGroupIds==""){
                        alert("未选择任何组");
                        return false;
                    }
                    console.log(tempGroupIds);
                    $.ajax({
                        type : "POST",
                        url : "${ctx}/admin/user/insertBatch",
                        dataType : "json",
						data : {num1:num1,num2:num2,typeInsert:typeInsert,tempGroupIds:tempGroupIds},
                        success : function(response) {
                            alert("批量添加成功！");
                            location.reload();
                        }
                    });
                });

                /** 为返回按钮绑定点击事件 */
				$('#backid').click(function(){
					window.location.href = "${ctx}/admin/user/userList";
				});
    	 	});
    	 	
    	 	//前台非空验证
 		   $(function(){
 			   $('#Form').submit(function(){
 				   	var num1 = $('#num1');  
 				   	var num2 = $('#num2');	
 					var i="";
 					if($.trim(num1.val())==""){
 						i = "请输入起始id号";
 						num1.focus();
 					}else if($.trim(num2.val())==""){
 						i = "请输入结束id号";
 						num2.focus();
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
	<body ng-app="app" ng-controller="insertBatch">
		<table class="table table-bordered table-hover definewidth m10">
		    <form class="definewidth m20" enctype="multipart/form-data">
		        <tr>
		            <td width="20%" class="tableleft">起始id号</td>
		            <td><input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="num1" name="num1" maxlength = 10/></td>
		        </tr>
		        <tr>
		            <td width="20%" class="tableleft">结束id号</td>
		            <td><input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="num2" name="num2" maxlength = 10/></td>
		        </tr>
                <tr>
                    <td width="20%" class="tableleft">开户类型</td>
                    <td><input type="radio" class="magic-checkbox" name="typeInsert" value="0" checked/>用户
                        <input type="radio" class="magic-checkbox" name="typeInsert" value="1" />组
                    </td>
                </tr>
                <tr ng-repeat="x in groupList">
                    <td><input type="checkbox" name="groupIds"
                               ng-value="x.groupId"/></td>
                    <td>{{x.groupId}}</td>
                    <td>{{x.groupName}}</td>
                </tr>
		         <tr>
		        	<td>	            	
		            </td>
		            <td>
		            &nbsp;&nbsp;<button type="submit" class="btn btn-primary" name="save" id="save">保存</button>
		                &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回</button>
		            </td>
	        	</tr>
		    </form>
		</table>
        <script type="text/javascript">
            init = function() {
                var app = angular.module("app", []);
                app.controller("insertBatch", function($scope, $http) {
                    $http.get("${ctx}/admin/group/groupList?start=0&limit=10000&groupId=").success(
                            function(response) {
                                $scope.groupList = response.groupList;
                            });
                });
            };
            init();
        </script>
	</body>
</html>