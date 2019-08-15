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
	<div>
		<label>testView</label> <input type="button" name="testButton1"
			value="/user/view" />
	</div>
	<div>
		<label>testUpdate</label> <input type="button" name="testButton2"
			value="/user/alter" />
	</div>
	<div>
		<label>testInsert</label> <input type="button" name="testButton3"
			value="/user/add" />
	</div>
	<div>
		<label>testDelete</label> <input type="button" name="testButton4"
			value="/user/remove" />
	</div>
	<div>
		<label>testFilter</label> <input type="button" name="testButton5"
			value="/user/filter" />
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