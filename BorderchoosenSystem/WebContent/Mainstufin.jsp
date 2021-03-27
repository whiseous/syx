<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
				
			</style>
	</head>
<body>
		<div id="wrapper">
			<div id="header">
				<font size="3">欢迎您！： ${stu.sid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>返回首页</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=showtutors'>导师信息</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=selecttutors'>选择导师</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=showresult'><b>选择结果</b></a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=changepwd'>修改密码</a><br></br>
				<a href='LoginFrm.html'>安全退出</a>
				</div>
				<div id="main">
					<br></br>
					<font size="4">${stu.sname}，您好！</font>
					<font size="4" color=#01814A >${cname}</font>
					<font size="4">，最终您的导师是</font>
					<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<table >
								<thead>
									<tr>
										<th>编号</th>
										<th>导师姓名</th>
										<th>导师类别</th>
										<th>学科方向</th>
										<th>查看详细</th>
									</tr>
								</thead>
								<tbody>
								       <tr bgcolor="#A6FFFF">
								         <td>${tutor.tid}</td>
								         <td>${tutor.tname}</td>
								         <td>${tutor.ttype}</td>
								         <td>${tutor.tsub}</td>
								         <td>
								            <a href='Student.action?id=${stu.sid}&tid=${t.tid}&opttype=viewtutor'>查看详细</a>
								         </td>
								       </tr>
								</tbody>
							</table>
						</div>	
					</div>			
				</div>
			</div>
		</div>
		
</body>
</html>