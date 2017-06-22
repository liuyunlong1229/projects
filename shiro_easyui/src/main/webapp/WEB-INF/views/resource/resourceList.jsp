<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/layouts/taglibs.jsp" %>
<%@ include file="/WEB-INF/layouts/style.jsp" %>
<%@taglib prefix="liuyl" uri="http://lyl.com/liuyunlong/tags/liuyl/functions"%>
<html>
<head>
<title></title>
<script type="text/javascript" src="${ctx}/static/js/systemSet/resource/resource.js"></script>

</head>
<body>

<table id="tg" ></table>


<!-- 根节点菜单 -->
<div id="root_menu" class="easyui-menu" style="width:120px;">
		<div onclick="appendMenu()" data-options="iconCls:'icon-add'">添加子节点</div>
		<div class="menu-sep"></div>
		<div onclick="collapse()">收缩</div>
		<div onclick="expand()">展开</div>
</div>


<!-- 没有子节点菜单 -->	
<div id="menu_nochild" class="easyui-menu" style="width:120px;">
		<div onclick="updateMenu()" data-options="iconCls:'icon-edit'">修改/查看</div>
		<div onclick="appendMenu()" data-options="iconCls:'icon-add'">添加子节点</div>
		<div onclick="removeMenu()" data-options="iconCls:'icon-remove'">删除该节点</div>
</div>

<!-- 有子节点菜单 -->	
<div id="menu_child" class="easyui-menu" style="width:120px;">
		<div onclick="updateMenu()" data-options="iconCls:'icon-edit'">修改/查看</div>
		<div onclick="appendMenu()" data-options="iconCls:'icon-add'">添加子节点</div>
		<div class="menu-sep"></div>
		<div onclick="collapse()">收缩</div>
		<div onclick="expand()">展开</div>
</div>

<!-- 按钮菜单 -->	
<div id="menu_button" class="easyui-menu" style="width:120px;">
<div onclick="updateMenu()" data-options="iconCls:'icon-edit'">修改/查看</div>
<div onclick="removeMenu()" data-options="iconCls:'icon-remove'">删除该节点</div>
</div>

</body>
</html>