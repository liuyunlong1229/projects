<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/layouts/taglibs.jsp" %>
<%@ include file="/WEB-INF/layouts/style.jsp" %>
<%@taglib prefix="liuyl" uri="http://lyl.com/liuyunlong/tags/liuyl/functions"%>
<html>
<head>
<title></title>
<script type="text/javascript" src="${ctx}/static/js/systemSet/user/user.js"></script>

<script type="text/javascript">
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true" >

	 <div data-options="region:'north',split:true,title:'查询条件'"  style="height:150px;padding:5px; border-width: 0 0 1px 0;">
		<form id="searchForm"  action="" >
		<table style="width:100%;" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<th>用户名：</th>
				<td><input class="easyui-textbox" type="text" name="loginName"  style="width:200px" /></td>
				<th>角色：</th>
				<td>
				<select class="easyui-combobox" panelHeight="auto" style="width:200px" name="sex">
					<option value="">-请选择-</option>
					<option value="1">男</option>
					<option value="0">女</option>
				</select>
				</td>
			</tr>
			
			<tr>
				<th>邮箱：</th>
				<td><input class="easyui-textbox" type="text" name="email"  style="width:200px" /></td>
				<th>性别：</th>
				<td>
				<select class="easyui-combobox" panelHeight="auto" style="width:200px">
					<option value="">--请选择--</option>
					<option value="1">男</option>
					<option value="0">女</option>
				</select>
				
				</td>
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
		<shiro:hasPermission name="user:add">
		   <a href="#" id="addbtn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addDataInfoRefresh('resList','用户添加','${ctx}/user/add',$(document).width()*0.5,$(document).height()*0.7,false,false,false)">新增</a>
		</shiro:hasPermission>
			
		<shiro:hasPermission name="user:edit">	
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="updateDataInfoRefresh('resList','用户修改','${ctx}/user/update',$(document).width()*0.5,$(document).height()*0.7,false,false)">修改/查看</a>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="user:delete">
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick='delDataInfoRefresh("resList","${ctx}/user/delete",false,false)'>删除</a>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="user:add">
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick='resetPassword("resList","${ctx}/user/resetPwd")'>重置密码</a>
		</shiro:hasPermission>
	</div>
</body>
</html>