<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>

<title>用户登陆</title>
</head>
<body>
	<div align="center" >
	 ${error}
	 <form  method="post">
		用户名:<input type="text" name="username" value="admin" /><br/>
		密码:<input type="password" name="password" value="admin" /><br/>
		<input type="checkbox" name="rememberMe" value="true"  checked="checked"/>
		<input type="submit" value="登陆">
	</form>
	</div> 
</body>
</html>