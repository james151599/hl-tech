<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${baseUrl}/testShiro/login" method="post">
		<div>
			<input type="text" name="username" value="abc" />
		</div>
		<div>
			<input type="text" name="password" value="123" />
		</div>
		<div>
			<button type="submit">提交</button>
		</div>
	</form>
</body>
</html>