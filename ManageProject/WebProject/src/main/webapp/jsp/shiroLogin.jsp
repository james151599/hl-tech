<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<title>Insert title here</title>
<script src="${baseUrl}/js/jquery-3.3.1.min.js" />
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
	<div>
		<input type="button" name="testButton1" value="/testShiro/doView">testView</input>
	</div>
	<div>
		<input type="button" name="testButton2" value="/testShiro/doUpdate">testUpdate</input>
	</div>
	<div>
		<input type="button" name="testButton3" value="/testShiro/doInsert">testInsert</input>
	</div>
	<div>
		<input type="button" name="testButton4" value="/testShiro/doDelete">testDelete</input>
	</div>

	<script>
		$(document).ready(function() {
		    $('input[name^=testButton]').on('click', function() {
		    	$.post(${baseUrl} + (this).val(), function(result) {
		    		console.log(result);
		    	});
		    });
		});
	</script>
</body>
</html>