$(document).ready(function() {
		$.ajax({
			type : "post",
			url : baseUrl + "/index/init",
		    dataType : 'json',
			success : function(list) {
				addNav(list);
				InitLeftMenu();
			}
		});
	});


//移除现有的导航菜单
function Clearnav() {
	for ( var i = 0; i < menuTitle.length; i++) {
		var p = $('#leftMenuNav').accordion('getPanel', menuTitle[i]);
		
		if (p) {
			$('#leftMenuNav').accordion('remove', p.panel('options').title);
		}
	}
}


//添加导航信息
function addNav(data) {
	// 情况菜单数组
	$.each(data, function(i, sm) {
		var menulist = "";
		menulist += '<ul>';
		$.each(sm.subList, function(j, m) {		
			menulist += '<li><a href="#" icon="'+m.icon+' "rel="'+ baseUrl + '/' + m.resourcePath + '"  >'+ m.name +'</a></li>';
		});
		menulist += '</ul>';
		
		$('#leftMenuNav').accordion('add', {
			title : sm.name,
			content : menulist,
			iconCls : sm.icon
		});
		
	});
	
	
	//默认第一个面板选中，展开
	var pp = $('#leftMenuNav').accordion('panels');
	if (pp.length > 0) {
		var t = pp[0].panel('options').title;
		$('#leftMenuNav').accordion('select', t);
	}
	
}



function createFrame(subtitle, url) {
	var s = '<iframe scrolling="auto" name="' + subtitle
			+ '" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}


//绑定菜单的点击事件
function InitLeftMenu() {
	$('#leftMenuNav li a').bind('click', function() {
		//var tabTitle = $(this).children('.nav').text();
		var tabTitle=$(this).text();
		var url = $(this).attr("rel");
		var icon = $(this).attr("icon");
		addTab(tabTitle, url, icon);
		//$(this).parent().addClass("selected");
	});
}


function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {

		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(subtitle, url),
			closable : true,
			icon : icon
		});
		
		/** ********选项卡打开数量超过6个关闭除欢迎页的第一个*********** */
		var tabnum = 0;
		var firstTitle = "";
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if (i == 2) {
				firstTitle = t;
			}
			if (t != "") {
				tabnum += 1;
			}
		});
		if (tabnum > 6) {
			$('#tabs').tabs('close', firstTitle);
		}
		/** ********选项卡打开数量超过6个关闭除欢迎页的第一个*********** */
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}

}	
	
function createFrame(subtitle, url) {
	var s = '<iframe scrolling="auto" name="' + subtitle
			+ '" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}



















function doLogout() {
	$.messager.confirm('提示', '确定要退出系统吗?', function(confirmInfo) {
		if (confirmInfo) {
			window.top.location =baseUrl+"/logout";
			
		}
	});
}