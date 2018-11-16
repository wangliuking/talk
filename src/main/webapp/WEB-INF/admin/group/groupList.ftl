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
     function deletes(groupId){
    		var d = dialog({
				title: '温馨提示',
			    content: '<font size=6 color=red>您确定要删除对应的数据吗?</font>',
			    okValue:'确定',
			    ok:function(){
			    	this.title("请稍等...");
			    	window.location="${ctx}/admin/group/delete?groupId="+groupId;
			    	return false;
			    },
			    cancelValue:'取消',
			    cancel:function(){}
			});
			d.showModal();
    	}
     
     //添加
     $(function(){
    	$("#addnew").click(function(){
    		window.location="${ctx}/admin/group/toAddGroup";
    	}); 
     });
     
     //前台非空验证
     $(function(){
  	   $('#groupListForm').submit(function(){
  		   	var groupId = $('#groupId');
  			var i="";
  			if($.trim(groupId.val())==""){
  				i = "请输入组Id";
  				groupId.focus();
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
<body ng-app="app" ng-controller="groupList">
<form class="form-inline definewidth m20" action="${ctx}/admin/group/groupList" method="post" id="groupListForm">  
    组ID：
    <input type="text" name="groupId" id="groupId"class="abc input-default" placeholder="请输入需要查询的组ID" value="">&nbsp;&nbsp;  
    <button id="searchGroupId" type="button" class="btn btn-primary" ng-click="search(1)">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
    	<th>序号</th>
        <th>组id号</th>
        <th>组名</th>
        <th>类型</th>
        <th>PTT静默时长</th>
        <th>一次呼叫总得通话时间限定</th>
        <th>单次PTT授权最长时间</th>
        <th colspan="2" style="text-align:center">操作</th>
    </tr>
    </thead>
    <tbody>
	     <tr ng-repeat="x in groupList">
	     	<td>{{$index+1}}</td>
            <td>{{x.groupId}}</td>
            <td>{{x.groupName}}</td>
            <td>{{x.type}}</td>
            <td>{{x.pttSilentTime}}</td>
            <td>{{x.callTime}}</td>
            <td>{{x.pttOnTime}}</td>
            <td><a href="#" ng-click="updateGroup($index)">修改</a></td>
            <td><a href="#" ng-click="deleteGroup($index)">删除</a></td>
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
<button type="button" class="btn btn-success" id="addnew" style="margin-left:23px;">添加新的组</button>

<script type="text/javascript">
		var frist = 0;
		init = function() {
			var app = angular.module("app", []);
			var pageSize = $("#page-limit").val();
			var groupId = $("#groupId").val();
			app.controller("groupList", function($scope, $http) {
				$scope.count = "10";//每页数据显示默认值
				$http.get(
						"${ctx}/admin/group/groupList?"
								+ "start=0&limit=" + pageSize + "&groupId=" +groupId).success(
						function(response) {
							$scope.groupList = response.groupList;
							$scope.totals = response.totals;
							console.log($scope.groupList);
							pagging(1, parseInt($scope.totals), $scope);
						});
				/* 刷新数据 */
				$scope.refresh = function() {
					$scope.search(1);
				};
				
				/*删除*/
				$scope.deleteGroup = function(id){
					var tempData = $scope.groupList[id];
					var d = dialog({
						title: '温馨提示',
					    content: '<font size=6 color=red>您确定要删除对应的数据吗?</font>',
					    okValue:'确定',
					    ok:function(){
					    	this.title("请稍等...");
					    	window.location="${ctx}/admin/group/delete?groupId="+tempData.groupId;
					    	return false;
					    },
					    cancelValue:'取消',
					    cancel:function(){}
					});
					d.showModal();
				};
				/* 跳转到修改 */
				$scope.updateGroup = function(id) {		
					var tempData = $scope.groupList[id];
					window.location="${ctx}/admin/group/showUpdate?groupId="+tempData.groupId;
				};

				/* 查询数据 */
				$scope.search = function(page) {
					var pageSize = $("#page-limit").val();
					var groupId = $("#groupId").val();
					var start = 1, limit = pageSize;
					frist = 0;
					page = parseInt(page);
					if (page <= 1) {
						start = 0;

					} else {
						start = (page - 1) * pageSize;
					}
					$http.get(
							"${ctx}/admin/group/groupList?"
									+ "start=" + start + "&limit=" + limit + "&groupId=" +groupId)
							.success(function(response) {
								$scope.groupList = response.groupList;
								$scope.totals = response.totals;
								console.log($scope.groupList);
								pagging(1, parseInt($scope.totals), $scope);
							});
				};
				//分页点击
				$scope.pageClick = function(page, totals, totalPages) {			
					var pageSize = $("#page-limit").val();
					var groupId = $("#groupId").val();
					var start = 1, limit = pageSize;
					page = parseInt(page);
					if (page <= 1) {
						start = 0;
					} else {
						start = (page - 1) * pageSize;
					}
					$http.get(
							"${ctx}/admin/group/groupList?"
									+ "start=" + start + "&limit=" + pageSize + "&groupId=" +groupId)
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
					.querySelector('[ng-controller=groupList]');
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