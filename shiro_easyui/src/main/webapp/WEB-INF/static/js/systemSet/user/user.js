
$(function(){
	$('#resList').datagrid({
		toolbar:"#toolButton",
		striped: true,
		fit: true,
		pagination:true,
		url: baseUrl+"/user/list",
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
			{title:'用户名',field:"loginName",width:100,editor:false,align:"none"},
			{title:'真实名',field:"realName",width:100,editor:false,align:"none"},
			{title:'性别',field:"sex",width:50,editor:false,align:"none",
			formatter:function(value){
				        if(value=='0'){
				        	return '女';
				        }else if(value=='1'){
				        	return '男';
				        }
				        return '未知';
					}
			},
			{title:'电话',field:"telephone",width:100,editor:false,align:"none"},
			{title:'邮箱',field:"email",width:100,editor:false,align:"none"},
			{title:'地址',field:"address",width:200,align:"none",
				editor:{
					type:'text'
				}
			},
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
	
/*	
	 var p = $('#resList').datagrid('getPager'); 
     $(p).pagination({ 
         pageSize: 10,//每页显示的记录条数，默认为10
         pageList:[1,5,10,15,20],//每页显示几条记录
         beforePageText: '第',//页数文本框前显示的汉字
         afterPageText: '页    共 {pages} 页',
         displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
     });*/
	
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



