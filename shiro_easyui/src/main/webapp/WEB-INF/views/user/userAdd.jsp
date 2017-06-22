<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/layouts/taglibs.jsp" %>
<%@ include file="/WEB-INF/layouts/style.jsp" %>
<%@taglib prefix="liuyl" uri="http://lyl.com/liuyunlong/tags/liuyl/functions"%>
<html>
<head>
	<script type="text/javascript">
		function submitForm(){
			var flag = true;
			var result = false;
			flag = $("#userForm").form('validate');
			if (flag) {
				var param = $("#userForm").serialize();
				var url = $("#userForm").attr("action");
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
			$('#userForm').form('clear');
		}
	</script>
</head>
<body style="text-align: center;">
	    <form id="userForm" method="post"  action="${ctx}/user/add">
	    	<table  width="100%" border="0" cellspacing="0" cellpadding="0" class="add_tab" frame="border" >
	    		<tr>
	    			<th width="30%">用户名:</th>
	    			<td  width="70%"><input class="easyui-textbox" type="text" name="loginName" data-options="required:true" width="200px"> </input></td>
	    		</tr>
	    		<tr>
	    			<th>密码:</th>
	    			<td><input class="easyui-textbox" type="text" name="password" data-options="required:true"  width="200px" ></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<th>密码确认:</th>
	    			<td><input class="easyui-textbox" type="text" name="rePassword" data-options="required:true"  width="200px" ></input></td>
	    		</tr>
	    		<tr>
	    			<th>真实姓名:</th>
	    			<td><input class="easyui-textbox" type="text" name="realName" data-options="required:true"  width="200px"></input></td>
	    		</tr>
	    		<tr>
	    			<th>性别:</th>
	    			<td>
	    				<input class="easyui-radio" type="radio" name="sex" value="1" checked="checked"/>男&nbsp;&nbsp;
	    				<input class="easyui-radio" type="radio" name="sex" value="0"/>女
	    		   </td>
	    		</tr>
	    		<tr>
	    			<th>电话:</th>
	    			<td>
	    				<input class="easyui-textbox" type="text" name="telephone" data-options="required:true"  width="200px"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<th>邮箱:</th>
	    			<td>
	    				<input class="easyui-textbox" type="text" name="email" data-options="validType:'email'"  width="200px"></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<th>地址:</th>
	    			<td>
	    				<input class="easyui-textbox" name="address" data-options="multiline:true" style="height:60px;width:200px"></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<th>描述:</th>
	    			<td>
	    				<input class="easyui-textbox" name="description" data-options="multiline:true" style="height:60px;width:200px"></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<th>所属角色</th>
	    			<td>
          				<input id="roleids" name="roleIds" class="easyui-combobox" 
          			          data-options="url:'${ctx}/user/findComboboxVos?uid=-1',editable:false,method:'get',valueField:'id',textField:'text',multiple:true,panelHeight:'auto'" style="width:200px;"/>
   					</td>
	    		</tr>
	    	</table>
	    </form>
 </body>
</html>