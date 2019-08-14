<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="/head.jsp"%>
<title>Insert title here</title>
</head>
<body>
	<form action="${baseUrl}/user/doLogin" method="post">
		<div>
			<input type="text" name="username" value="abc" />
		</div>
		<div>
			<input type="text" name="password" value="123" />
		</div>
		<div>
			<input type="checkbox" name="rememberMe" value="true" />Remember Me?
		</div>
		<div>
			<button type="submit">登录</button>
			<a href="${baseUrl}/user/logout">退出</a>
		</div>
	</form>
</body>
</html>