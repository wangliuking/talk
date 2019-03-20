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
     function deletes(bdId){
    		var d = dialog({
				title: '温馨提示',
			    content: '<font size=6 color=red>您确定要删除对应的数据吗?</font>',
			    okValue:'确定',
			    ok:function(){
			    	this.title("请稍等...");
			    	window.location="${ctx}/admin/bureaudirection/delete?bdId="+bdId;
			    	return false;
			    },
			    cancelValue:'取消',
			    cancel:function(){}
			});
			d.showModal();
    	}
     
     //添加商品请求
     $(function(){
    	$("#addnew").click(function(){
    		window.location="${ctx}/admin/bureaudirection/toAddBureaudirection";
    	}); 
     });
     
     //前台非空验证
     $(function(){
  	   $('#bureaudirectionListForm').submit(function(){
  		   	var bdId = $('#bdId');
  			var i="";
  			if($.trim(bdId.val())==""){
  				i = "请输入需要查询的局向码";
  				bdId.focus();
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
<body ng-app="app" ng-controller="bureaudirectionList">
<form class="form-inline definewidth m20" action="${ctx}/admin/bureaudirection/bureaudirectionList" method="post" id="bureaudirectionListForm">  
    局向码：
    <input type="text" name="bdId" id="bdId" class="abc input-default" placeholder="请输入需要查询的局向码" value="">&nbsp;&nbsp;  
    <button id="searchBureaudirectionId" type="button" class="btn btn-primary" ng-click="search(1)">查询</button>&nbsp;&nbsp;
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
    	<th>序号</th>
        <th>局向码</th>
        <th>局向IP地址</th>
        <th>局向信令端口</th>
        <th>局向语音端口</th>
        <th>局向名</th>
        <th colspan="2" style="text-align:center">操作</th>
    </tr>
    </thead>
    <tbody>
	     <tr ng-repeat="x in bureaudirectionList">
	     	<td>{{$index+1}}</td>
            <td>{{x.bdId}}</td>            
            <td>{{x.address}}</td>
            <td>{{x.csPort}}</td>
            <td>{{x.voicePort}}</td>
            <td>{{x.bdName}}</td>
            <td><a href="#" ng-click="updateBureaudirection($index)">修改</a></td>
            <td><a href="#" ng-click="deleteBureaudirection($index)">删除</a></td>
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
	<button type="button" class="btn btn-success" id="addnew" style="margin-left:23px;">添加新的局向</button>
	
	<script type="text/javascript">
		var frist = 0;
		init = function() {
			var app = angular.module("app", []);
			var pageSize = $("#page-limit").val();
			var bdId = $("#bdId").val();
			app.controller("bureaudirectionList", function($scope, $http) {
				$scope.count = "10";//每页数据显示默认值
				$http.get(
						"${ctx}/admin/bureaudirection/bureaudirectionList?"
								+ "start=0&limit=" + pageSize + "&bdId=" +bdId).success(
						function(response) {
							$scope.bureaudirectionList = response.bureaudirectionList;
							$scope.totals = response.totals;
							console.log($scope.bureaudirectionList);
							pagging(1, parseInt($scope.totals), $scope);
						});
				/* 刷新数据 */
				$scope.refresh = function() {
					$scope.search(1);
				};
				
				/*删除*/
				$scope.deleteBureaudirection = function(id){
					var tempData = $scope.bureaudirectionList[id];
					var d = dialog({
						title: '温馨提示',
					    content: '<font size=6 color=red>您确定要删除对应的数据吗?</font>',
					    okValue:'确定',
					    ok:function(){
					    	this.title("请稍等...");
					    	window.location="${ctx}/admin/bureaudirection/delete?bdId="+tempData.bdId;
					    	return false;
					    },
					    cancelValue:'取消',
					    cancel:function(){}
					});
					d.showModal();
				};
				/* 跳转到修改 */
				$scope.updateBureaudirection = function(id) {		
					var tempData = $scope.bureaudirectionList[id];
					window.location="${ctx}/admin/bureaudirection/showUpdate?bdId="+tempData.bdId;
				};

				/* 查询数据 */
				$scope.search = function(page) {
					var pageSize = $("#page-limit").val();
					var bdId = $("#bdId").val();
					var start = 1, limit = pageSize;
					frist = 0;
					page = parseInt(page);
					if (page <= 1) {
						start = 0;

					} else {
						start = (page - 1) * pageSize;
					}
					$http.get(
							"${ctx}/admin/bureaudirection/bureaudirectionList?"
									+ "start=" + start + "&limit=" + limit + "&bdId=" +bdId)
							.success(function(response) {
								$scope.bureaudirectionList = response.bureaudirectionList;
								$scope.totals = response.totals;
								console.log($scope.bureaudirectionList);
								pagging(1, parseInt($scope.totals), $scope);
							});
				};
				//分页点击
				$scope.pageClick = function(page, totals, totalPages) {			
					var pageSize = $("#page-limit").val();
					var bdId = $("#bdId").val();
					var start = 1, limit = pageSize;
					page = parseInt(page);
					if (page <= 1) {
						start = 0;
					} else {
						start = (page - 1) * pageSize;
					}
					$http.get(
							"${ctx}/admin/bureaudirection/bureaudirectionList?"
									+ "start=" + start + "&limit=" + pageSize + "&bdId=" +bdId)
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
								$scope.bureaudirectionList = response.bureaudirectionList;
								$scope.totals = response.totals;
								console.log($scope.bureaudirectionList);
							});

				};
			});
		};

		// 刷新数据
		refresh = function() {
			var appElement = document
					.querySelector('[ng-controller=bureaudirectionList]');
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