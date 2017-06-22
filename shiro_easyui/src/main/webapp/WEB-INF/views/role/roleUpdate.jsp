<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/layouts/taglibs.jsp" %>
<%@ include file="/WEB-INF/layouts/style.jsp" %>

<html>
<head>
<script>	
		 $(function(){ //页面加载完毕后触发事件
			   $('#tt').tree({
			    checkbox: true,  
			    lines:true,
			    animate:true,
			    cascadeCheck:false,
			    url: '${ctx}/role/findResourceTree?roleId=${role.id}',
			    onClick:function(node){ //点击文件节点触发事件
			    	  $(this).tree('toggle', node.target);
			    	  var ss=node.attributes.cas.toString();//p1
			    	  alert('you click '+ss);
			   	 },
			   
			    onCheck: function(node,checked){ //选择复选框触发事件

				    if(checked){ //当选中时逐级将父节点选中
						 var parent = $("#tt").tree('getParent', node.target);
						 if(parent){
							 $("#tt").tree('check', parent.target);
						 }
					
					 }else{  //当取消时逐级将子节点取消
						
						if($("#tt").tree('isLeaf', node.target)){ //import判断是否是叶子节点，否则getChildren会报错。
							return;
						}
						 var children  = $("#tt").tree('getChildren', node.target); 
								
					     if(children && children.length>0){
						 
						    for(var i=0;i<children.length;i++){
								  $("#tt").tree('uncheck',children[i].target);
							}	
						 }
					}					
		    }
			    
	     });
	});
		 
	
		 

	function getCheckedNodes(){
		var nodes = $('#tt').tree('getChecked');
		var ids = '';
		for(var i=0; i<nodes.length; i++){
			if (ids != '') ids += ',';
			ids += nodes[i].id;
		}
		return ids;
	}
	
	 function submitForm(){
			var flag = true;
			var result = false;
			var selectedIds=getCheckedNodes();
			if(selectedIds==''){
				parent.$.messager.alert('警告','请选择至少一个资源！','error');
				return ;
				
			};
	
			$("#resourceIds").val(selectedIds);
			flag = $("#roleForm").form('validate');
			if (flag) {
				var param = $("#roleForm").serialize();
				var url = $("#roleForm").attr("action");
				$.ajax({
					type : "POST",
					url : url,
					data : param,
					dataType : "json",
					async : false,
					success:function(returnMsg){
						result=returnMsg;
					},
					error : function(XHR, textStatus, errorThrown) {
						result=false;
					}
				});
			}
			return result;
		}
		
		function clearForm(){
			$('#roleForm').form('clear');
		}
		

</script>
</head>
<body class="easyui-layout">
	<div style="float: left;width:30%">
		<div id="tt" class="easyui-tree" ></div>
	</div>
	<div style="float: right;width:70%"  >
	    
 	   <form id="roleForm" method="post"  action="${ctx}/role/update" >
				<input type="hidden" name="resourceIds"  id="resourceIds"  />
				<input type="hidden" name="id" value="${role.id}" />
		    	<input type="hidden" name="version" value="${role.version}" />
		    	<input type="hidden" value="enableFlag" value="${role.enableFlag}" />
				<table border="0" cellspacing="0" cellpadding="0" class="add_tab" frame="border" style="width: 100%">
					<tr>
						<th width="30%">角色名：</th>
						<td width="70%">
							<input class="easyui-textbox" type="text"  name="name" value="${role.name}"  data-options="required:true" width="200px"> </input>
						</td>
					</tr>
					<tr>
						<th>角色标签：</th>
						<td>
							<input class="easyui-textbox" type="text" name="label"  value="${role.label}" data-options="required:true" width="200px"> </input>
						</td>
					</tr>
					<tr>
						<th>描述:
						</th>
						<td>
							<input class="easyui-textbox" name="description"  value="${role.description}"  data-options="multiline:true" style="height: 60px; width: 200px"></input>
						</td>
					</tr>
				</table>
			</form>
 	
	</div>
</body>
</html>