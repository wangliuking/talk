<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
	<head>
    	<title>添加用户和组关联</title>
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
    	 		/** 为保存操作绑定点击事件*/
		    	$('#save').click(function(){
		    		/**序列化表单参数*/
		    		var userId = $('#selectUserId option:selected').val();
		    		var tempGroupIds = [];
	    	 		$('input[name="groupIds"]').each(function(){  
                    //此处如果用attr，会出现第三次失效的情况 
                    if($(this).is(':checked')){
                    	tempGroupIds.push($(this).val());
                    }                                       
                	});
                	console.log("userId:"+userId+"tempGroupIds"+tempGroupIds);
                	if(tempGroupIds==""){
                		alert("未选择任何组!");
                		return false;
                	}
                	console.log(tempGroupIds);
					$.ajax({
						type : "GET",
						url : "${ctx}/admin/user2group/addUser2Group?userId="+userId+"&groupIds="+tempGroupIds,
						dataType : "json",
						success : function(data,status) {
							if(status == "success"){
								if(data.status == 1){
									window.location = "${ctx}/admin/user2group/toUser2groupList"
								}else if(data.status == 0){
									window.location = "${ctx}/admin/user2group/toUser2groupList"
								}else if(data.status == 2){
									alert(data.tip);
								}
							}else{
								alert("数据加载失败!");	
							}
						}
					});
		    	});			
				
		    	/** 为返回按钮绑定点击事件 */
				$('#backid').click(function(){
					window.location.href = "${ctx}/admin/user2group/toUser2groupList";
				});
				
				/** 全选和反选 */
				$('input[name="selectall"]').click(function(){   
            	if($(this).is(':checked')){  
                $('input[name="groupIds"]').each(function(){  
                    //此处如果用attr，会出现第三次失效的情况  
                    $(this).prop("checked",true);  
                });  
        }else{  
            $('input[name="groupIds"]').each(function(){  
                    $(this).removeAttr("checked",false);  
                });  
        }  
              
    });
		    	
    	 	});
		</script>
	</head>
	<body ng-app="app" ng-controller="addUser2Group">
	<form id="addForm" action="" method="POST" class="definewidth m20"
		enctype="multipart/form-data">
		<table class="table table-bordered table-hover">
			<div class="table definewidth m10">
				用户ID号
				<select id="selectUserId" ng-change="refresh()" ng-model="chooseUserId">
					<option>请选择</option>
					<option ng-repeat="x in userList">{{x.userId}}</option>
				</select>
			</div>

			<thead>
				<tr class="tr-gray">
					<th style="width:2%;"><input type="checkbox" id="table-checkbox"
						class="table-check" name="selectall" /></th>
					<th style="width:40%;">组ID</th>
					<th style="width:40%;">组名称</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="x in groupList">
					<td><input type="checkbox" name="groupIds"
						ng-value="x.groupId" ng-if="x.choosed==0"/></td>
					<td>{{x.groupId}}</td>
					<td>{{x.groupName}}</td>
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
		<button type="button" class="btn btn-primary" name="save" id="save">保存</button>
		<button type="button" class="btn btn-success" name="backid"
			id="backid">返回</button>
	</form>
	<script type="text/javascript">
		var frist = 0;
		init = function() {
			var app = angular.module("app", []);
			var pageSize = $("#page-limit").val();
			app.controller("addUser2Group", function($scope, $http) {
				var selectUserId = $("#selectUserId option:selected").text();
				$scope.count = "10";//每页数据显示默认值
				$scope.chooseUserId = "请选择";
				$http.get(
						"${ctx}/admin/user2group/addUser2GroupData?"
								+ "start=0&limit=" + pageSize + "&selectUserId=" + selectUserId).success(
						function(response) {
							$scope.userList = response.userList;
							$scope.groupList = response.groupList;
							$scope.totals = response.totals;
							console.log($scope.groupList);
							pagging(1, parseInt($scope.totals), $scope);
						});
				/* 刷新数据 */
				$scope.refresh = function() {
				//默认不选中
				$('input[name="selectall"]').removeAttr("checked",false);
				$('input[name="groupIds"]').each(function(){  
                    $(this).removeAttr("checked",false);  
                });
					$scope.search(1);
				};

				/* 查询数据 */
				$scope.search = function(page) {
					var selectUserId = $("#selectUserId option:selected").text();
					var pageSize = $("#page-limit").val();
					var start = 1, limit = pageSize;
					frist = 0;
					page = parseInt(page);
					if (page <= 1) {
						start = 0;

					} else {
						start = (page - 1) * pageSize;
					}
					$http.get(
							"${ctx}/admin/user2group/addUser2GroupData?"
									+ "start=" + start + "&limit=" + limit + "&selectUserId=" + selectUserId)
							.success(function(response) {
								$scope.userList = response.userList;
								$scope.groupList = response.groupList;
								$scope.totals = response.totals;
								console.log($scope.groupList);
								pagging(1, parseInt($scope.totals), $scope);
							});
				};
				//分页点击
				$scope.pageClick = function(page, totals, totalPages) {
					//默认不选中
					$('input[name="selectall"]').removeAttr("checked",false);
					$('input[name="groupIds"]').each(function(){  
                    	$(this).removeAttr("checked",false);  
                	});
				
					var selectUserId = $("#selectUserId option:selected").text();
					var pageSize = $("#page-limit").val();
					var start = 1, limit = pageSize;
					page = parseInt(page);
					if (page <= 1) {
						start = 0;
					} else {
						start = (page - 1) * pageSize;
					}
					$http.get(
							"${ctx}/admin/user2group/addUser2GroupData?"
									+ "start=" + start + "&limit=" + pageSize + "&selectUserId=" + selectUserId)
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
								$scope.groupList = response.groupList;
								$scope.totals = response.totals;
								console.log($scope.groupList);
							});

				};
			});
		};

		// 刷新数据
		refresh = function() {
			var appElement = document
					.querySelector('[ng-controller=addUser2Group]');
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