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
    <script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/artDialog-6.0.2/dist/dialog-min.js"></script>
	<link rel="stylesheet" href="${ctx}/js/artDialog-6.0.2/css/ui-dialog.css">
	<style>#addnew{margin-left:20px;}</style>
	<script type="text/javascript" src="${ctx}/js/angular.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/paging.js"></script>
	<script type="text/javascript">
     
   //添加用户
     $(function(){
    	$("#addUser").click(function(){
    		window.location="${ctx}/admin/user/toAddUser";
    	}); 
     });
    //导出excel文件
    $(function(){
    	$("#exportUser").click(function(){
    		window.location="${ctx}/admin/user/export";
    	}); 
    });
    
    $(function(){
    	//模拟上传excel  
			$("#uploadEventBtn").unbind("click").bind("click", function() {
				$("#uploadEventFile").click();
			});
			$("#uploadEventFile").bind(
					"change",
					function() {
						$("#uploadEventPath").attr("value",
								$("#uploadEventFile").val());
					});
    });
    
   //前台非空验证
   $(function(){
	   $('#userListFrom').submit(function(){
		   	var userId = $('#userId');
			var i="";
			if($.trim(userId.val())==""){
				i = "请输入用户Id";
				userId.focus();
			}
			if(i != ""){
				alert(i);
				return false;
			}else{
				return true;
			}
	   });
   });
   
		//点击上传按钮  
		var uploadBtn = function() {
			var uploadEventFile = $("#uploadEventFile").val();
			if (uploadEventFile == '') {
				alert("请选择excel,再上传");
			} else if (uploadEventFile.lastIndexOf(".xls") < 0) {//可判断以.xls和.xlsx结尾的excel  
				alert("只能上传Excel文件");
			} else {
				var url = "${ctx}/admin/user/upload";
				var formData = new FormData($('#batchUpload')[0]);
				sendAjaxRequest(url, 'POST', formData);
			}
		};
		var sendAjaxRequest = function(url, type, data) {
			$.ajax({
				url : url,
				type : type,
				data : data,
				success : function(result) {
					$('#upload').modal('hide');
					alert("excel上传成功");
				},
				error : function() {
					alert("excel上传失败");
				},
				cache : false,
				contentType : false,
				processData : false
			});
		};
     </script>
</head>
<body ng-app="app" ng-controller="userList">
<div class="modal fade" id="upload" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="color-line"></div>
				<div class="modal-header ">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<small class="font-bold">添加excel文件</small>
				</div>
				<div class="modal-body pad-0" style="margin:2px;padding:2px;">
					<form enctype="multipart/form-data" id="batchUpload" action=""
						method="post" class="form-horizontal">
						<div class="row">
							<div class="col-md-2" style="padding-right:0px;margin-right:0px;">
								<button class="btn btn-success btn-xs" id="uploadEventBtn"
									style="height:26px;margin-left:20px;" type="button">选择文件</button>
							</div>
							<div class="col-md-4" style="padding-left:0px;margin-left:0px;">
								<input type="file" name="file" style="width:0px;height:0px;"
									id="uploadEventFile"> <input id="uploadEventPath"
									disabled="disabled" type="text" placeholder="请选择excel表"
									style="border: 1px solid #e6e6e6; height: 26px;width: 200px;">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger btn-sm"
						onclick="uploadBtn()">上传</button>
				</div>
			</div>
		</div>
	</div>

