<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
		<title>研究生导师自选系统（学生端）</title>
			<style type="text/css">
				.cls1 {background-color: #F0F0F0;}
				tr:hover {color: aqua;}
				#wrapper {width: 100%;margin: 0 auto;}
				#header {float:right;width: 100%;height: 40px;background-color:#0080FF;text-align:right;}
				#container {width: 100%;height:1000px;margin-top: 5px;}
				#left {float:left;width: 4%;height: 95%;background-color:#ECF5FF}
				#leftbar {float:left;width: 15%;height: 95%;background-color:#ECF5FF}
				#main {float:left;text-align:center;width: 80%;height: 95%;background-color:#FFFFFF}
				#mainboard {text-align:left;margin:0 auto;width: 80%;height: 80%;background-color:#F0F0F0}
				#board {text-align:left;margin:0 auto;width: 95%;height: 90%;background-color:#FFFFFF}
				
				a{
					text-decoration:none;
				}
				a:visited{
					color:#2828FF;
				}
				
				
			</style>
	</head>
<body>
		<div id="wrapper">
			<div id="header">
				<font size="3">欢迎您！：${stu.sid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>返回首页</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=showtutors'>导师信息</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=selecttutors'>选择导师</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=showresult'>选择结果</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=changepwd'>修改密码</a><br></br>
				<a href='LoginFrm.html'>安全退出</a>
				</div>
				<div id="main">
					<br></br>
				<font size="4">欢迎您！： ${stu.sname},请阅读自主选择导师流程</font>
				<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<font size="3"><b>第一步:</b></font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">进入系统登录界面并以学生的身份登录，登录时需输入学号及密码并输入正确的验证码。</font>
							<hr/>
							
							<font size="3"><b>第二步:</b></font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">账号密码均验证通过且系统未关闭，则进入系统，提供4个菜单选项</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">1).查看本专业的导师信息;</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">2).选择导师:按第一、第二、第三志愿选择导师并提交;</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">3).查看最终导师:可以在系统开放时查看自己的志愿;</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">4).修改密码:可以更改密码。</font><br></br>
							<hr/>
							
							<font size="3"><b>第三步:</b></font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">退出系统，关闭浏览器。</font>
							<hr/>
							
						</div>	
					</div>			
				</div>
			</div>
		</div>
		
</body>
</html>