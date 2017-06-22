<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/layouts/taglibs.jsp" %>
<%@ include file="/WEB-INF/layouts/style.jsp" %>
<%@taglib prefix="liuyl" uri="http://lyl.com/liuyunlong/tags/liuyl/functions"%>
<html>
<head>
<title></title>
<script type="text/javascript" src="${ctx}/static/js/systemSet/resource/resource.js"></script>


<script type="text/javascript">
function submitForm(){
	var flag = true;
	var result = false;
	flag = $("#resourceForm").form('validate');
	if (flag) {
		var param = $("#resourceForm").serialize();
		var url = $("#resourceForm").attr("action");
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
	$('#resourceForm').form('clear');
}


</script>

</head>
<body>

<body style="text-align: center;">
	    <form id="resourceForm" method="post"  action="${ctx}/resource/update">
	    	<!-- 此字段一定要 根据情况进行设置，否则提交的时候，没有id的得空对象还是会报错的-->
	        <c:if test="${not empty resource.parent.id}">
		        <input type="hidden"  name="parent.id" value="${resource.parent.id}"/>
	        </c:if>
        
	    	<input type="hidden" name="id" value="${resource.id}" />
	    	<input type="hidden" name="version" value="${resource.version}" />
	    	<input type="hidden" value="enableFlag" value="${resource.enableFlag}" />
	    	<table  width="100%" border="0" cellspacing="0" cellpadding="0" class="add_tab" frame="border" >
	    		<tr>
	    			<th width="30%">名称:</td>
	    			<td  width="70%"><input class="easyui-textbox" type="text" name="name" data-options="required:true" width="200px" value="${resource.name}" > </input></td>
	    		</tr>
	    		
	    		<tr>
	    			<th>标签:</th>
	    			<td>
	    				<input class="easyui-textbox" type="text" name="label" data-options="required:true"  width="200px" value="${resource.label}" ></input>
	    			</td>
	    		</tr>
	    		
	    		<tr>
	    			<th>路径:</th>
	    			<td><input class="easyui-textbox" type="text" name="resourcePath" data-options="required:true"  width="200px" value="${resource.resourcePath}" ></input></td>
	    		</tr>
	    		<tr>
	    			<th>类型:</th>
	    			<td>
	    				${resource.resourceType.text}
	    		   </td>
	    		</tr>
	    		
	    		<tr>
					<th>描述:</th>
					<td>
						<input class="easyui-textbox" name="description"  value="${resource.description}"  data-options="multiline:true" style="height: 60px; width: 200px"></input>
					</td>
				</tr>
	    		
	    	</table>
	    </form>
 </body>
</html>