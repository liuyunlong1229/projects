
$(function(){
	$('#resList').datagrid({
		toolbar:"#toolButton",
		striped: true,
		fit: true,
		pagination:true,
		url: baseUrl+"/role/list",
		idField:'id',
		singleSelect:false,
//		pageNumber:1,
//		pageSize:10,   
		fitColumns:true,
		nowrap:true,
		loadMsg:'数据加载中,请稍候……',
		frozenColumns:[[
		{field:'ck',checkbox:true},
		{title:'主键ID',field:'id',width:60,align:'center',sortable:true,hidden:true}     
		]],
		columns:[[
			{title:'角色名',field:"name",width:100,editor:false,align:"none"},
			{title:'角色标签',field:"label",width:100,editor:false,align:"none"},
			{title:'描述',field:"description",width:200,editor:false,align:"none"}
			
		]],
			
		pagination:true,
		rownumbers:true,
		
		onBeforeEdit:function(index,row){
		row.editing = true;
		
		},
		onDblClickRow:function(index,row){
		    $('#resList').datagrid('beginEdit', index);
		},
		onClickRow:function(index,row){
		    $('#resList').datagrid('endEdit', index);
		}
	});
	
}); 



function resetPassword(dataListId,url){
	
	
	var rows = $("#"+dataListId).datagrid("getSelections");
	if(rows.length == 0){
		parent.$.messager.alert('提示','请选择你要删除的记录！');
		return;
	}
	var ids = "";
	for(var i=0;i<rows.length;i++){
        ids =ids + rows[i].id+",";
	}
	ids = ids.substr(0,ids.length - 1);
	parent.$.messager.confirm('提示', '你确定要重置所选用户的密码?', function(confirmInfo){
		if (confirmInfo){
			$.post(url, {ids:ids}, function (msg) {
				if(msg == 'success') {
					$.dialog.tips('批量重置成功！');
				}else{
					$.dialog.tips('操作失败！');
					return;
				}
			});
		}
	});
	
	
}



