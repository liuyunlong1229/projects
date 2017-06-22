/**
 * 全局Ajax操作默认设置处理，beforeSend、error、success，如有其它请在此处添加
 */
$.ajaxSetup({
	global : true,
	cache : false
});

function doReload(url) {
	window.location = url;
}

/**
 * 通用检索条件传递，只支持文本框、下拉框的传递
 * 
 * @param {Object}
 *            dataListId 列表ID
 */
function doSearch(dataListId) {

	var params = $("#searchForm").serializeArray();
	var array = {};
	// json封装
	$.each(params, function() {
		if (array[this.name]) {
			if (!array[this.name].push) {
				array[this.name] = [ array[this.name] ];
			}
			array[this.name].push(this.value || '');
		} else {
			array[this.name] = this.value || '';
		}
	});
	$('#' + dataListId).datagrid("options").queryParams = array;
	$('#' + dataListId).datagrid("load");
	$('#' + dataListId).datagrid("clearSelections");

}

/**
 * 添加数据，刷新列表
 * 
 * @param {Object}
 *            id 数据表格Id
 * @param {Object}
 *            title 弹出框标题
 * @param {Object}
 *            url 调用url
 * @param {Object}
 *            width 宽度
 * @param {Object}
 *            height 高度
 * @param {Object}
 *            isTreeAdd 是否在树上添加节点
 */
