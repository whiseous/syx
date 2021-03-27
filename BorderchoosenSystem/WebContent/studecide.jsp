<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	var conti='<%=request.getParameter("k")%>';
	if(conti=='-1')
	{
		alert("选择意向导师失败，请确认意向导师是否已经选满！。");
	}
	else if(conti=='-2')
	{
		alert("已经选过该导师了！");
	}
	
	function confirm()
	{
		var a=alert("已选择意向导师，可在 选择导师 界面查看。");
		location.href="Student.action?id=${stu.sid}&tid=${t.tid}&opttype=updatechoice"; 
	}
	</script>
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
				.input{
					margin:10px 50px 5px 40%;
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
				<a href='Student.action?id=${stu.sid}&opttype=showtutors'><b>导师信息</b></a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=selecttutors'>选择导师</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=showresult'>选择结果</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=changepwd'>修改密码</a><br></br>
				<a href='LoginFrm.html'>安全退出</a>
				</div>
				<div id="main">
					<br></br>
					<font size="4">您好！此为</font>
					<font size="4" color=#01814A >${cname}</font>
					<font size="4">：${t.tname}导师</font>
					<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<table >
								<thead>
									<tr>
										<th colspan="6">导师基本信息</th>
									</tr>
								</thead>
								<tbody>
										
										<tr class="info">
								         <td><b>姓名</b></td>
								         <td>${t.tname}</td>
								         <td><b>性别</b></td>
								         <td>${t.tgender}</td>
								         <td><b>出生年月</b></td>
								         <td>${t.tbirth}</td>
								      	</tr>
								       
								      	<tr>
								         <td><b>职称</b></td>
								         <td>${t.tjob}</td>
								         <td><b>最高学历</b></td>
								         <td>${t.tedu}</td>
								         <td><b>学科方向</b></td>
								         <td>${t.tsub}</td>
								      	</tr>
								      	
								      	<tr class="info">
								         <td><b>导师类型</b></td>
								         <td>${t.ttype}</td>
								         <td><b>所在学院</b></td>
								         <td>${cname}</td>
								         <td><b>办公电话</b></td>
								         <td>${t.ttele}</td>
								       	</tr>
								       	
								       	<tr>
								         <td><b>电子邮件</b></td>
								         <td colspan="5">${t.tmail}</td>
								      	</tr>
								      	
								      	<tr class="info">
								         <td><b>研究方向或领域</b></td>
								      	</tr>
								      	
								      	<tr>
								         <td colspan="6">${t.tfield}</td>
								      	</tr>
								</tbody>
							</table>
							<button class="input" onclick="confirm();">选择导师</button>
							<button onclick="javascript:window.location.href='Mainstusee.jsp'">返回</button>
							
						</div>	
					</div>			
				</div>
			</div>
		</div>
		
</body>
</html>