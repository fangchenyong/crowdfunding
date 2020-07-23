<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- http://localhost:8080/crowdfunding-admin-webui/test/ssm.html -->
<link rel="stylesheet" href="layer/theme/default/layer.css">
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">

	$(function(){
		$("#btn5").click(function(){
			alert("fangchy");
			layer.alert("layer:fangchy");
		});
		$("#btn4").click(function(){

			// 准备要发送的数据
			var student = {
				"stuId": 5,
				"stuName":"joey",
				"address": {
					"province": "浙江",
					"city": "杭州",
					"street":"西湖"
				},
				"subjectList": [
					{
						"subjectName": "JavaSE",
						"subjectScore": 100
					},{
						"subjectName": "SSM",
						"subjectScore": 99
					}
				],
				"map": {
					"k1":"v1",
					"k2":"v2"
				}
			};

			// 将JSON对象转换为JSON字符串
			var requestBody = JSON.stringify(student);

			// 发送Ajax请求
			$.ajax({
				"url":"send/compose/object.json",
				"type":"post",
				"data":requestBody,
				"contentType":"application/json;charset=UTF-8",
				"dataType":"json",
				"success":function(response){
					console.log(response);
				},
				"error":function(response){
					console.log(response);
				}
			});

		});

		$("#btn3").click(function(){

			// 准备好要发送到服务器端的数组
			var array = [5, 8, 12];
			console.log(array.length);

			// 将JSON数组转换为JSON字符串
			var requestBody = JSON.stringify(array);
			// "['5','8','12']"
			console.log(requestBody.length);

			$.ajax({
				"url": "send/array/three.html",			// 请求目标资源的地址
				"type": "post",						// 请求方式
				"data": requestBody,				// 请求体
				"contentType": "application/json;charset=UTF-8",	// 设置请求体的内容类型，告诉服务器端本次请求的请求体是JSON数据
				"dataType": "text",					// 如何对待服务器端返回的数据
				"success": function(response) {		// 服务器端成功处理请求后调用的回调函数，response是响应体数据
					alert(response);
				},
				"error":function(response) {		// 服务器端处理请求失败后调用的回调函数，response是响应体数据
					alert(response);
				}
			});

		});

		$("#btn2").click(function(){

			$.ajax({
				"url": "send/array/two.html",			// 请求目标资源的地址
				"type": "post",						// 请求方式
				"data": {							// 要发送的请求参数
					"array[0]": 5,
					"array[1]": 8,
					"array[2]": 12
				},
				"dataType": "text",					// 如何对待服务器端返回的数据
				"success": function(response) {		// 服务器端成功处理请求后调用的回调函数，response是响应体数据
					alert(response);
				},
				"error":function(response) {		// 服务器端处理请求失败后调用的回调函数，response是响应体数据
					alert(response);
				}
			});

		});

		$("#btn1").click(function(){

			$.ajax({
				"url": "send/array/one.html",			// 请求目标资源的地址
				"type": "post",						// 请求方式
				"data": {							// 要发送的请求参数
					"array": [5,8,12]
				},
				"dataType": "text",					// 如何对待服务器端返回的数据
				"success": function(response) {		// 服务器端成功处理请求后调用的回调函数，response是响应体数据
					alert(response);
				},
				"error":function(response) {		// 服务器端处理请求失败后调用的回调函数，response是响应体数据
					alert(response);
				}
			});

		});
	});

</script>
</head>
<body>
	<a href="test/ssm.html">测试SSM整合环境</a>
	<br>
	<button id="btn1">Send [5,8,12] ONE</button>
	<br>
	<button id="btn2">Send [5,8,12] TWO</button>
	<br>
	<button id="btn3">Send [5,8,12] THREE</button>
	<br>
	<button id="btn4">Send Compose Object</button>
	<br>
	<button id="btn5">点我弹框</button>
</body>
</html>