<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	var conti='<%=request.getParameter("k")%>';
	if(conti=='1')
	{
		alert("更新成功！");
	}
	else if(conti=='-2')
	{
		alert("可能的错误！");
	}else if(conti=='0')
	{
		alert("结束时间不得早于或等于开始时间！");
	}
	
	function confirm()
	{
		var a=alert("开始更新开放时间！");
		location.href="Uniadmin.action?id=${uadmin.uid}&tid=${t.tid}&opttype=updatetime"; 
	}
	</script>
	<meta charset="utf-8">
		<title>研究生导师自选系统（校级管理端）</title>
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
					margin:10px 50px 5px 40%;
				}
				.itext{
					height: 30px;
					width: 300px;
					
				}
				.ifieldtext{
					height: 100px;
					width: 880px;
					
				}
			</style>
	</head>
<body>
		<div id="wrapper">
			<div id="header">
				<font size="3">欢迎您！：${uadmin.uid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>●返回首页</a><br></br>
				<a href='Login.action?id=${uadmin.uid}&pwd=${uadmin.upwd}&identity=uniadmin'>●导师信息管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Uniadmin.action?id=${uadmin.uid}&opttype=tutorin'>1.导师信息录入</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Uniadmin.action?id=${uadmin.uid}&opttype=selectshowtutor'>2.导师信息维护</a><br></br>
				<a href='Login.action?id=${uadmin.uid}&pwd=${uadmin.upwd}&identity=uniadmin'>●研究生信息管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Uniadmin.action?id=${uadmin.uid}&opttype=graduin'>1.研究生信息录入</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Uniadmin.action?id=${uadmin.uid}&opttype=selectshowgrad'>2.研究生信息维护</a><br></br>
				<a href='Login.action?id=${uadmin.uid}&pwd=${uadmin.upwd}&identity=uniadmin'>●研究生导师设置</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Uniadmin.action?id=${uadmin.uid}&opttype=setsystime'><b>1.设置系统开放时间</b></a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Uniadmin.action?id=${uadmin.uid}&opttype=selectcoadmin'>2.维护院级管理员</a><br></br>
				<a href='Login.action?id=${uadmin.uid}&pwd=${uadmin.upwd}&identity=uniadmin'>●系统管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Uniadmin.action?id=${uadmin.uid}&opttype=changepwd'>1.更改密码</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Uniadmin.action?id=${uadmin.uid}&opttype=viewlogs'>2.查看系统日志</a><br></br>
				<a href='LoginFrm.html'>●安全退出</a>
				</div>
				<div id="main">
				<form action="Uniadmin.action?id=${uadmin.uid}" method="post" name="upsetime" id="upsetime">
				<input type=hidden name="opttype" value="updatetime">
					<br></br>
					<font size="4">欢迎您！管理员：${uadmin.uid}</font>
					<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<table >
								<thead>
									<tr>
										<th colspan="2">设置系统开放时间</th>
									</tr>
								</thead>
								<tbody>
										
										<tr class="info">
								         <td><b>开始时间</b></td>
								         <td><input type="date" id="txtDate" name="starttime" /></td>
								         
								      	</tr>
								       	
								       	<tr>
								       	<td><b>结束时间</b></td>
								        <td><input type="date" id="txtDate" name="endtime" /></td>
								       	</tr>

								</tbody>
							</table>
							<button class="input" onclick="confirm();">确定</button>
							<button type="button" onclick="javascript:window.location.href='Login.action?id=${uadmin.uid}&pwd=${uadmin.upwd}&identity=uniadmin' " onsubmit='return false';>返回</button>
							
						</div>	
					</div>	
					</form>		
				</div>
			</div>
		</div>
		
</body>
</html>