function addDataInfoRefresh(id, title, url, winWidth, winHeight, isTreeAdd,
		isHasTreeId, isTreeRefresh) {
	if (isTreeAdd) {
		url = url + "?parentId=" + $("#parentId").val();
	}
	var d = null;
	d = $.dialog({
		id : id,
		title : title,
		autoPos : {
			left : 'center',
			top : 'center'
		},
		width : winWidth,
		height : winHeight,
		lock : true,
		content : 'url:' + url,

		button : [ {
			name : '保存',
			callback : function() {
				var iframe = this.iframe.contentWindow;
				var validFlag = iframe.submitForm();// 调用子页面函数
				if (validFlag) {
					$.dialog.tips('操作成功!');
					doSearch(id, isHasTreeId, isTreeRefresh);
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

/**
 * 修改数据，刷新列表
 * 
 * @param {Object}
 *            id 数据表格Id
 * @param {Object}
 *            title 弹出框标题
 * @param {Object}
 *            url 调用url
 * @param {Object}
 *            width 宽度
 * @param {Object}
 *            height 高度
 */
function updateDataInfoRefresh(id, title, url, winWidth, winHeight,
		isHasTreeId, isTreeRefresh) {
	var rows = $("#" + id).datagrid("getSelections");
	if (rows.length == 0) {
		parent.$.messager.alert('提示', '请选择你要修改的记录！');
		return;
	} else if (rows.length > 1) {
		parent.$.messager.alert('警告', '只能选择一条记录！', 'error');
		return;
	}
	var selectDataId = rows[0].id;
	var d = null;
	d = $.dialog({
		id : id,
		autoPos : {
			left : 'center',
			top : 'center'
		},
		title : title,
		width : winWidth,
		height : winHeight,
		lock : true,
		content : 'url:' + url + '?id=' + selectDataId,
		button : [ {
			name : '保存',
			callback : function() {
				var iframe = this.iframe.contentWindow;
				var validFlag = iframe.submitForm();// 调用子页面函数
				if (validFlag) {
					$.dialog.tips('操作成功!');
					doSearch(id, isHasTreeId, isTreeRefresh);
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

/**
 * 删除数据
 * 
 * @param {Object}
 *            dataListId 数据表格ID
 * @param {Object}
 *            url 删除请求URL
 * @return {TypeName}
 */
function delDataInfoRefresh(dataListId, url, isHasTreeId, isTreeRefresh) {
	var rows = $("#" + dataListId).datagrid("getSelections");
	if (rows.length == 0) {
		parent.$.messager.alert('提示', '请选择你要删除的记录！');
		return;
	}
	var ids = "";
	for ( var i = 0; i < rows.length; i++) {
		ids = ids + rows[i].id + ",";
	}
	ids = ids.substr(0, ids.length - 1);
	parent.$.messager.confirm('提示', '你确定要删除吗?', function(confirmInfo) {
		if (confirmInfo) {
			$.post(url, {
				ids : ids
			}, function(msg) {
				if (msg == 'success') {
					$.dialog.tips('删除成功！');
					doSearch(dataListId, isHasTreeId, isTreeRefresh);
				} else {
					$.dialog.tips('操作失败！');
					return;
				}
			});
		}
	});
}

/**
 * 单条删除数据，通过传入联合主键的属性列名
 * 
 * @param {Object}
 *            dataListId 数据表格ID
 * @param {Object}
 *            url 删除请求URL
 * @param {Object}
 *            fieldNames 列属性名,多个逗号分割
 * @return {TypeName}
 */
function delDataInfoByFieldRefresh(dataListId, url, fieldNames, isHasTreeId,
		isTreeRefresh) {
	var rows = $("#" + dataListId).datagrid("getSelections");
	if (rows.length == 0) {
		parent.$.messager.alert('提示', '请选择你要删除的记录！');
		return;
	}
	var fieldNameArray = fieldNames.split(",");
	var paramJson = "{";
	for ( var j = 0; j < fieldNameArray.length; j++) {
		var fieldValue = "";
		$.each(rows[0], function(key, value) {
			if (fieldNameArray[j] == key) {
				fieldValue = value;
			}
		});
		paramJson += "\"" + fieldNameArray[j] + "\":\"" + fieldValue + "\",";
	}
	paramJson = paramJson.substring(0, paramJson.length - 1);
	paramJson += "}";
	parent.$.messager.confirm('提示', '你确定要删除吗?', function(confirmInfo) {
		if (confirmInfo) {
			$.post(url, $.parseJSON(paramJson), function(msg) {
				if (msg == 'success') {
					$.dialog.tips('删除成功！');
					doSearch(dataListId, isHasTreeId, isTreeRefresh);
				} else {
					$.dialog.tips('操作失败！');
					return;
				}
			});
		}
	});
}
/**
 * 修改数据，通过传入联合主键的属性列名
 * 
 * @param {Object}
 *            id 数据表格Id
 * @param {Object}
 *            title 弹出框标题
 * @param {Object}
 *            url 调用url
 * @param {Object}
 *            fieldNames 列属性名,多个逗号分割
 * @param {Object}
 *            width 宽度
 * @param {Object}
 *            height 高度
 */
function updateDataInfoByFieldRefresh(id, title, url, fieldNames, winWidth,
		winHeight, isHasTreeId, isTreeRefresh) {
	var rows = $("#" + id).datagrid("getSelections");
	if (rows.length == 0) {
		parent.$.messager.alert('提示', '请选择你要修改的记录！');
		return;
	} else if (rows.length > 1) {
		parent.$.messager.alert('警告', '只能选择一条记录！', 'error');
		return;
	}
	var selectDataId = rows[0].id;
	var fieldNameArray = fieldNames.split(",");
	var paramJson = "";
	for ( var j = 0; j < fieldNameArray.length; j++) {
		var fieldValue = "";
		$.each(rows[0], function(key, value) {
			if (fieldNameArray[j] == key) {
				fieldValue = value;
			}
		});
		paramJson += "&" + fieldNameArray[j] + "=" + fieldValue;
	}
	var d = $.dialog({
		id : id,
		autoPos : {
			left : 'center',
			top : 'center'
		},
		title : title,
		width : winWidth,
		height : winHeight,
		lock : true,
		content : 'url:' + url + '?id=' + selectDataId
				+ encodeURI(encodeURI(paramJson)),
		ok : function() {
			var iframe = this.iframe.contentWindow;
			var validFlag = iframe.doSave();// 调用子页面函数
			if (validFlag) {
				$.dialog.tips('操作成功!');
				doSearch(id, isHasTreeId, isTreeRefresh);
				d.close();
			} else {
				$.dialog.tips('操作失败!');
			}
			return false;
		},
		cancelVal : '关闭',
		cancel : true
	});
}