<form class="form-inline definewidth m20" action="" method="post" id="userListFrom">  
    用户名称：
    <input type="text" name="userId" id="userId"class="abc input-default" placeholder="请输入需要查询的用户ID" value="">&nbsp;&nbsp;  
    <button id="searchUserId" type="button" class="btn btn-primary" ng-click="search(1)">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
    	<th>序号</th>
        <th>用户ID</th>
        <th>用户名</th>
        <th>登录密码</th>
        <th>鉴权码</th>
        <th>优先级</th>
        <th>用户类型</th>
        <th>最近一次登录时间</th>
        <th>登录状态</th>
        <th colspan="2" style="text-align:center">操作</th>
    </tr>
    </thead>
    <tbody>
	     <tr ng-repeat="x in userList">
	     	<td>{{$index+1}}</td>
            <td>{{x.userId}}</td>
            <td>{{x.userName}}</td>
            <td>{{x.password}}</td>
            <td>{{x.authenticateCode}}</td>
            <td>{{x.priority}}</td>
            <td>{{x.type}}</td>
            <td>{{x.loginTime}}</td>
            <td ng-if="x.loginStatus=='1'"><font color=green>已登录</font></td>
            <td ng-if="x.loginStatus=='0'"><font color=red>未登录</font></td>                     
            <td ng-if="x.userId!='admin'"><a href="#" ng-click="updateUser($index)">修改</a></td>
            <td ng-if="x.userId=='admin'">无法修改</td>
            <td ng-if="x.userId!='admin'"><a href="#" ng-click="deleteUser($index)">删除</a></td>  
            <td ng-if="x.userId=='admin'">无法删除</td>                    
        </tr>
        </tbody>
        </table>
        <div class="page">
			<div class="page-info">
				每页显示 <select id="page-limit" ng-change="refresh()" ng-model="count">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
				</select> 条, <span ng-model="index">显示{{start}}-</span><span
					ng-model="lastIndex">{{lastIndex}}条,</span> <span ng-model="totals">总计{{totals}}条</span>
			</div>
			<div class="page-paging"></div>
		</div>
	<button type="button" class="btn btn-success" id="addUser" style="margin-left:27px;">添加新的用户</button>
	<button type="button" class="btn btn-info" id="exportUser" style="margin-left:27px;">导出用户列表</button>
	<button type="submit" class="btn btn-danger" data-toggle="modal" data-target="#upload" style="margin-left:27px;">导入用户列表</button>
	
	<script type="text/javascript">
		var frist = 0;
		init = function() {
			var app = angular.module("app", []);
			var pageSize = $("#page-limit").val();
			var userId = $("#userId").val();
			app.controller("userList", function($scope, $http) {
				$scope.count = "10";//每页数据显示默认值
				$http.get(
						"${ctx}/admin/user/userList?"
								+ "start=0&limit=" + pageSize + "&userId=" +userId).success(
						function(response) {
							$scope.userList = response.userList;
							$scope.totals = response.totals;
							console.log($scope.userList);
							pagging(1, parseInt($scope.totals), $scope);
						});
				/* 刷新数据 */
				$scope.refresh = function() {
					$scope.search(1);
				};
				
				/*删除*/
				$scope.deleteUser = function(id){
					var tempData = $scope.userList[id];
					var d = dialog({
						title: '温馨提示',
					    content: '<font size=6 color=red>您确定要删除对应的数据吗?</font>',
					    okValue:'确定',
					    ok:function(){
					    	this.title("请稍等...");
					    	window.location="${ctx}/admin/user/delete?userId="+tempData.userId;
					    	return false;
					    },
					    cancelValue:'取消',
					    cancel:function(){}
					});
					d.showModal();
				};
				/* 跳转到修改 */
				$scope.updateUser = function(id) {		
					var tempData = $scope.userList[id];
					window.location="${ctx}/admin/user/showUpdate?userId="+tempData.userId;
				};

				/* 查询数据 */
				$scope.search = function(page) {
					var pageSize = $("#page-limit").val();
					var userId = $("#userId").val();
					var start = 1, limit = pageSize;
					frist = 0;
					page = parseInt(page);
					if (page <= 1) {
						start = 0;

					} else {
						start = (page - 1) * pageSize;
					}
					$http.get(
							"${ctx}/admin/user/userList?"
									+ "start=" + start + "&limit=" + limit + "&userId=" +userId)
							.success(function(response) {
								$scope.userList = response.userList;
								$scope.totals = response.totals;
								console.log($scope.userList);
								pagging(1, parseInt($scope.totals), $scope);
							});
				};
				//分页点击
				$scope.pageClick = function(page, totals, totalPages) {			
					var pageSize = $("#page-limit").val();
					var userId = $("#userId").val();
					var start = 1, limit = pageSize;
					page = parseInt(page);
					if (page <= 1) {
						start = 0;
					} else {
						start = (page - 1) * pageSize;
					}
					$http.get(
							"${ctx}/admin/user/userList?"
									+ "start=" + start + "&limit=" + pageSize + "&userId=" +userId)
							.success(function(response) {
								$scope.start = (page - 1) * pageSize + 1;
								$scope.lastIndex = page * pageSize;
								if (page == totalPages) {
									if (totals > 0) {
										$scope.lastIndex = totals;
									} else {
										$scope.start = 0;
										$scope.lastIndex = 0;
									}
								}
								$scope.userList = response.userList;
								$scope.totals = response.totals;
								console.log($scope.userList);
							});

				};
			});
		};

		// 刷新数据
		refresh = function() {
			var appElement = document
					.querySelector('[ng-controller=userList]');
			var $scope = angular.element(appElement).scope();
			// 调用$scope中的方法
			$scope.refresh();

		};
		
		/* 数据分页 */
		pagging = function(currentPage, totals, $scope) {
			var pageSize = $("#page-limit").val();
			var totalPages = (parseInt(totals, 10) / pageSize) < 1 ? 1 : Math
					.ceil(parseInt(totals, 10) / pageSize);
			var start = (currentPage - 1) * pageSize + 1;
			var end = currentPage * pageSize;
			if (currentPage == totalPages) {
				if (totals > 0) {
					end = totals;
				} else {
					start = 0;
					end = 0;
				}
			}
			$scope.start = start;
			$scope.lastIndex = end;
			$scope.totals = totals;
			if (totals > 0) {
				$(".page-paging").html('<ul class="pagination"></ul>');
				$('.pagination').twbsPagination({
					totalPages : totalPages,
					visiblePages : 10,
					version : '1.1',
					startPage : currentPage,
					onPageClick : function(event, page) {
						if (frist == 1) {
							$scope.pageClick(page, totals, totalPages);
						}
						frist = 1;

					}
				});
			}

		};
		init();
	</script>
	
        </body>
</html>