<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
		<title>银行储蓄系统（业务员端）</title>
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
				<font size="3">欢迎您！：${bteller.eid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>●返回首页</a><br></br>
				<a href='Loginbstore.action?account=${bteller.account}&pwd=${bteller.password}&identity=bankteller'>●存款业务</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_DEPOSIT_LIVE'>1.活期存款</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_DEPOSIT_SOLID'>2.定期存款</a><br></br>
				<a href='Loginbstore.action?account=${bteller.account}&pwd=${bteller.password}&identity=bankteller'>●取款业务</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_WITHDRAW'>1.取款</a><br></br>
				<a href='Loginbstore.action?account=${bteller.account}&pwd=${bteller.password}&identity=bankteller'>●查询打印业务</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_PRINT_WITHDRAW'>1.用户取款记录</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_PRINT_HISTORY'>2.用户历史记录</a><br></br>
				<a href='Loginbstore.action?account=${bteller.account}&pwd=${bteller.password}&identity=bankteller'>●系统管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=changepwd'>1.更改密码</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=viewlogs'>2.查看日志</a><br></br>
				<a href='LoginFrm.html'>●安全退出</a>
				</div>
				<div id="main">
					<br></br>
				<font size="4">欢迎您！业务员：${bteller.eid}，请阅读银行储蓄系统业务流程</font>
				<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<font size="3"><b>第一步:</b></font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">进入系统登录界面并以业务员的身份登录，登录时需输入业务员账号及密码并输入验证码。</font>
							<hr/>
							
							<font size="3"><b>第二步:</b></font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">账号密码均验证通过，则进入系统，提供4个菜单选项</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">1).存款业务，包括活期、定期及存款记录查询;</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">2).取款业务，包括取款功能;</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">3).查询打印管理：包括查看取款记录及查看历史操作。</font><br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="3">4).系统管理：包括更改密码，查看日志。</font><br></br>
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