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
<!-- <script
	src="${pageContext.request.contextPath}/javascript/checkZipcode.js?rnd=<%=Math.random()%>"
	type="text/javascript"></script> -->
<script type="text/javascript">
	// 尾部的逗号会被忽略,中间为undefined
	var colors = [ 'red', , 'green', ];
	colors[3] = 'blue';

	var myObj = {
		property1 : "value1",
		property2 : "value2"
	};

	// 构建函数通常是大写字母开头便于区分构建函数和普通函数
	function Constructor(value4, value5) {
		this.property4 = value4;
		this.property5 = value5;
		this.desc = function() {
			// in will check the entire chain of the object (regardless of enumerability)
			for ( var p in this) {
				console.log(p + '   ' + this[p]);
			}
		}
	}

	function descending(a, b) {
		return b - a;
	}

	// 闭包允许将函数与其所操作的某些数据(环境)关连起来
	function innerFunction() {
		var num = 0;
		function changeNum(val) {
			num += val;
		}
		return {
			increment : function() {
				changeNum(1);
			},
			decrement : function() {
				changeNum(-1);
			},
			value : function() {
				return num;
			}
		};
	};

	function Person(name) {
		this.name = name;
		this.age = 17;
	}

	// prototype 属性包含(指向)一个对象,你在这个对象中定义需要被继承的成员
	// 属性很少定义在 prototype 属性中,在构造器内定义属性更好
	Person.prototype.sayHello = function() {
	};

	function Teacher() {
		// 在Teacher()构造函数里运行了Person()构造函数动态生成name,age属性
		Person.call(this, 'ada');
		this.occupation = 'teacher';
	}

	(function() {
		// 所有数字在javascript 中均用浮点数值表示
		// 空值null在数值型下会被当作０来对待,而布尔类型下会被当作false
		// 你可以从一个窗口或框架用指定窗口或框架名的方法,取得在另一个窗口或框架里声明的变量
		// 设想一个叫phoneNumber的变量在文档FRAMESET里被声明,你可以在其子框架里用变量parent.phoneNumber来指向它
		// this refers to the calling object in a method(this 在一个方法中指调用的对象)
		// 在form里面的文本框按回车键会自动触发form提交事件,如用ajax提交写return false阻止
		// ===和!==执行的是严格的相等性比较而不会在进行判断之前进行操作数类型转换
		// delete操作成功返回true否则false,属性或者元素会变成 undefined
		// 函数体中有一个名为 arguments的内部对象,arguments.callee指向当前的(调用)函数,可用于匿名函数递归调用
		// 闭包就是一个函数和执行函数时创建的作用域对象scope object(保存在这个函数中创建的局部变量)
		var val;
		if (typeof (val) === "undefined") {
			console.log(val);
		}

		// 没有语句块作用域
		if (true) {
			var x = 5;
		}
		alert(x);

		// push()    在数组的最后增加一个元素并且返回数组的新长度
		// pop()     删除最后一个元素并且返回该元素 
		// shift()   删除第一个元素并且返回该元素
		// unshift() 开头增加一个或多个元素并且返回数组的新长度
		// reverse() 数组逆序(会更改原数组 )
		// length是数组的属性,可以给length属性赋值写入0将把数组全部清空
		// 数组包含空元素(undefined)不返回元素
		// 因为javascript元素被作为标准的对象属性存储,所以不建议对数组使用 for...in循环进行遍历,它导致普通的元素和所有可枚举的属性都会出现在清单中
		// for (var i = 0, len = testArray.length; i < len; i++){}
		colors.forEach(function(each, index) {
			console.log(each + '   ' + index);
		});

		var myArray = [ (+"0.1") + Number("1.2"), 3.3 ];
		myArray.sort(descending);
		console.log(myArray.toString());
		delete myArray[1];
		// element[1] is undefined
		console.log(myArray.join());
		// array.splice(index, howMany, [element1][, ..., elementN]);
		myArray.splice(1, 1);
		console.log(myArray.join(" - "));

		myObj["property3"] = "value3";
		console.log(myObj["property3"]);

		var myObj2 = new Constructor("value4", "value5");
		delete myObj2["property4"];
		myObj2.desc();

		// omit hours, minutes or seconds. The value will be set to zero
		// Seconds and minutes: 0 to 59
		// Hours: 0 to 23
		// Day: 0 (Sunday) to 6 (Saturday)
		// Date: 1 to 31 (day of the month)
		// Months: 0 (January) to 11 (December)
		var dateObject = new Date("Month day, year hours:minutes:seconds");
		dateObject.getFullYear();
		var ymd = new Date(1995, 11, 25);
		var ymdhms = new Date(1995, 11, 25, 9, 30, 0);

		var counter1 = innerFunction();
		var counter2 = innerFunction();
		counter1.increment();
		counter2.decrement();
		console.log(counter1.value() + "   " + counter2.value());
		// 操作滚动条到底部
		document.documentElement.scrollTop = 999999;
		// 获取当前链接地址
		alert(window.location.href);
		// 检测对象的属性是定义在自身上还是在原型链上,有必要使用hasOwnProperty方法

		var p1 = new Person('p1');
		console.log(p1);
		if (Person === Person.prototype.constructor) {
			console.log(p1.constructor === Person.prototype.constructor);
		}
		Teacher.prototype = Object.create(Person.prototype);
		Teacher.prototype.constructor = Teacher;
		var p2 = new Teacher();
		console.log(p2);
	})();
</script>
</head>
<body>

</body>
</html>