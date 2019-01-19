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
<link href="${baseUrl}/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
li[data-custom*="bcd"] {
	background-image: url(${baseUrl}/image/icon.png);
	background-repeat: x;
	background-position: center;
	border-style: outset;
	opacity: 0.5;
}
</style>
</head>
<body>
	<!-- !important加在属性值的后面总是优先于其他规则(重要性)>具有更高的专用性(专用性)>顺序后面的规则通常会覆盖较早的规则(源代码次序) -->
	<!-- 应用于某个元素的一些属性值将由该元素的子元素继承 -->
	<div
		style="width: 300px; padding: 5px 10px 15px 20px; border: 1px solid black;">
		<div>
			<label>子元素从父元素继承属性&nbsp;<span>text</span></label>
			<p>paragraph inherits red</p>
			<pre>
			abc
			def
			ghi
			</pre>
		</div>
		<div id="divId">select id</div>
		<div class="my_class">use class</div>
		<div>
			<img alt="test" src="/test.png" title="test" />
		</div>
		<!-- data-* 属性被称为 数据属性 -->
		<ul>
			<li data-custom="change">A</li>
			<li data-custom="abcde" class="my_class">B</li>
			<li data-custom="change" style="padding: 5px">C</li>
			<li data-custom="" class="my_class special">D</li>
			<li data-custom="change">E</li>
		</ul>
	</div>
	<p />
	<ul>
		<li style="float: left;"><a href="http://www.w3school.com.cn"
			target="_blank">a link</a></li>
		<li style="float: left;"><a href="http://www.w3school.com.cn/example/csse_examples.asp"
			target="_blank">b link</a></li>
	</ul>
	<p>
		some <i>text</i>. some <i>text</i>.
	</p>
	<p />
	<pre>
    aaaaaaaaaa
    bbbbbbbbbb
    cccccccccc
    </pre>
	<p />
	<table>
		<tr>
			<th>Firstname</th>
			<th>Lastname</th>
		</tr>
		<tr>
			<td>Bill</td>
			<td>Gates</td>
		</tr>
		<tr>
			<td>Steven</td>
			<td>Jobs</td>
		</tr>
	</table>
</body>
</html>