<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/layouts/taglibs.jsp" %>
<%@ include file="/WEB-INF/layouts/style.jsp" %>
<%@taglib prefix="liuyl" uri="http://lyl.com/liuyunlong/tags/liuyl/functions"%>
<html>
<head>
<title></title>
<script type="text/javascript" src="${ctx}/static/js/systemSet/role/role.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true" >
	
	<body class="easyui-layout" data-options="fit:true" >
	 <div data-options="region:'north',split:true,title:'查询条件'"  style="height:150px;padding:5px; border-width: 0 0 1px 0;">
		<form id="searchForm"  action="" >
			<table   style="width:100%;" border="0" cellspacing="5" cellpadding="0" >
				<tr>
					<th>角色名：</th>
					<td><input class="easyui-textbox" type="text" name="name"  style="width:200px" /></td>
					<th>角色标签：</th>
					<td><input class="easyui-textbox" type="text" name="label"  style="width:200px" /></td>
				</tr>
				
				<tr>
					<td colspan="4" align="center">
						<a href="#" class="easyui-linkbutton" iconCls="icon-search"   onclick="doSearch('resList')" style="width:100px">查询</a>
						<a href="#" class="easyui-linkbutton" iconCls="icon-reload" style="width:100px">重置</a>
					</td>
				</tr>
			
			</table>
		</form>
	
	</div>
	
	<div data-options="region:'center',title:'查询结果'" >
	 	<table id="resList"></table>
    </div>
	
	
	<div id="toolButton" >
		<shiro:hasPermission name="role:add">
		   <a href="#" id="addbtn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addDataInfoRefresh('resList','角色添加','${ctx}/role/add',$(document).width()*0.5,$(document).height()*0.7,false,false,false)">新增</a>
		</shiro:hasPermission>
			
		<shiro:hasPermission name="role:edit">	
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="updateDataInfoRefresh('resList','角色修改','${ctx}/role/update',$(document).width()*0.5,$(document).height()*0.7,false,false)">修改/查看</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="role:delete">
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick='delDataInfoRefresh("resList","${ctx}/role/delete",false,false)'>删除</a>
		</shiro:hasPermission>
	</div>
</body>
</html>