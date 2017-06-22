<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/layouts/taglibs.jsp" %>
<%@ include file="/WEB-INF/layouts/style.jsp" %>
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
		
		
		/* $(function(){
			$('#ff').form('load',{
				name:'myname',
				email:'mymail@gmail.com',
				subject:'subject',
				message:'message',
				language:'en'
			});
			
			
			
		}); */
		
	</script>
</head>
<body style="text-align: center;">
	    <form id="userForm" method="post"  action="${ctx}/user/update">
	    	<input type="hidden" name="id" value="${user.id}" />
	    	<input type="hidden" name="password" value="${user.password}" />
	    	<input type="hidden" name="version" value="${user.version}" />
	    	<input type="hidden" value="enableFlag" value="${user.enableFlag}" />
	    	<table  width="100%" border="0" cellspacing="0" cellpadding="0" class="add_tab" frame="border" >
	    		<tr>
	    			<th width="30%">用户名:</th>
	    			<td  width="70%"><input class="easyui-textbox" type="text" name="loginName" data-options="required:true" width="200px" value="${user.loginName}" > </input></td>
	    		</tr>
	    		<tr>
	    			<th>真实姓名:</th>
	    			<td><input class="easyui-textbox" type="text" name="realName" data-options="required:true"  width="200px" value="${user.realName}" ></input></td>
	    		</tr>
	    		<tr>
	    			<th>性别:</th>
	    			<td>
	    				<input class="easyui-radio" type="radio" name="sex" value="1" <c:if test="${user.sex==1}">checked</c:if> />男&nbsp;&nbsp;
	    				<input class="easyui-radio" type="radio" name="sex" value="0" <c:if test="${user.sex==0}">checked</c:if> />女
	    		   </td>
	    		</tr>
	    		<tr>
	    			<th>电话:</th>
	    			<td>
	    				<input class="easyui-textbox" type="text" name="telephone" data-options="required:true"  width="200px" value="${user.telephone}" ></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<th>邮箱:</th>
	    			<td>
	    				<input class="easyui-textbox" type="text" name="email" data-options="validType:'email'"  width="200px" value="${user.email}" ></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<th>地址:</th>
	    			<td>
	    				<input class="easyui-textbox" name="address" data-options="multiline:true" style="height:60px;width:200px" value="${user.address}" ></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<th>描述:</th>
	    			<td>
	    				<input class="easyui-textbox" name="description" data-options="multiline:true" style="height:60px;width:200px" value="${user.description}"></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<th>所属角色</th>
	    			<td>
          				<input id="roleids" name="roleIds" class="easyui-combobox" 
          			          data-options="url:'${ctx}/user/findComboboxVos?uid=${user.id}',editable:false,method:'get',valueField:'id',textField:'text',multiple:true,panelHeight:'auto'" style="width:200px;"/>
   					</td>
	    		</tr>
	    	</table>
	    </form>
 </body>
</html>