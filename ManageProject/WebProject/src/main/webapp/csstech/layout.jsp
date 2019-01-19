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
<style type="text/css">
#main {
	width: 500px;
	height: 400px;
	border: 1px solid red;
}

.displayClass {
	position: relative;
	width: 150px;
	height: 70px;
	border: 1px solid blue;
	color: green;
}

.floatClass {
	float: right;
	width: 70px;
	height: 35px;
	border: 1px solid purple;
}

.floatContainer {
	float: right;
	width: 150px;
	height: 90px;
	border: 1px solid orange;
}

#main2 {
	background-color: yellow;
}

#main3 {
	background-color: yellow;
	float: left;
}

p.clipped {
	width: 400px;
	height: 100px;
	padding: 10px;
	border: 1px solid black;
	line-height: 30px;
	overflow: auto; /* 试试hidden */
}

span {
	background-color: yellow;
}

em {
	background-color: pink;
	width: 240px;
	display: inline-block;
}
</style>
</head>
<body>
	<!-- 设置内容大小约束min-width,max-width,min-height,max-height -->
	<!-- block内容会独占一行,inline和其他行内元素出现在同一行(设置宽高行高无效),
	inline-block不会重新另起一行但会像行内框 一样随着周围文字而流动 -->
	<!-- 对<body>元素的外边距和内边距进行设定避免在不同的浏览器中出现差异 -->
	<div id="main">
		<!-- 浮动框不在文档的普通流中 -->
		<div class="floatClass">1</div>
		<div class="floatClass">2</div>
		<div class="floatClass">3</div>
		<div class="displayClass"></div>
		<!-- 相对定位时无论是否进行移动元素仍然占据原来的空间 -->
		<div class="displayClass" style="top: 20px; left: 30px;">相对于它的起点进行移动</div>
		<div class="displayClass"></div>
		<!-- 绝对定位使元素的位置与文档流无关因此不占据空间能够交叠元素 -->
		<div class="displayClass"
			style="position: absolute; top: 25px; left: 25px;">相对于最近的已定位祖先元素或最初的包含块</div>
		<!-- 之前已存在文档的普通流中元素会占据位置 -->
		<div class="floatContainer">
			<div class="floatClass" style="height: 50px;">4</div>
			<div class="floatClass">5</div>
			<div class="floatClass">6</div>
		</div>
		<span> <img style="float: left;"
			src="${baseUrl}/image/icon.png" />This is some text. This is some
			text. This is some text. This is some text. This is some text. This
			is some text. This is some text. This is some text. This is some
			text. This is some text. This is some text. This is some text. This
			is some text. This is some text.
		</span>
	</div>
	<p />
	<div id="main2">
		<img src="${baseUrl}/image/icon.png" style="float: left" /> <em
			style="float: right">This is some text.</em>
		<div style="clear: both"></div>
	</div>
	<div id="main3">
		<img src="${baseUrl}/image/icon.png" style="float: left" /> <em
			style="float: right">This is some text.</em>
	</div>
	<div>
		<p class="clipped">
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. <span
				style="display: inline;">Mauris tempus turpis id ante mollis
				dignissim.</span>abc <span style="display: inline-block;">Mauris
				tempus turpis id ante mollis dignissim.</span>abc <span
				style="display: block;">Mauris tempus turpis id ante mollis
				dignissim.</span> ornare venenatis augue hendrerit. Praesent non elit
			metus. Morbi vel sodales ligula.
		</p>
	</div>
	<div>
		<em style="display: none;">1</em><em style="visibility: hidden;">2</em><em>3</em>
	</div>
</body>
</html>