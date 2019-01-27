<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
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
			<button type="submit">登录</button>
			<a href="${baseUrl}/testShiro/logout">退出</a>
		</div>
	</form>
	<div>
		<label>testView</label> <input type="button" name="testButton1"
			value="/testShiro/doView" />
	</div>
	<div>
		<label>testUpdate</label> <input type="button" name="testButton2"
			value="/testShiro/doUpdate" />
	</div>
	<div>
		<label>testInsert</label> <input type="button" name="testButton3"
			value="/testShiro/doInsert" />
	</div>
	<div>
		<label>testDelete</label> <input type="button" name="testButton4"
			value="/testShiro/doDelete" />
	</div>

	<script>
		$(document).ready(function() {
			$.each($('input[name^=testButton]'), function(index, obj) {
				$(obj).on('click', function() {
					if ($(this).attr('name') === 'testButton1') {
						$.get('${baseUrl}' + $(this).val(), function(result) {
							console.log(result);
						});
					} else {
						$.post('${baseUrl}' + $(this).val(), function(result) {
							console.log(result);
						});
					}
				});
			});
		});
	</script>
</body>
</html>