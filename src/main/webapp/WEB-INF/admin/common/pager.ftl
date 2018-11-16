<#-- 自定义的分页指令
前置条件：需要导入jQuery.js
参数说明：
   pageIndex   当前页号(int类型)
   pageSize    每页显示数量(int类型)
   recordCount 总数(int类型)
   toURL       点击分页跳转URL(String类型)
 使用方式：
  <#if recordCount??>
    <#import "common/pager.html" as page>
    <@page.pager pageIndex=pageIndex pageSize=pageSize recordCount=recordCount toURL="url"/>
  </#if>
-->
<#macro pager pageIndex pageSize recordCount toURL>  
  <#-- 定义局部变量pageCount保存总页数 -->
  <#assign pageCount=((recordCount + pageSize - 1) / pageSize)?int>  
  <#if recordCount==0><#return/></#if>
	<#-- 输出分页样式 -->
	<style type="text/css">
		.pagination {padding: 5px;float:right;font-size:12px;}
		.pagination a, .pagination a:link, .pagination a:visited {padding:2px 5px;margin:2px;border:1px solid #aaaadd;text-decoration:none;color:#006699;}
		.pagination a:hover, .pagination a:active {border: 1px solid #ff0000;color: #000;text-decoration: none;}
		.pagination span.current {padding: 2px 5px;margin: 2px;border: 1px solid #ff0000;font-weight: bold;background-color: #ff0000;color: #FFF;}
		.pagination span.disabled {padding: 2px 5px;margin: 2px;border: 1px solid #eee; color: #ddd;}
	</style>
	<#-- 控制JS代码 -->
	<script type="text/javascript">
	  function toPage(index){
	    if(index > ${pageCount}){
	    	index = ${pageCount};
	    }
	    if(index < 1){
	    	index = 1;
	    }
	    $("#pageIndex").val(index);
	    $("#pagerForm").action="${toURL}";
	    $("#pagerForm").submit();
	  }
	</script>
<#-- 页号越界处理 -->
<#if (pageIndex > pageCount)>
    <#assign pageIndex=pageCount>
</#if>
<#if (pageIndex lt 1)>
    <#assign pageIndex=1>
</#if>
<#-- 输出分页表单 -->
<div class="pagination">
<form id="pagerForm" method="POST">
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/>
<#-- 上一页处理 -->
<#if (pageIndex == 1)>
<span class="disabled">&laquo;&nbsp;上一页</span>
<#else>
<a href="javascript:void(0);" onclick="toPage(${pageIndex - 1})">&laquo;&nbsp;上一页</a>
</#if>
<#-- 如果前面页数过多,显示... -->
<#assign start=1>
<#if (pageIndex > 4)>
	<#assign start=(pageIndex - 1)>
	<a href="javascript:void(0);" onclick="toPage(1)">1</a>
	<a href="javascript:void(0);" onclick="toPage(2)">2</a>&hellip;
</#if>
<#-- 显示当前页号和它附近的页号 -->
<#assign end=(pageIndex + 1)>
<#if (end > pageCount)>
	<#assign end=pageCount>
</#if>
<#list start..end as i>
<#if (pageIndex == i)>
	<span class="current">${i}</span>
<#else>
 	<a href="javascript:void(0);" onclick="toPage(${i})">${i}</a>      
</#if>
</#list>
<#-- 如果后面页数过多,显示... -->
<#if (end lt pageCount - 2)>
&hellip;
</#if>
<#if (end lt pageCount - 1)>
	<a href="javascript:void(0);" onclick="toPage(${pageCount - 1})">${pageCount-1}</a>
</#if>
<#if (end lt pageCount)>
	<a href="javascript:void(0);" onclick="toPage(${pageCount})">${pageCount}</a>
</#if>
<#-- 下一页处理 -->
<#if (pageIndex == pageCount)>
	<span class="disabled">下一页&nbsp;&raquo;</span>
<#else>
	<a href="javascript:void(0);" onclick="toPage(${pageIndex + 1})">下一页&nbsp;&raquo;</a>
</#if>
</form>
</div> 
</#macro>  