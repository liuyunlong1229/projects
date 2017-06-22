<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/layouts/taglibs.jsp" %>
<%@ include file="/WEB-INF/layouts/style.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<title>首页</title>

<script type="text/javascript" src="${ctx}/static/js/index.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/index.css"></script>

<script>
$(document).ready(function() {
	/* 
	var html="<ul><li>用户管理</li><li>角色管理</li><li>菜单管理</li></ul>";

	
	//新增一个面板
	$('#leftMenuNav').accordion('add', {
		title : '新增的菜单',
		content : html,
		iconCls : 'icon-help'
	});
	
	//根据名称删除一个面板
	$('#leftMenuNav').accordion('remove','系统管理1');
	 */ 
	
	
});
	
</script>



</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:80px;background:#B3DFDA;">
		<div style="float: left;">【<shiro:principal property="realName"/>】欢迎您登陆本系统！</div>
		<div style="float:right;">点击<a href="javascript:doLogout()">【退出】</a>系统</div>
	</div>
	<div data-options="region:'west',split:true,title:'功能导航'" style="width:150px;">
		<div id='leftMenuNav' class="easyui-accordion" data-options="fit:true,border:false"></div>
	</div>
	<div data-options="region:'south',border:false" style="text-align:center;height:50px;background:#B3DFDA;">
			>>>>>>>>>>>>>>>>>>>>这用于声明版权的相关信息>>>>>>>>>>>>>>>>>>>>
	</div>
	<div data-options="region:'center',title:'Center'">
		<div id="tabs" class="easyui-tabs"  fit="true" border="false" >  
	</div>
</body>
</html>