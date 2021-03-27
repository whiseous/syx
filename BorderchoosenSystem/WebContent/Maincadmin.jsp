<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
		<title>研究生导师自选系统（院级管理端）</title>
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
				<font size="3">欢迎您！：${cadmin.tid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>●返回首页</a><br></br>
				<a href='Login.action?id=${cadmin.tid}&pwd=${cadmin.tpwd}&identity=coladmin'>●导师信息管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=tutorin'>1.导师信息录入</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=selectshowtutor'>2.导师信息维护</a><br></br>
				<a href='Login.action?id=${cadmin.tid}&pwd=${cadmin.tpwd}&identity=coladmin'>●研究生信息管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=graduin'>1.研究生信息录入</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=selectshowgrad'>2.研究生信息维护</a><br></br>
				<a href='Login.action?id=${cadmin.tid}&pwd=${cadmin.tpwd}&identity=coladmin'>●系统管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=changepwd'>1.更改密码</a><br></br>
				<a href='LoginFrm.html'>●安全退出</a>
				</div>
				<div id="main">
					<br></br>
				<font size="4">欢迎您！管理员：${cadmin.tname}，请阅读研究生导师双选系统院级管理流程</font>
				<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<font size="3"><b>第一步:</b></font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">进入系统登录界面并以校级管理员的身份登录，登录时需输入管理员编号及密码并输入验证码。</font>
							<hr/>
							
							<font size="3"><b>第二步:</b></font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">账号密码均验证通过且系统未关闭，则进入系统，提供3个菜单选项</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">1).导师信息管理，包括录入信息及维护信息;</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">2).研究生信息管理，包括录入信息及维护信息;</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">4).系统管理：更改密码。</font><br></br>
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