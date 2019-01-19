<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
	<div id="myDiv"></div>
	<div id="myDiv2"></div>
	<div>
		<input id="testGet" type="button" value="testGet" />
	</div>
	<div>
		<input id="testPost" type="button" value="testPost" />
	</div>

	<script type="text/javascript">
		var input1 = document.querySelector('#testGet');
		var input2 = document.querySelector('#testPost');
		/*The currentTarget property always refers to the element whose event listener triggered the event,
		opposed to the target property, which returns the element that triggered the event.*/
		input1.addEventListener('click', function(event) {
			sendGetRequest();
			console.log(event.target);
		}, true);
		input2.addEventListener('click', function(event) {
			sendPostRequest();
			console.log(event.currentTarget);
		});

		var xhr;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xhr = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xhr = new ActiveXObject("Microsoft.xhr");
		}

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				document.getElementById("myDiv").innerHTML = xhr.responseText;
				/* var xmldoc = xhr.responseXML;
				   var nodes = xmldoc.getElementsByTagName("info");
				   var txt = "";
				   for(var i = 0;i < nodes[0].childNodes.length;i++) {
				       txt += nodes[0].childNodes[i].nodeValue + " ";
				   }
				   document.getElementById("myDiv2").innerHTML = txt;
				 */
			}
		};

		function sendGetRequest() {
			xhr.open("GET",
					"${baseUrl}/OriginalAjaxServer?fname=Bill&lname=Gates",
					true);
			xhr.send();
		}

		function sendPostRequest() {
			xhr.open("POST", "${baseUrl}/OriginalAjaxServer", true);
			xhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhr.send("fname=Bill&lname=Gates");
		}
	</script>
</body>
</html>