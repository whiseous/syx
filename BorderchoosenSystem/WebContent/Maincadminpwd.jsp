<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	var conti='<%=request.getParameter("pd")%>';
	if(conti=='-1')
	{
		alert("旧密码不相符！");
	}
	else if(conti=='-2')
	{
		alert("确认密码不相符！");
	}
	else if(conti=='1')
	{
		alert("更改密码成功！");
	}
	</script>
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
				#mainboard {text-align:left;margin:0 auto;width: 95%;height: 80%;background-color:#F0F0F0}
				#board {text-align:left;margin:0 auto;width: 95%;height: 90%;background-color:#FFFFFF}
				
				a{
					text-decoration:none;
				}
				a:visited{
					color:#2828FF;
				}
				table,th,td{
					border:1px lightgray solid;
					border-collapse:collapse;
				}
				table{
					width:100%;
					margin:0px auto;
				}
				th,td{
					padding:10px;
				}
				th{
					background-color:#FCFCFC;
					color:black;
				}
				.info{
					background-color:#F0F0F0;
					color:black;
				}
				.input{
					margin:10px 50px 5px 10px;
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
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=changepwd'><b>1.更改密码</b></a><br></br>
				<a href='LoginFrm.html'>●安全退出</a>
				</div>
				<div id="main">
				<form action="Coladmin.action?id=${cadmin.tid}" method="post" name="chpasswd" id="chpasswd">
				<input type=hidden name="opttype" value="dochangepwd">
					<br></br>
					<font size="4">您好！</font>
					<font size="4" color=#01814A >${cadmin.tname}</font>
					<font size="4">管理员，请更改您的密码</font>
					<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board" >
							旧密码:<input class="input" type="password" name="pastpwd" value="" ><br/>
							新密码:<input class="input" type="password" name="newpwd" value="" ><br/>
							确认密码:<input class="input" type="password" name="connewpwd" value="" ><br/>
							<input class="input" type="submit" value="确定" onclick="" style="width:80px;height:30px" ></input>
						</div>	
					</div>	
					</form>			
				</div>
			</div>
		</div>
		
</body>
</html>