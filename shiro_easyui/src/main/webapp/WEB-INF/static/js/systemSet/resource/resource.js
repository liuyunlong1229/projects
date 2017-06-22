$(function() {

	$("#tg").treegrid({

		title : '系统资源',
		iconCls : 'icon-ok',
		width : 700,
		height : 400,
		rownumbers : true,
		animate : true,
		collapsible : true,
		fitColumns : true,
		url : baseUrl + "/resource/list.do",
		idField : 'id',
		treeField : 'name',
		onContextMenu : onContextMenu,
		showFooter : true,

		columns : [ [ {
			title : '名称',
			field : "name",
			width : 100,
			editor : false,
			align : "none"
		}, {
			title : '类型',
			field : "resourceType",
			width : 100,
			editor : false,
			align : "none"
		}, {
			title : '资源路径',
			field : "resourcePath",
			width : 50,
			editor : false,
			align : "none"
		}, {
			title : '权限操作符',
			field : "label",
			width : 100,
			editor : false,
			align : "none"
		}

		] ]

	});

});

// 根据条件显示不同的右键菜单
function onContextMenu(e, row) {
	e.preventDefault();
	var menuid = "";
	if (row.resourceType == '根节点') {
		menuid = "root_menu";
	} else {
		if (row.resourceType == '按钮') {
			menuid = "menu_button";
		} else {
			if (row.isLeaf == true) {
				menuid = "menu_nochild";
			} else {
				menuid = "menu_child";
			}
		}
	}
	$(this).treegrid('select', row.id);

	$('#' + menuid).menu('show', {
		left : e.pageX,
		top : e.pageY
	});
}

function refreshTreeGrid() {

	$("#tg").treegrid("reload");

}

function updateMenu() {

	var node = $('#tg').treegrid('getSelected'); // 获得选中的节点
	var selectDataId = node.id;
	var d = null;
	d = $.dialog({
		id : selectDataId,
		autoPos : {
			left : 'center',
			top : 'center'
		},
		title : '资源查看/修改',
		width : 600,
		height : 300,
		lock : true,
		content : 'url:' + baseUrl + '/resource/update?rsid=' + selectDataId,
		button : [ {
			name : '保存',
			callback : function() {
				var iframe = this.iframe.contentWindow;
				var validFlag = iframe.submitForm();// 调用子页面函数
				if (validFlag) {
					$.dialog.tips('操作成功!');
					refreshTreeGrid();
					d.close();
				} else {
					$.dialog.tips('操作失败!');
				}
				return false;
			},
			focus : true
		// 被focus的按钮，会显示深色

		},

		{
			name : '重置',
			callback : function() {
				var iframe = this.iframe.contentWindow;
				iframe.clearForm();
				return false;
			}
		},

		{
			name : '关闭',
			callback : function() {
				d.close();
			}
		}

		]
	});
}

function appendMenu() {
	var node = $('#tg').treegrid('getSelected');
	var selectDataId = node.id;
	var d = null;
	d = $.dialog({
		id : selectDataId,
		autoPos : {
			left : 'center',
			top : 'center'
		},
		title : '资源新增',
		width : 600,
		height : 300,
		lock : true,
		content : 'url:' + baseUrl + '/resource/add?rsid=' + selectDataId,
		button : [ {
			name : '保存',
			callback : function() {
				var iframe = this.iframe.contentWindow;
				var validFlag = iframe.submitForm();// 调用子页面函数
				if (validFlag) {
					$.dialog.tips('操作成功!');
					refreshTreeGrid();
					d.close();
				} else {
					$.dialog.tips('操作失败!');
				}
				return false;
			},
			focus : true
		// 被focus的按钮，会显示深色

		},

		{
			name : '重置',
			callback : function() {
				var iframe = this.iframe.contentWindow;
				iframe.clearForm();
				return false;
			}
		},

		{
			name : '关闭',
			callback : function() {
				d.close();
			}
		}

		]
	});
}

function removeMenu() {
	var node = $('#tg').treegrid('getSelected');
	var url = baseUrl + '/resource/delete';
	$.messager.confirm('提示', '你确定要删除吗?', function(confirmInfo) {
		if (confirmInfo) {
			$.post(url, {
				rsid : node.id
			}, function(msg) {
				if (msg == 'success') {
					$.dialog.tips('删除成功！');
					refreshTreeGrid();
				} else {
					$.dialog.tips('操作失败！');
					return;
				}
			});

		}
	});

}
function collapse() {
	var node = $('#tg').treegrid('getSelected');
	if (node) {
		$('#tg').treegrid('collapse', node.id);
	}
}
function expand() {
	var node = $('#tg').treegrid('getSelected');
	if (node) {
		$('#tg').treegrid('expand', node.id);
	}
}